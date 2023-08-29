package com.kazurayam.ks.reporting

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.util.regex.Pattern

public class ReportsDirectoryScanner {
	
	public static final String HTML_REPORT_FILENAME = /\d{8}_\d{6}.html/
	
	public static final String PDF_REPORT_FILENAME = /\d{8}_\d{6}.pdf/

	public List<Path> scanReportsDirectory(Path reportsDir) {
		Objects.requireNonNull(reportsDir)
		assert Files.exists(reportsDir)
		assert Files.isDirectory(reportsDir)
		List<Path> reportFolders = new ArrayList<Path>()
		Files.walkFileTree(reportsDir,
				new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						String fileName = file.getFileName().toString()
						if (fileName =~ HTML_REPORT_FILENAME) {
							reportFolders.add(file.getParent())
						}
						return FileVisitResult.CONTINUE;
					}
				})
		return reportFolders
	}

	public List<Path> findHtmlFiles(Path reportFolder) {
		Objects.requireNonNull(reportFolder)
		assert Files.exists(reportFolder)
		assert Files.isDirectory(reportFolder)
		List<Path> htmlFiles = new ArrayList<Path>()
		Files.walkFileTree(reportFolder,
				new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						String fileName = file.getFileName().toString()
						if (fileName =~ HTML_REPORT_FILENAME) {
							htmlFiles.add(file)
						}
						return FileVisitResult.CONTINUE;
					}
				})
		return htmlFiles
	}

	public Path findHtmlFile(Path reportFolder) {
		List<Path> htmlFiles = findHtmlFiles(reportFolder)
		if (htmlFiles.size() > 0) {
			return htmlFiles.get(0)
		} else {
			return null
		}
	}
}
