package com.rambabu.kindnesspoints;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Converts players' kindness points into real-money donations to
 * verified NGOs (orphanages, old-age homes, social welfare causes).
 *
 * Conversion rate: 100 points = $1 donated (configurable).
 * Donations are tracked with receipts for full transparency.
 *
 * TODO: persist donations to the MySQL donations table (see schema.sql)
 * and integrate a real payment gateway for NGO payouts.
 */
@Service
public class DonationService {

    /** Points needed for one dollar of donation. */
    private static final int POINTS_PER_DOLLAR = 100;

    private final PointsService pointsService;

    // In-memory donation ledger (replace with MySQL for production)
    private final Map<String, List<Map<String, Object>>> ledger =
            new ConcurrentHashMap<>();

    public DonationService(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    /**
     * Converts a player's points into a donation to a verified NGO.
     *
     * @param userId the donating player
     * @param ngoId  the verified NGO receiving the donation
     * @param points points to convert
     * @return a donation receipt
     */
    public Map<String, Object> donate(String userId, String ngoId,
            int points) {
        // TODO: verify the NGO is approved before accepting the donation.
        pointsService.spend(userId, points);
        double amountUsd = points / (double) POINTS_PER_DOLLAR;

        Map<String, Object> receipt = Map.of(
                "donationId", UUID.randomUUID().toString(),
                "userId", userId,
                "ngoId", ngoId,
                "pointsConverted", points,
                "amountUsd", amountUsd,
                "status", "PLEDGED",
                "createdAt", LocalDateTime.now().toString());

        ledger.computeIfAbsent(userId, k -> new ArrayList<>()).add(receipt);
        return receipt;
    }

    /** Donation history for a player (for the donation-tracking screen). */
    public List<Map<String, Object>> history(String userId) {
        return ledger.getOrDefault(userId, List.of());
    }
}
