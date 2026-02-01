package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SecondActivity - Receives data from MainActivity via Intent Extras
 * Demonstrates how to extract data from Intent
 */
public class SecondActivity extends AppCompatActivity {

    private TextView tvStudentName;
    private TextView tvRollNumber;
    private TextView tvSemester;
    private TextView tvIntentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize TextViews
        tvStudentName = findViewById(R.id.tvStudentName);
        tvRollNumber = findViewById(R.id.tvRollNumber);
        tvSemester = findViewById(R.id.tvSemester);
        tvIntentInfo = findViewById(R.id.tvIntentInfo);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract Extras from the Intent
        if (intent != null) {
            // Get string extra
            String studentName = intent.getStringExtra("STUDENT_NAME");
            String rollNumber = intent.getStringExtra("ROLL_NUMBER");
            
            // Get integer extra with default value
            int semester = intent.getIntExtra("SEMESTER", 0);

            // Display the received data
            if (studentName != null) {
                tvStudentName.setText("Student Name: " + studentName);
            } else {
                tvStudentName.setText("Student Name: Not provided");
            }

            if (rollNumber != null) {
                tvRollNumber.setText("Roll Number: " + rollNumber);
            } else {
                tvRollNumber.setText("Roll Number: Not provided");
            }

            tvSemester.setText("Semester: " + semester);

            // Display Intent information (Action, Categories, Flags)
            StringBuilder intentInfo = new StringBuilder();
            
            // Action
            String action = intent.getAction();
            intentInfo.append("Action: ").append(action != null ? action : "None").append("\n\n");
            
            // Categories
            intentInfo.append("Categories: ");
            if (intent.getCategories() != null) {
                for (String category : intent.getCategories()) {
                    intentInfo.append(category).append(" ");
                }
            } else {
                intentInfo.append("None");
            }
            intentInfo.append("\n\n");
            
            // Flags
            int flags = intent.getFlags();
            intentInfo.append("Flags: ");
            if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0) {
                intentInfo.append("FLAG_ACTIVITY_CLEAR_TOP ");
            }
            if (flags == 0) {
                intentInfo.append("None");
            }

            tvIntentInfo.setText(intentInfo.toString());
        }
    }
}
