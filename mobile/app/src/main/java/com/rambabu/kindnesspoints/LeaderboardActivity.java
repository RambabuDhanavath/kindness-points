package com.rambabu.kindnesspoints;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Leaderboard screen.
 *
 * Shows the top kindness-point earners — friends, school, and global
 * boards — to encourage friendly competition around doing good.
 * Data comes from the backend (GET /api/leaderboard).
 */
public class LeaderboardActivity extends AppCompatActivity {

    private ListView leaderboardList;
    private ArrayAdapter<String> adapter;
    private final List<String> entries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardList = findViewById(R.id.leaderboardList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, entries);
        leaderboardList.setAdapter(adapter);

        loadLeaderboard();
    }

    /**
     * Loads the leaderboard from the backend.
     * TODO: GET /api/leaderboard and render real entries of the form
     * "#1 Ananya — 320 pts".
     */
    private void loadLeaderboard() {
        // Placeholder entries until the backend endpoint is wired up.
        entries.clear();
        entries.add("#1 — 320 pts");
        entries.add("#2 — 275 pts");
        entries.add("#3 — 210 pts");
        adapter.notifyDataSetChanged();
    }
}
