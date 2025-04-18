package com.example.myapplication.tests.junit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import com.example.myapplication.pages.CalculatorScreen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CalculatorTest {

    private static UiDevice device;
    private static final String CALC_PACKAGE = "com.google.android.calculator";
    private static final long TIMEOUT = 20000;

    @Before
    public void setup() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from home screen
        device.pressHome();
        //Initialize Calculator app icon element
        UiObject2 calculatorApp = device.findObject(By.text("Calculator"));
        // Click on app icon
        calculatorApp.click();
        // wait for calculator app
        device.wait(Until.hasObject(By.pkg(CALC_PACKAGE).depth(0)), TIMEOUT);

    }
    @Test
    public void additionTest() {

        CalculatorScreen calculatorScreen = new CalculatorScreen(device, CALC_PACKAGE);

        // Enter an equation: 5 + 3 = ?
        calculatorScreen.addNumber("5", "3");

        // Verify the result = 8
        Assert.assertEquals("8", calculatorScreen.getResult());
    }
}
