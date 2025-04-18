package com.example.myapplication.tests.junit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import com.example.myapplication.util.Utilities;
import com.example.myapplication.watcher.Watchers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ProverbialTest {
    UiDevice device;
    String PACKAGE_NAME = "com.lambdatest.proverbial";
    String ACTIVITY_NAME = ".MainActivity";

    Utilities util;
    Watchers watcher;

    @Before
    public void setup() throws IOException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // start system setting intent
        util = new Utilities(device);

        //util.startActivity(PACKAGE_NAME, ACTIVITY_NAME);
        device.pressHome();
        util.openAppDrawer();

        UiObject2 appIcon = device.findObject(By.text("Proverbial"));
        appIcon.click();

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), 5000);

        // Check system settings has been opened.
        Assert.assertTrue(device.hasObject(By.pkg(PACKAGE_NAME)));
    }

    @Test
    public void textUpdateTest(){
        // initialise the text lable element
        UiObject2 textElement = device.findObject(By.res(PACKAGE_NAME, "Textbox"));
        // Assert the initial text
        Assert.assertEquals("Hello! Welcome to lambdatest Sample App called Proverbial", textElement.getText());

        UiObject2 textButton = device.findObject(By.res(PACKAGE_NAME, "Text"));

        //Click on Text button to update the element
        textButton.click();

        //Assert that text has been updated.
        Assert.assertEquals("Proverbial", textElement.getText());
    }

    @After
    public void tearDown() throws IOException {
        util.closeActivity(PACKAGE_NAME);
    }

}
