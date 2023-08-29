import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.ks.reporting.ReportsDirectoryScanner
import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path workDir = projectDir.resolve("build/tmp/test/smoke/Reports")

ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
List<Path> reportFolders = scanner.scanReportsDirectory(workDir)
assert reportFolders.size() == 1
