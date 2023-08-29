package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class ReportHtmlExpander {
	
	public static EXPANDING_JS = "expandAllChildren('s1')"
	
	public Path expand(Path reportHtml, Path tempDir) {
		Objects.requireNonNull(reportHtml)
		assert Files.exists(reportHtml)
		if (tempDir != null) {
			assert Files.exists(tempDir)
			assert Files.isDirectory(tempDir)
		}
		//
		WebUI.openBrowser(reportHtml.toFile().toURI().toURL().toExternalForm())
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
	}
}
