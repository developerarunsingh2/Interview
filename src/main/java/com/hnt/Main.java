package com.hnt;

import java.util.Scanner;

public class Main {
    public static String reduceFraction(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        return numerator + "/" + denominator;
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static String coderFriends(int n, int ericaFirst, int ericaSecond, int bob) {
        int ericaWins = 0;
        ericaWins += n / 3; // Erica wins every 3rd day
        if (n % 3 != 0) {
            // Erica wins on the remaining days based on the questions she can solve
            if (ericaFirst == bob || ericaSecond == bob)
                ericaWins += n % 3;
            else
                ericaWins += Math.min(2, n % 3);
        }
        return reduceFraction(ericaWins, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int ericaFirst = scanner.nextInt();
            int ericaSecond = scanner.nextInt();
            int bob = scanner.nextInt();
            System.out.println(coderFriends(n, ericaFirst, ericaSecond, bob));
        }
        scanner.close();
    }
}

