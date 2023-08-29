import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Path

import com.kazurayam.ks.reporting.HtmlToPdfPrinter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.nio.file.StandardCopyOption
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.ks.reporting.ReportHtmlExpander
import com.kazurayam.ks.reporting.ReportsDirectoryScanner
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 *  test/ReportHtmlExpanderTest
 */
HtmlToPdfPrinter printer = new HtmlToPdfPrinter()
String chromeBinaryPath = HtmlToPdfPrinter.getChromeBinaryPath()
println "chromeBinaryPath: ${chromeBinaryPath}"

Path workDir = WebUI.callTestCase(findTestCase("test/prepareWorkDir"), [:])

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(workDir)
assert reportFolders.size() == 1

Path reportFolder = reportFolders.get(0)
Path html = scanner.findHtmlFile(reportFolder)
assert html != null
assert html.getFileName().toString() =~ ReportsDirectoryScanner.HTML_REPORT_FILENAME

Path tmpDir = Paths.get(RunConfiguration.getProjectDir()).resolve("build/tmp/test")
ReportHtmlExpander expander = new ReportHtmlExpander()
Path expandedTmp = expander.expand(html, tmpDir)
// overwrite the source html with the expanded one
Files.copy(expandedTmp, html, StandardCopyOption.REPLACE_EXISTING)

Path pdf = html.getParent().resolve("output.pdf")
printer.print(html, pdf)

