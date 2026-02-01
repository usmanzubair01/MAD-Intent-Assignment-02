package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity - Demonstrates all Intent flavors and communication types
 * Contains 5 buttons, each triggering a unique Intent use-case
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all 5 buttons
        Button btnActivityToActivity = findViewById(R.id.btnActivityToActivity);
        Button btnActivityToService = findViewById(R.id.btnActivityToService);
        Button btnActivityToSystem = findViewById(R.id.btnActivityToSystem);
        Button btnActivityToOtherApp = findViewById(R.id.btnActivityToOtherApp);
        Button btnActivityToBroadcast = findViewById(R.id.btnActivityToBroadcast);

        // Button 1: Activity → Activity (Explicit Intent with Extras and Flags)
        btnActivityToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Explicit Intent
                // Action: Default (no specific action needed for explicit intents)
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                
                // Extras: Pass data to the second activity
                intent.putExtra("STUDENT_NAME", "John Doe");
                intent.putExtra("ROLL_NUMBER", "CS-2024-001");
                intent.putExtra("SEMESTER", 5);
                
                // Flag: Clear top to prevent multiple instances
                // If SecondActivity is already running, bring it to front instead of creating new instance
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                
                // Category: Default category is automatically added
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                
                startActivity(intent);
            }
        });

        // Button 2: Activity → Service (Start Service with Extras)
        btnActivityToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to start Service
                Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
                
                // Extras: Pass data to service
                serviceIntent.putExtra("SERVICE_MESSAGE", "Hello from MainActivity!");
                serviceIntent.putExtra("TASK_ID", 12345);
                
                // Start the service
                startService(serviceIntent);
                
                Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_SHORT).show();
            }
        });

        // Button 3: Activity → System App (Implicit Intent with Action, Data, Category)
        btnActivityToSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Implicit Intent to open Phone Dialer
                // Action: ACTION_DIAL - Opens dialer with pre-filled number
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                
                // Data: URI with phone number
                dialIntent.setData(Uri.parse("tel:+923001234567"));
                
                // Category: Default category for browsable content
                dialIntent.addCategory(Intent.CATEGORY_DEFAULT);
                
                // Check if there's an app to handle this intent
                if (dialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(dialIntent);
                } else {
                    Toast.makeText(MainActivity.this, "No app found to handle this action", 
                                 Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button 4: Activity → Other App (Implicit Intent with Text Sharing)
        btnActivityToOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Implicit Intent to share text
                // Action: ACTION_SEND - Share content with other apps
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                
                // Type: Specify MIME type for text
                shareIntent.setType("text/plain");
                
                // Extras: Text to share
                shareIntent.putExtra(Intent.EXTRA_TEXT, 
                    "Check out this Android Assignment on Intents! Learn about Activity, Service, " +
                    "Broadcast, and System communication.");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Intents Assignment");
                
                // Category: Default
                shareIntent.addCategory(Intent.CATEGORY_DEFAULT);
                
                // Create chooser to let user select app
                // This handles the case when target app is not installed
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                
                // Verify that the intent will resolve to an activity
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                } else {
                    Toast.makeText(MainActivity.this, "No app available for sharing", 
                                 Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button 5: Activity → BroadcastReceiver (Custom Broadcast)
        btnActivityToBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent for custom broadcast
                // Action: Custom action name
                Intent broadcastIntent = new Intent("com.example.assignment2.CUSTOM_BROADCAST");
                
                // Extras: Pass data to broadcast receiver
                broadcastIntent.putExtra("BROADCAST_MESSAGE", "Custom Broadcast Triggered!");
                broadcastIntent.putExtra("TIMESTAMP", System.currentTimeMillis());
                
                // Category: Custom category
                broadcastIntent.addCategory("com.example.assignment2.CATEGORY_CUSTOM");
                
                // Send the broadcast
                sendBroadcast(broadcastIntent);
                
                Toast.makeText(MainActivity.this, "Broadcast Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
