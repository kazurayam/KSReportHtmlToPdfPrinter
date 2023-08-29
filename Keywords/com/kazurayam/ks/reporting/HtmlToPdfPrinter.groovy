package com.kazurayam.ks.reporting

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import java.nio.file.Path
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kazurayam.subprocessj.OSType
import com.kazurayam.subprocessj.CommandLocator
import com.kazurayam.subprocessj.CommandLocator.CommandLocatingResult

public class HtmlToPdfPrinter {

	public void print(Path html, Path pdf) {
	}

	public String getChromeBinaryBath() {
		if (OSType.isMac()) {
			return "/Applications/Google Chrome.app/Contents/MacOS"
		} else (OSType.isWindows() ) {
			CommandLocator.CommandLocatingResult cfr = CommandLocator.find("chrome");
			if (cfr.returncode() == 0) {
				return cfr.command()
			} else {
				throw new RuntimeException("could not find the path of the chrome command")
			}
		}
	}
}
