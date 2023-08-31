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
Path sourceReportsDir = projectDir.resolve("Reports")
Path targetReportsDir = projectDir.resolve("build/tmp/JongleurTest2plural/Reports")
WebUI.callTestCase(findTestCase("test/prepareWorkDir"), ["sourceReportsDir": sourceReportsDir, "targetReportsDir": targetReportsDir])

Jongleur jongleur = new Jongleur()
int numProcessed = jongleur.convertAllReportHtmlToPdf(targetReportsDir)
assert numProcessed > 0



