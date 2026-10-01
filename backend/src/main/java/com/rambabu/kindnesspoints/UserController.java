package com.rambabu.kindnesspoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST endpoints for player accounts.
 *
 *   POST /api/users/register  -> register a new player (kid + guardian)
 *   GET  /api/users/{id}      -> player profile
 *   GET  /api/users/me        -> current player profile + points balance
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final PointsService pointsService;

    public UserController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody Map<String, String> request) {
        String name = request.getOrDefault("name", "");
        String guardianEmail = request.getOrDefault("guardianEmail", "");
        // TODO: persist the player, hash any credentials, issue a JWT.
        return ResponseEntity.ok(Map.of(
                "name", name,
                "guardianEmail", guardianEmail,
                "message", "Welcome to Kindness Points!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> profile(
            @PathVariable String id) {
        // TODO: load the player from MySQL.
        return ResponseEntity.ok(Map.of(
                "userId", id,
                "pointsBalance", pointsService.getBalance(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me() {
        // TODO: resolve the player from the JWT.
        String demoUserId = "demo-player";
        return ResponseEntity.ok(Map.of(
                "userId", demoUserId,
                "pointsBalance", pointsService.getBalance(demoUserId),
                "dailyChallenge",
                "Help someone in your neighborhood today!"));
    }
}
