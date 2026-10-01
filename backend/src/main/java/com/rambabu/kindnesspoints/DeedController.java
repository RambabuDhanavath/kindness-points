package com.rambabu.kindnesspoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * REST endpoints for logging and verifying good deeds.
 *
 *   POST /api/deeds            -> log a deed (with photo proof URL)
 *   GET  /api/deeds/user/{id}  -> deeds logged by a player
 *
 * Verified deeds earn points via {@link PointsService}.
 */
@RestController
@RequestMapping("/api/deeds")
public class DeedController {

    private final PointsService pointsService;

    public DeedController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> logDeed(
            @RequestBody Map<String, String> request) {
        String userId = request.getOrDefault("userId", "");
        String deedType = request.getOrDefault("deedType", "OTHER");
        String description = request.getOrDefault("description", "");
        String photoUrl = request.getOrDefault("photoUrl", "");

        // TODO: persist the deed with status PENDING, then verify
        // (community/admin review) before awarding points.
        int awarded = pointsService.awardForDeed(userId, deedType);

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "deedType", deedType,
                "description", description,
                "photoUrl", photoUrl,
                "status", "PENDING_VERIFICATION",
                "pointsAwarded", awarded));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Map<String, Object>>> deedsByUser(
            @PathVariable String userId) {
        // TODO: load the player's deeds from MySQL.
        return ResponseEntity.ok(List.of());
    }
}
