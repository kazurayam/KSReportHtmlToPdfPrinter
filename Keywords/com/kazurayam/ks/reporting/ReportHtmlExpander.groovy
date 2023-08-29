package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class ReportHtmlExpander {

	public Path expand(Path reportHtml, Path tempDir = null) {
		Objects.requireNonNull(reportHtml)
		assert Files.exists(reportHtml)
		if (tempDir != null) {
			assert Files.exists(tempDir)
			assert Files.isDirectory(tempDir)
		}
		//
		WebUI.openBrowser(reportHtml.toFile().toURI().toURL().toExternalForm())
		//
		WebUI.executeJavaScript("expandAllChildren('s1')", null)
		WebUI.delay(1)
		//
		String js = """
let s = new XMLSerializer();
let d = document;
let str = s.serializeToString(d);
return str
"""
		String serializedDOM = WebUI.executeJavaScript(js, null)
		Path tmpFile = Files.createTempFile(tempDir, ".tmp")
		tmpFile.toFile().text = serializedDOM
		//
		WebUI.closeBrowser()
		return tmpFile
	}
}
