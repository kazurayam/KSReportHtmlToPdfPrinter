import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.ks.reporting.Jongleur
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * test/JongleurTestMore
 */
Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path ReportsDir = projectDir.resolve("build/tmp/JongleurTest3productional/Reports")

Jongleur jongleur = new Jongleur()
int numProcessed = jongleur.convertAllReportHtmlToPdf(ReportsDir)
assert numProcessed > 0



