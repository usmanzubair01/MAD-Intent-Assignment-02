# Assignment No 2: Android Intents

**Name:** Muhammad Usman  
**Roll No:** 100045  
**Session:** GCUF (2023–2027)  
**Course:** Mobile App Development 

## 📋 Project Overview

This Android application demonstrates **all Intent flavors and communication types**.

**Package:** `com.assignment2`  
**Language:** Java   
**UI:** XML Layouts with ConstraintLayout  
**Target SDK:** 34 (Android 14)  
**Min SDK:** 21 (Android 5.0)

---

## 📁 Project Structure

```
assignment2/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/assignment2/
│   │       │   ├── MainActivity.java          (5 buttons for Intent demos)
│   │       │   ├── SecondActivity.java        (Receives Intent data)
│   │       │   ├── MyService.java             (Background Service)
│   │       │   └── MyReceiver.java            (BroadcastReceiver)
│   │       ├── res/
│   │       │   └── layout/
│   │       │       ├── activity_main.xml      (Main UI with 5 buttons)
│   │       │       └── activity_second.xml    (Data display UI)
│   │       └── AndroidManifest.xml            (All declarations)
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---
### Intent Flavors Demonstrated:
- ✅ **Action** - All 5 buttons use different Intent actions
- ✅ **Category** - Categories applied in explicit, implicit, and broadcast intents
- ✅ **Data (URI)** - Phone dialer uses tel: URI
- ✅ **Flags** - FLAG_ACTIVITY_CLEAR_TOP used in Activity→Activity
- ✅ **Extras** - Data passed in all Intent types

### Intent Communication Types:
1. ✅ **Activity → Activity** (Button 1)
   - Explicit Intent
   - Extras: Student name, roll number, semester
   - Flag: CLEAR_TOP
   - Category: DEFAULT

2. ✅ **Activity → Service** (Button 2)
   - Start Service Intent
   - Extras: Service message, task ID
   - Toast from Service

3. ✅ **Activity → System App** (Button 3)
   - Implicit Intent
   - Action: ACTION_DIAL
   - Data: tel:+923001234567
   - Category: DEFAULT
   - Opens Phone Dialer

4. ✅ **Activity → Other App** (Button 4)
   - Implicit Intent
   - Action: ACTION_SEND
   - Type: text/plain
   - Extras: EXTRA_TEXT, EXTRA_SUBJECT
   - Chooser for app selection
   - Handles missing apps gracefully

5. ✅ **Activity → BroadcastReceiver** (Button 5)
   - Custom Broadcast Intent
   - Action: com.example.assignment2.CUSTOM_BROADCAST
   - Extras: Broadcast message, timestamp
   - Category: Custom category
   - Receiver shows Toast

---

## 🎯 Key Features

### MainActivity (5 Buttons)
Each button demonstrates a unique Intent use-case with clear labels:
1. "Activity → Activity (Explicit Intent + Extras + Flags)"
2. "Activity → Service (Start Service with Extras)"
3. "Activity → System App (Phone Dialer with URI + Category)"
4. "Activity → Other App (Share Text via Intent)"
5. "Activity → Broadcast (Custom Broadcast + Receiver)"

### SecondActivity
- Displays received Extras (name, roll number, semester)
- Shows Intent information (Action, Categories, Flags)
- Professional UI with cards and proper formatting

### MyService
- Receives Intent with Extras
- Shows Toast with received data
- Performs background task simulation
- Auto-stops after completion

### MyReceiver
- Listens for custom broadcast
- Extracts Extras from broadcast Intent
- Displays Toast with received message and timestamp
- Logs categories and actions

---

## 📱 How to Run

### Method 1: Android Studio
1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to and select the `assignment2` folder
4. Wait for Gradle sync to complete
5. Run the app on emulator or device

### Intent Action Examples:
```java
// Explicit Intent - No specific action needed
Intent intent = new Intent(MainActivity.this, SecondActivity.class);

// System App - ACTION_DIAL
Intent dialIntent = new Intent(Intent.ACTION_DIAL);

// Share - ACTION_SEND
Intent shareIntent = new Intent(Intent.ACTION_SEND);

// Custom Broadcast
Intent broadcastIntent = new Intent("com.example.assignment2.CUSTOM_BROADCAST");
```

### Intent Data (URI):
```java
dialIntent.setData(Uri.parse("tel:+923001234567"));
```

### Intent Flags:
```java
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
```

### Intent Categories:
```java
intent.addCategory(Intent.CATEGORY_DEFAULT);
```

### Intent Extras:
```java
// Putting extras
intent.putExtra("STUDENT_NAME", "John Doe");
intent.putExtra("ROLL_NUMBER", "CS-2024-001");

// Getting extras
String name = intent.getStringExtra("STUDENT_NAME");
int semester = intent.getIntExtra("SEMESTER", 0);
```

## 📝 AndroidManifest.xml Declarations

All components are properly declared:

```xml
<!-- MainActivity - Launcher -->
<activity android:name=".MainActivity" android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

<!-- SecondActivity -->
<activity android:name=".SecondActivity" android:exported="false" />

<!-- MyService -->
<service android:name=".MyService" android:exported="false" />

<!-- MyReceiver -->
<receiver android:name=".MyReceiver" android:exported="true">
    <intent-filter>
        <action android:name="com.example.assignment2.CUSTOM_BROADCAST" />
        <category android:name="com.example.assignment2.CATEGORY_CUSTOM" />
    </intent-filter>
</receiver>
```

## 📚 Learning Outcomes

This assignment demonstrates:
- ✅ Explicit vs Implicit Intents
- ✅ Intent components (Action, Category, Data, Flags, Extras)
- ✅ Inter-component communication
- ✅ Service lifecycle and background tasks
- ✅ Broadcast mechanism
- ✅ System integration (Phone Dialer)
- ✅ App-to-app communication (Sharing)
- ✅ Proper Manifest configuration
- ✅ Error handling for missing apps

