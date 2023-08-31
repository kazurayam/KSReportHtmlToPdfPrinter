import java.nio.file.Files
import java.nio.file.Path

import org.apache.commons.io.FileUtils

import com.kazurayam.ks.reporting.ReportsDirectoryScanner

/**
 * test/prepareTargetReportsDir
 */
assert sourceReportsDir != null
assert targetReportsDir != null

if (Files.exists(targetReportsDir)) {
	FileUtils.deleteDirectory(targetReportsDir.toFile())
}
Files.createDirectories(targetReportsDir)
FileUtils.copyDirectory(sourceReportsDir.toFile(), targetReportsDir.toFile())

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(targetReportsDir)
assert reportFolders.size() > 0
