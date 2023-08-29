import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Path

import com.kazurayam.ks.reporting.ReportsDirectoryScanner
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * test/ReportsDirectoryScannerTest
 */
Path workDir = WebUI.callTestCase(findTestCase("test/prepareWorkDir"), [:])

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(workDir)
assert reportFolders.size() == 1

Path html = scanner.findHtmlFile(reportFolders.get(0))
assert html != null
assert html.getFileName().toString().endsWith(".html")

