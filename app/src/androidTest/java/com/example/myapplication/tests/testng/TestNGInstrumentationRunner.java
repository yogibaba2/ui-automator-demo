package com.example.myapplication.tests.testng;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testng.TestNG;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;

@RunWith(AndroidJUnit4.class)
public class TestNGInstrumentationRunner {

    @Test
    public void runTestNGSuite() throws IOException {
        // Get the Android context to access assets
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        // Load the testng.xml file from assets
        InputStream testNgConfig = context.getAssets().open("testng.xml");

        // Create a temporary file to pass to TestNG
        File tempFile = File.createTempFile("testng", ".xml");
        Files.copy(testNgConfig, tempFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        // Run the TestNG suite
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList(tempFile.getAbsolutePath()));
        testng.run();
    }
}