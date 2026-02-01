package com.example.assignment2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * MyService - Background Service that receives Intent with Extras
 * Demonstrates Activity → Service communication
 */
public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Check if intent is not null
        if (intent != null) {
            // Extract Extras from the Intent
            String message = intent.getStringExtra("SERVICE_MESSAGE");
            int taskId = intent.getIntExtra("TASK_ID", -1);

            // Build display message
            String displayMessage = "Service Received:\n" + 
                                  (message != null ? message : "No message") + 
                                  "\nTask ID: " + taskId;

            // Show Toast from Service
            // Note: Toast is shown on UI thread
            Toast.makeText(getApplicationContext(), displayMessage, Toast.LENGTH_LONG).show();

            // Simulate some background work
            performBackgroundTask(message, taskId);
        }

        // Return START_NOT_STICKY so service won't restart if killed
        return START_NOT_STICKY;
    }

    /**
     * Simulate background task execution
     * In real app, this could be network call, database operation, etc.
     */
    private void performBackgroundTask(String message, int taskId) {
        // Create a background thread for work
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate some work (2 seconds)
                    Thread.sleep(2000);
                    
                    // Log completion (in real app, you might update UI via broadcast)
                    android.util.Log.d("MyService", "Task completed: " + taskId);
                    
                    // Stop the service after work is done
                    stopSelf();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // This is a started service, not a bound service
        // Return null as we don't support binding
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Service is being destroyed
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }
}
