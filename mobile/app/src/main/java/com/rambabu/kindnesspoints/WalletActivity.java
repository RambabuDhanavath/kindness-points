package com.rambabu.kindnesspoints;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Points Wallet screen.
 *
 * Shows the player's points balance, lifetime earned/donated totals, and
 * recent point transactions. From here the player can convert points into
 * a donation to a verified NGO (POST /api/donations).
 */
public class WalletActivity extends AppCompatActivity {

    private TextView balanceText;
    private TextView lifetimeEarnedText;
    private TextView lifetimeDonatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        balanceText = findViewById(R.id.balanceText);
        lifetimeEarnedText = findViewById(R.id.lifetimeEarnedText);
        lifetimeDonatedText = findViewById(R.id.lifetimeDonatedText);
        Button btnDonate = findViewById(R.id.btnDonate);

        btnDonate.setOnClickListener(v -> donatePoints());

        loadWallet();
    }

    /**
     * Loads the wallet from the backend (GET /api/users/me/wallet).
     * TODO: replace with a real network call.
     */
    private void loadWallet() {
        balanceText.setText("💛 0 points available");
        lifetimeEarnedText.setText("Earned all time: 0");
        lifetimeDonatedText.setText("Donated all time: 0");
    }

    /**
     * Converts the player's points into a donation to a verified NGO.
     * TODO: show an NGO picker, then POST /api/donations
     * with {ngoId, points}.
     */
    private void donatePoints() {
        // TODO: real network call; on success refresh the wallet.
        Toast.makeText(this,
                "Thank you! Your points will help a verified NGO.",
                Toast.LENGTH_LONG).show();
    }
}
