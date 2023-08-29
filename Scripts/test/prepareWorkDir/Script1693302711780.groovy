import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.apache.commons.io.FileUtils

import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.ks.reporting.ReportsDirectoryScanner

/**
 * test/prepareWorkDir
 */
Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path fixtureReportsDir = projectDir.resolve("src/test/fixtures/Reports")
Path workDir = projectDir.resolve("build/tmp/test/Reports")
if (Files.exists(workDir)) {
	FileUtils.deleteDirectory(workDir.toFile())
}
Files.createDirectories(workDir)
FileUtils.copyDirectory(fixtureReportsDir.toFile(), workDir.toFile())

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(workDir)
assert reportFolders.size() > 0

return reportFolders.get(0)
