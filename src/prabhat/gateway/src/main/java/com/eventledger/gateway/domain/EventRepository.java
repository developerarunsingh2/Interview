package com.eventledger.gateway.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e FROM Event e WHERE e.accountId = ?1 ORDER BY e.eventTimestamp ASC")
    List<Event> findByAccountIdOrderByTimestamp(String accountId);

    List<Event> findByPropagationStatus(String propagationStatus);
}
