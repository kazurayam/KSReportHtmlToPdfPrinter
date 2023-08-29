import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Path

import com.kazurayam.ks.reporting.HtmlToPdfPrinter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 *  test/ReportHtmlExpanderTest
 */
Path workDir = WebUI.callTestCase(findTestCase("test/prepareWorkDir"), [:])

HtmlToPdfPrinter printer = new HtmlToPdfPrinter()

String chromeBinaryPath = HtmlToPdfPrinter.getChromeBinaryPath()
println "chromeBinaryPath: ${chromeBinaryPath}"