# TimerApp

This is a complete Android TV application that provides the three Sony Clock & Timer features you requested:

1. **Persistent digital clock** – an overlay displayed at the top‑left corner of the screen, always on‑top of other apps.
2. **Power‑on (wake‑up) at a specified time** – uses `AlarmManager.RTC_WAKEUP` to turn the TV on from standby and start the overlay service.
3. **Alarm popup** – a full‑screen activity that appears at the scheduled time (or after waking the TV) and can be dismissed.

## Project structure
```
 timerapp/
 ├─ app/
 │   ├─ src/main/AndroidManifest.xml
 │   ├─ src/main/java/com/example/timerapp/
 │   │   ├─ MainActivity.kt
 │   │   ├─ OverlayService.kt
 │   │   ├─ AlarmScheduler.kt
 │   │   ├─ WakeAlarmReceiver.kt
 │   │   ├─ BootReceiver.kt
 │   │   └─ AlarmFullScreenActivity.kt
 │   ├─ src/main/res/layout/
 │   │   ├─ activity_main.xml
 │   │   ├─ overlay_clock.xml
 │   │   └─ activity_alarm_fullscreen.xml
 │   └─ build.gradle   (module)
 ├─ build.gradle         (top‑level)
 └─ settings.gradle
```

## Build & install
1. **Open in Android Studio**
   - Choose *Open an existing project* and select the `timerapp` folder.
   - Android Studio will sync Gradle and download the required dependencies.
2. **Connect your TV** (ADB over network is already configured):
   ```bash
   adb connect 192.168.29.104:5555
   ```
3. **Build the debug APK**
   ```bash
   ./gradlew assembleDebug
   ```
   The output APK will be at `app/build/outputs/apk/debug/timerapp-debug.apk`.
4. **Install the APK**
   ```bash
   adb install -r app/build/outputs/apk/debug/timerapp-debug.apk
   ```
5. **Grant overlay permission**
   - Launch *TimerApp* from the TV launcher.
   - The app will prompt you to allow "Draw over other apps" – go to Settings → Apps → TimerApp → Permission and enable *Display over other apps*.
6. **Use the app**
   - The clock overlay starts automatically.
   - Press **Add Alarm (Demo)** to schedule a demo alarm one minute from now. You can replace the stub in `AlarmScheduler` with a proper UI for custom times and power‑on scheduling.
   - The TV will wake from standby at the scheduled power‑on time and show the overlay.
   - When the alarm fires, a full‑screen activity appears on the lock screen; dismiss it with the *Dismiss* button.

## Extending the app
- Implement a UI for selecting arbitrary alarm times and power‑on times (replace the demo code in `AlarmScheduler`).
- Persist alarms using `SharedPreferences` or a Room database and re‑schedule them in `BootReceiver`.
- Customize the overlay appearance (font, size, colors) by editing `overlay_clock.xml`.
- Add recurring alarms, snooze, etc., by storing recurrence rules and rescheduling after each trigger.

## Limitations
- The TV can only be woken from **standby** (sleep) – powering on from a completely powered‑off state requires OEM‑signed privileges, which is outside the scope of a regular Play‑store app.
- The overlay requires the user to manually grant the `SYSTEM_ALERT_WINDOW` permission.

Enjoy!
