package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

public class ReportHtmlExpander {

	public static EXPANDING_JS = "expandAllChildren('s1');"

	private TestObject makeTestObject(String id, String selector) {
		TestObject tObj = new TestObject(id)
		tObj.addProperty("css", ConditionType.EQUALS, selector)
		return tObj
	}

	public Path expand(Path reportHtml, Path tempDir = null) {
		Objects.requireNonNull(reportHtml)
		assert Files.exists(reportHtml)
		if (tempDir != null) {
			assert Files.exists(tempDir)
			assert Files.isDirectory(tempDir)
		}
		try {
			WebUI.openBrowser(reportHtml.toFile().toURI().toURL().toExternalForm())
			TestObject expandAll = makeTestObject("expandAll", "a.expand")
			WebUI.waitForElementPresent(expandAll, 10)
			//
			WebUI.executeJavaScript(EXPANDING_JS, null)
			WebUI.delay(1)
			//
			String js = """
let s = new XMLSerializer();
let d = document;
let str = s.serializeToString(d);
return str
"""
			String serializedDOM = WebUI.executeJavaScript(js, null)
			Path tmpFile = Files.createTempFile(tempDir, null, ".html")
			tmpFile.toFile().text = serializedDOM
			//
			WebUI.closeBrowser()
			return tmpFile
		} catch (Exception e) {
			KeywordUtil.markWarning("failed to expand ${reportHtml.toString()}")
			return null
		}
	}
}
