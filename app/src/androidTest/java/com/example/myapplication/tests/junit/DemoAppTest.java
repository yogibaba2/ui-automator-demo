package com.example.myapplication.tests.junit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import com.example.myapplication.util.Utilities;
import com.example.myapplication.watcher.Watchers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class DemoAppTest {
    UiDevice device;
    String PACKAGE_NAME = "com.appiumpro.the_app";
    String ACTIVITY_NAME = ".MainActivity";

    Utilities util;
    Watchers watcher;

    @Before
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
    public void enablePermissionTest(){
        UiObject2 geoLocationItem = device.findObject(By.res("Geolocation Demo"));

        geoLocationItem.click();

        device.runWatchers();
        boolean wasTriggered = device.hasWatcherTriggered("permissionWatcher");
        Assert.assertTrue(wasTriggered);

    }

    @After
    public void tearDown(){
        device.removeWatcher("permissionWatcher");
    }

}
