package com.kazurayam.ks.reporting

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

public class Jongleur {

	public int convertAllReportHtmlToPdf(Path reportsDir) {
		Objects.requireNonNull(reportsDir)
		assert Files.exists(reportsDir)
		assert Files.isDirectory(reportsDir)
		//
		ReportsDirectoryScanner scanner = new ReportsDirectoryScanner()
		List<Path> reportFolders = scanner.scanReportsDirectory(reportsDir)
		int numPass = 0;
		int numFail = 0;
		for (Path reportFolder : reportFolders) {
			List<Path> htmlFiles = scanner.findReportHtmlFiles(reportFolder)
			if (htmlFiles.size() > 0) {
				for (Path html : htmlFiles) {
					// modify HTML to expand collapsed div nodes
					ReportHtmlExpander expander = new ReportHtmlExpander()
					Path tempDir = Files.createTempDirectory(null)
					Path expanded = expander.expand(html, tempDir)
					if (expanded != null) {
						// overwrite the source html with the expanded one
						Files.copy(expanded, html, StandardCopyOption.REPLACE_EXISTING)
						// convert HTML to PDF
						HtmlToPdfPrinter printer = new HtmlToPdfPrinter()
						Path pdf = html.getParent().resolve(html.getParent().getFileName().toString() + ".pdf")
						printer.print(html, pdf)
						numPass += 1
					} else {
						numFail += 1
					}
				}
			} else {

			}
		}
		return numPass + numFail
	}
}
