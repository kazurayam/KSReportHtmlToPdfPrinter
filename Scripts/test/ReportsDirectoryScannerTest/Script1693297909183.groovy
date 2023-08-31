import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.ks.reporting.ReportsDirectoryScanner
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * test/ReportsDirectoryScannerTest
 */
Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path sourceReportsDir = projectDir.resolve("src/test/fixtures/Reports")
Path targetReportsDir = projectDir.resolve("build/tmp/ReportsDirectoryScannerTest/Reports")
WebUI.callTestCase(findTestCase("test/prepareTargetReportsDir"), ["sourceReportsDir": sourceReportsDir, "targetReportsDir": targetReportsDir])

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(targetReportsDir)
assert reportFolders.size() == 1

Path html = scanner.findReportHtmlFile(reportFolders.get(0))
assert html != null
assert html.getFileName().toString().endsWith(".html")

