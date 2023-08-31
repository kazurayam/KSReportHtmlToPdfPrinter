import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import org.apache.commons.io.FileUtils

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.SetupTestCase
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.annotation.TearDownTestCase
import com.kms.katalon.core.configuration.RunConfiguration

/**
 * Test Suites/runAll
 */

/**
 * Setup test suite environment.
 */
@SetUp(skipped = false)
def setUp() {
	Path projectDir = Paths.get(RunConfiguration.getProjectDir())
	Path tmpDir = projectDir.resolve("build/tmp")
	if (Files.exists(tmpDir)) {
		FileUtils.deleteDirectory(tmpDir.toFile())
	}
	Files.createDirectories(tmpDir)
}

/**
 * Clean test suites environment.
 */
@TearDown(skipped = true)
def tearDown() {
	//
}

/**
 * Run before each test case starts.
 */
@SetupTestCase(skipped = true) //
def setupTestCase() {
	//
}

/**
 * Run after each test case ends.
 */
@TearDownTestCase(skipped = true)
def tearDownTestCase() {
	//
}
