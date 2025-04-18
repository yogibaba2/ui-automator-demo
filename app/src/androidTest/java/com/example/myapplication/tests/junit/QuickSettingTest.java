package com.example.myapplication.tests.junit;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class QuickSettingTest {
    UiDevice device;
    String PACKAGE_NAME = "com.android.settings";

    @Before
    public void setup() throws IOException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // ensure user is on home screen
        device.pressHome();

        //open quick settings
        device.openQuickSettings();
        device.wait(Until.findObjects(By.res("qs_pager")), 1000);

    }

    @Test
    public void enablePowerSaverTest(){
        UiObject2 scrollableObj = device.findObject(By.scrollable(true));
        scrollableObj.scroll(Direction.RIGHT,0.9f );

        UiObject2 batterySaver = device.findObject(By.text("Battery Saver, Off"));

        if(batterySaver.isChecked()){
            Log.i("enable-power-saver-test", "already enabled");
            return;
        }
        batterySaver.click();
        batterySaver.wait(Until.checked(batterySaver.isChecked()),10000);
    }

}
