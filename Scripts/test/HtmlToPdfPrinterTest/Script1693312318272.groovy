import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

import com.kazurayam.ks.reporting.HtmlToPdfPrinter
import com.kazurayam.ks.reporting.ReportHtmlExpander
import com.kazurayam.ks.reporting.ReportsDirectoryScanner
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 *  test/ReportHtmlExpanderTest
 */
Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path sourceReportsDir = projectDir.resolve("src/test/fixtures/Reports")
Path targetReportsDir = projectDir.resolve("build/tmp/HtmlToPdfPrinterTest/Reports")
WebUI.callTestCase(findTestCase("test/prepareTargetReportsDir"), ["sourceReportsDir": sourceReportsDir, "targetReportsDir": targetReportsDir])

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(targetReportsDir)
assert reportFolders.size() == 1

Path reportFolder = reportFolders.get(0)
Path html = scanner.findReportHtmlFile(reportFolder)
assert html != null
assert html.getFileName().toString() =~ ReportsDirectoryScanner.HTML_REPORT_FILENAME

Path tmpDir = Paths.get(RunConfiguration.getProjectDir()).resolve("build/tmp")
ReportHtmlExpander expander = new ReportHtmlExpander()
Path expandedTmp = expander.expand(html, tmpDir)
// overwrite the source html with the expanded one
Files.copy(expandedTmp, html, StandardCopyOption.REPLACE_EXISTING)

HtmlToPdfPrinter printer = new HtmlToPdfPrinter()
String chromeBinaryPath = HtmlToPdfPrinter.getChromeBinaryPath()
println "chromeBinaryPath: ${chromeBinaryPath}"
Path pdf = html.getParent().resolve("output.pdf")
printer.print(html, pdf)


