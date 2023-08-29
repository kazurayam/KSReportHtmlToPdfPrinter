package com.kazurayam.ks.reporting

import java.nio.file.Path

import com.kazurayam.subprocessj.CommandLocator
import com.kazurayam.subprocessj.OSType

public class HtmlToPdfPrinter {

	public void print(Path html, Path pdf) {
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
}
