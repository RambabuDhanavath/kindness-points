package com.rambabu.kindnesspoints;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Home screen of the Kindness Points app.
 *
 * Shows the player's current points balance, today's kindness challenge,
 * and navigation to the deed logger, points wallet, and leaderboard.
 */
public class MainActivity extends AppCompatActivity {

    private TextView pointsBalanceText;
    private TextView dailyChallengeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsBalanceText = findViewById(R.id.pointsBalanceText);
        dailyChallengeText = findViewById(R.id.dailyChallengeText);

        Button btnLogDeed = findViewById(R.id.btnLogDeed);
        Button btnWallet = findViewById(R.id.btnWallet);
        Button btnLeaderboard = findViewById(R.id.btnLeaderboard);

        btnLogDeed.setOnClickListener(v ->
                startActivity(new Intent(this, LogDeedActivity.class)));
        btnWallet.setOnClickListener(v ->
                startActivity(new Intent(this, WalletActivity.class)));
        btnLeaderboard.setOnClickListener(v ->
                startActivity(new Intent(this, LeaderboardActivity.class)));

        loadHomeData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHomeData();
    }

    /**
     * Fetches the player's points balance and today's challenge from the
     * backend (GET /api/users/me). Placeholder wiring — connect to your
     * Spring Boot backend here.
     */
    private void loadHomeData() {
        // TODO: call backend and update UI on the main thread.
        pointsBalanceText.setText("💛 0 points");
        dailyChallengeText.setText(
                "Today's challenge: help someone in your neighborhood!");
    }
}
