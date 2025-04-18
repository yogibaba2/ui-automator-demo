package com.example.myapplication.pages;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

public class CalculatorScreen {
    private UiDevice device;
    private String CALC_PACKAGE;

    public CalculatorScreen(UiDevice device, String CALC_PACKAGE){
        this.device = device;
        this.CALC_PACKAGE =CALC_PACKAGE;
    }

    public void addNumber(String digit1, String digit2){
        UiObject2 digit_5 = device.findObject(By.res(CALC_PACKAGE, "digit_" + digit1));
        digit_5.click();

        UiObject2 op_add = device.findObject(By.res(CALC_PACKAGE, "op_add"));
        op_add.click();

        UiObject2 digit_3 = device.findObject(By.res(CALC_PACKAGE, "digit_" + digit2));
        digit_3.click();

        UiObject2 eq = device.findObject(By.res(CALC_PACKAGE, "eq"));
        eq.click();
    }

    public String getResult(){
        UiObject2 result = device.findObject(By.res(CALC_PACKAGE, "result_final"));
        return result.getText();
    }

}
