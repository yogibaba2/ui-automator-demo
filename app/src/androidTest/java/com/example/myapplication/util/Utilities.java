package com.example.myapplication.util;

import java.io.IOException;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;
import static org.junit.Assert.*;

import org.junit.Assert;

public class Utilities {

    UiDevice device;

    public Utilities(UiDevice device){this.device = device;}
    public void installApk(String path, String PACKAGE_NAME){

        device.performActionAndWait(() -> {
            try {
                device.executeShellCommand("am start -a android.settings.SETTINGS");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, Until.newWindow(), 10000);
    }

    public void checkApkInstalled(String PACKAGE_NAME) throws Exception {
        UiDevice uiDevice = UiDevice.getInstance();
        String command = "pm list packages";
        String output = uiDevice.executeShellCommand(command);

        if (output.contains("package:" + PACKAGE_NAME)) {
            assertTrue(true); // APK is installed
        } else {
            fail("APK not installed: " + PACKAGE_NAME); // APK is not installed
        }
    }

    public void startActivity(String packageName, String activityName) throws IOException {
        // start system setting intent
        device.performActionAndWait(() -> {
            try {
                device.executeShellCommand("am start -n " + packageName + "/" + activityName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, Until.newWindow(), 10000);
        // Check system settings has been opened.
        Assert.assertTrue(device.hasObject(By.pkg(packageName)));
    }

    public void closeActivity(String packageName) throws IOException {
        device.executeShellCommand("pm clear " + packageName);

        Assert.assertFalse(device.hasObject(By.pkg(packageName)));
    }

    public void openAppDrawer(){
        // Get the screen's dimensions
        int screenWidth = device.getDisplayWidth();
        int screenHeight = device.getDisplayHeight();

        // Swipe up from the bottom center of the screen to open the App Drawer
        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.9); // Bottom of the screen
        int endY = (int) (screenHeight * 0.3);  // Towards the top
        device.swipe(startX, startY, startX, endY, 50);

        // Wait for UI to settle
        device.waitForIdle();
    }

}
