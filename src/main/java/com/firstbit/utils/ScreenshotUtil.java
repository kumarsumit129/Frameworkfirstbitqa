package com.firstbit.utils;

//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
public class ScreenshotUtil {
    public static void takeFullPageScreenshot(FirefoxDriver driver, String fileName) throws IOException {
        File screenshotDir = new File("screenshots");
        if (!screenshotDir.exists()) screenshotDir.mkdir();

        File srcFile = driver.getFullPageScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(screenshotDir, fileName + ".png"));
    }
}

