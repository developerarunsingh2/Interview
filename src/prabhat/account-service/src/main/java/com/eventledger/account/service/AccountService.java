package com.eventledger.account.service;

import com.eventledger.account.domain.Transaction;
import com.eventledger.account.domain.TransactionRepository;
import com.eventledger.account.domain.TransactionRequest;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MeterRegistry meterRegistry;

    @org.springframework.transaction.annotation.Transactional
    public void applyTransaction(String accountId, TransactionRequest request, String traceId) {
        if (traceId != null) {
            MDC.put("traceId", traceId);
        }
        
        String transactionType = request.getType() != null ? request.getType() : "UNKNOWN";
        
        try {
            Optional<Transaction> existing = transactionRepository.findById(request.getEventId());
            if (existing.isPresent()) {
                logger.info("Transaction already applied: {}", request.getEventId());
                meterRegistry.counter("account_transactions_processed_total", "status", "duplicate", "type", transactionType).increment();
                return;
            }

            Transaction transaction = new Transaction(
                request.getEventId(),
                accountId,
                request.getType(),
                BigDecimal.valueOf(request.getAmount()),
                Instant.parse(request.getEventTimestamp())
            );

            transactionRepository.save(transaction);
            logger.info("Transaction applied: accountId={}, eventId={}, type={}, amount={}", 
                accountId, request.getEventId(), request.getType(), request.getAmount());
            meterRegistry.counter("account_transactions_processed_total", "status", "success", "type", transactionType).increment();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            logger.info("Transaction already applied (concurrent request): {}", request.getEventId());
            meterRegistry.counter("account_transactions_processed_total", "status", "duplicate", "type", transactionType).increment();
        } catch (Exception e) {
            meterRegistry.counter("account_transactions_processed_total", "status", "error", "type", transactionType).increment();
            throw e;
        } finally {
            if (traceId != null) {
                MDC.remove("traceId");
            }
        }
    }

    public BigDecimal calculateBalance(String accountId) {
        MDC.put("accountId", accountId);
        
        try {
            List<Transaction> transactions = transactionRepository.findByAccountIdOrderByTimestamp(accountId);
            BigDecimal balance = BigDecimal.ZERO;

            for (Transaction tx : transactions) {
                if ("CREDIT".equals(tx.getType())) {
                    balance = balance.add(tx.getAmount());
                } else if ("DEBIT".equals(tx.getType())) {
                    balance = balance.subtract(tx.getAmount());
                }
            }

            logger.info("Balance calculated: accountId={}, balance={}", accountId, balance);
            return balance;
        } finally {
            MDC.remove("accountId");
        }
    }

    public List<Transaction> getAccountTransactions(String accountId) {
        return transactionRepository.findByAccountIdOrderByTimestamp(accountId);
    }
}
