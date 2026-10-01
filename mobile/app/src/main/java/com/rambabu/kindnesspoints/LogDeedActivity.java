package com.rambabu.kindnesspoints;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Log Deed screen.
 *
 * The player picks a deed type (e.g. "Helped an elderly person"),
 * writes a short description, attaches a photo as proof, and submits.
 * The backend verifies the deed and awards points
 * (POST /api/deeds).
 */
public class LogDeedActivity extends AppCompatActivity {

    private Spinner deedTypeSpinner;
    private EditText deedDescriptionInput;

    // Deed types shown in the picker, with their point values.
    private static final String[] DEED_TYPES = {
            "Helped an elderly person (10 pts)",
            "Walking / exercise milestone (5 pts)",
            "Watched an educational video (3 pts)",
            "Volunteered at a community drive (15 pts)",
            "Other good deed"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_deed);

        deedTypeSpinner = findViewById(R.id.deedTypeSpinner);
        deedDescriptionInput = findViewById(R.id.deedDescriptionInput);
        Button btnAttachPhoto = findViewById(R.id.btnAttachPhoto);
        Button btnSubmitDeed = findViewById(R.id.btnSubmitDeed);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, DEED_TYPES);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        deedTypeSpinner.setAdapter(adapter);

        btnAttachPhoto.setOnClickListener(v ->
                // TODO: launch the camera/gallery and keep the photo URI.
                Toast.makeText(this, "Photo picker coming soon",
                        Toast.LENGTH_SHORT).show());

        btnSubmitDeed.setOnClickListener(v -> submitDeed());
    }

    /**
     * Submits the deed to the backend for verification and points.
     * TODO: POST /api/deeds with {type, description, photoUrl}.
     */
    private void submitDeed() {
        String type = deedTypeSpinner.getSelectedItem().toString();
        String description = deedDescriptionInput.getText().toString().trim();
        if (description.isEmpty()) {
            Toast.makeText(this, "Tell us what you did!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO: real network call; on success show awarded points.
        Toast.makeText(this, "Deed submitted for verification: " + type,
                Toast.LENGTH_LONG).show();
        finish();
    }
}
