package com.example.myapplication.tests.junit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class SettingTest {
    UiDevice device;
    String PACKAGE_NAME = "com.android.settings";

    @Before
    public void setup() throws IOException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // ensure setting app is closed before starting test
        device.executeShellCommand("pm clear " + PACKAGE_NAME);
        // start system setting intent
        device.performActionAndWait(() -> {
            try {
                device.executeShellCommand("am start -a android.settings.SETTINGS");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, Until.newWindow(), 10000);
        // Check system settings has been opened.
        Assert.assertTrue(device.hasObject(By.pkg(PACKAGE_NAME)));
    }

    @Test
    public void aboutInfoTest(){
        UiObject2 scrollableObj = device.findObject(By.scrollable(true));
        scrollableObj.scrollUntil(Direction.DOWN, Until.hasObject(By.text("About emulated device")));
    }

}
