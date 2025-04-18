package com.example.myapplication.tests.testng;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import com.example.myapplication.util.Utilities;
import com.example.myapplication.watcher.Watchers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CameraTest {
    private UiDevice device;
    private Watchers watcher;
    String PACKAGE_NAME = "com.android.camera2";
    String ACTIVITY_NAME = "com.android.camera.CameraLauncher";

    Utilities util;

    @BeforeClass
    public void setup() throws IOException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // start system setting intent
        util = new Utilities(device);
        watcher = new Watchers(device);

        //register a watcher
        device.registerWatcher("permissionWatcher", watcher.permissionDialogWatcher);


        util.startActivity(PACKAGE_NAME, ACTIVITY_NAME);
        // Check system settings has been opened.
        Assert.assertTrue(device.hasObject(By.pkg(PACKAGE_NAME)));

    }

    @Test
    public void testSwitchToFrontCamera() throws UiObjectNotFoundException {

        UiObject2 nextButton = device.findObject(By.text("NEXT"));
        nextButton.click();

        device.runWatchers();

        //open mode option of camera
        device.wait(Until.hasObject(By.res(PACKAGE_NAME, "mode_options_toggle")), 5000);
        UiObject2 moreOption = device.findObject(By.res(PACKAGE_NAME,"mode_options_toggle"));
        moreOption.click();

        //click camera toggle button
        device.wait(Until.hasObject(By.res(PACKAGE_NAME, "camera_toggle_button")), 5000);
        UiObject2 toggleButton = device.findObject(By.res(PACKAGE_NAME, "camera_toggle_button"));
        toggleButton.click();

        //Validate that flash toggle button is disabled for front camera
        device.wait(Until.hasObject(By.res(PACKAGE_NAME, "flash_toggle_button")), 5000);
        UiObject2 flashToggleButton = device.findObject(By.res(PACKAGE_NAME, "flash_toggle_button"));
        Assert.assertFalse(flashToggleButton.isEnabled());


    }

    @AfterClass
    public void tearDown() {
        device.pressHome();
    }

}
