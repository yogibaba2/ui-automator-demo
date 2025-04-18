package com.example.myapplication.watcher;

import android.util.Log;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiWatcher;

import java.util.Objects;

public class Watchers {
    private final UiDevice device;

    public Watchers(UiDevice device){
        this.device = device;
    }

    public UiWatcher permissionDialogWatcher = new UiWatcher() {
        @Override
        public boolean checkForCondition() {
            String PERMISSION_PACKAGE = "com.android.permissioncontroller";
            // Look for a permission dialog that might appear
            UiObject2 permissionDialogWatcher = device.findObject(By.res(PERMISSION_PACKAGE ,"grant_dialog"));
            if (permissionDialogWatcher != null) {
                try {
                    // Handle the dialog, e.g., press OK button
                    UiObject2 onlyThisTimeButton = device.findObject(By.text("Only this time"));
                    if (onlyThisTimeButton.isClickable()) {
                        onlyThisTimeButton.click();
                        return true; // Condition handled
                    }
                } catch (Exception e) {
                    Log.e("testing-error", Objects.requireNonNull(e.getLocalizedMessage()));
                }
            }
            return false; // Condition not handled
        }
    };
}
