import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files
import org.apache.commons.io.FileUtils

import com.kms.katalon.core.configuration.RunConfiguration

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path fixtureReportsDir = projectDir.resolve("src/test/fixtures/Reports")
Path workDir = projectDir.resolve("build/tmp/Reports")
Files.createDirectories(workDir)
FileUtils.copyDirectory(fixtureReportsDir.toFile(), workDir.toFile())

