package com.kazurayam.ks.reporting

import java.nio.file.Path

import com.kazurayam.subprocessj.CommandLocator
import com.kazurayam.subprocessj.OSType
import com.kazurayam.subprocessj.Subprocess
import com.kazurayam.subprocessj.Subprocess.CompletedProcess

public class HtmlToPdfPrinter {

	public void print(Path html, Path pdf) {
		Subprocess.CompletedProcess cp;
		String chromeBinaryPath = getChromeBinaryPath()
		cp = new Subprocess().cwd(html.getParent().toFile())
				.run(Arrays.asList(
				chromeBinaryPath, "--headless", "--disable-gpu",
				"--print-to-pdf=" + pdf.toString(),
				html.toString()
				));
		assert cp.returncode() == 0
	}

	public static String getChromeBinaryPath() {
		if (OSType.isMac()) {
			return "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
		} else (OSType.isWindows() ) {
				CommandLocator.CommandLocatingResult cfr = CommandLocator.find("chrome");
				if (cfr.returncode() == 0) {
					return cfr.command()
				} else {
					throw new RuntimeException("could not find the path of the chrome command")
				}
			}
	}

	public static String resolvePdfFileName(Path html) {
		String
	}
}
