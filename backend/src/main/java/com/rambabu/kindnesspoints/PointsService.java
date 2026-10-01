package com.rambabu.kindnesspoints;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Core points logic: awarding points for verified deeds and tracking
 * each player's balance.
 *
 * Point values follow the Kindness Points reward table:
 *   HELP_ELDERLY   -> 10 points
 *   EXERCISE       -> 5 points
 *   EDUCATIONAL    -> 3 points
 *   VOLUNTEER      -> 15 points
 *   other          -> 2 points
 *
 * Balances are kept in memory here; wire this to the MySQL
 * point_transactions table (see schema.sql) for production.
 */
@Service
public class PointsService {

    private final Map<String, Integer> balances = new ConcurrentHashMap<>();

    /**
     * Awards points to a player for a deed type.
     *
     * @return the number of points awarded
     */
    public int awardForDeed(String userId, String deedType) {
        int points = switch (deedType) {
            case "HELP_ELDERLY" -> 10;
            case "EXERCISE" -> 5;
            case "EDUCATIONAL" -> 3;
            case "VOLUNTEER" -> 15;
            default -> 2;
        };
        balances.merge(userId, points, Integer::sum);
        return points;
    }

    /** Current points balance for a player. */
    public int getBalance(String userId) {
        return balances.getOrDefault(userId, 0);
    }

    /**
     * Deducts points when they are converted into a donation.
     *
     * @throws IllegalArgumentException if the balance is insufficient
     */
    public void spend(String userId, int points) {
        int balance = getBalance(userId);
        if (balance < points) {
            throw new IllegalArgumentException(
                    "Insufficient points: have " + balance
                            + ", tried to spend " + points);
        }
        balances.put(userId, balance - points);
    }
}
