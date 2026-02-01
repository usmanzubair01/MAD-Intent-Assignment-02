package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * MyReceiver - BroadcastReceiver that receives custom broadcasts
 * Demonstrates Activity → BroadcastReceiver communication
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Verify this is our custom broadcast action
        if (intent.getAction() != null && 
            intent.getAction().equals("com.example.assignment2.CUSTOM_BROADCAST")) {
            
            // Extract Extras from the broadcast Intent
            String message = intent.getStringExtra("BROADCAST_MESSAGE");
            long timestamp = intent.getLongExtra("TIMESTAMP", 0);

            // Format the received data
            String displayMessage = "Broadcast Received!\n" + 
                                  (message != null ? message : "No message") + 
                                  "\nTime: " + new java.util.Date(timestamp).toString();

            // Show Toast to indicate broadcast was received
            Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();

            // Log the broadcast reception
            android.util.Log.d("MyReceiver", "Custom broadcast received: " + message);

            // Get categories if any
            if (intent.getCategories() != null) {
                for (String category : intent.getCategories()) {
                    android.util.Log.d("MyReceiver", "Category: " + category);
                }
            }
        }
    }
}
