package me.brandonyb.jazzsnazz.util;

import java.util.Locale;

/**
 * <p>Contains operating system information about the running computer.</p>
 * @author brandon
 */
public final class Analyser {
	private String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
	private String javaVersion = System.getProperty("java.version").toLowerCase(Locale.ENGLISH);
	
	/**
	 * <p>Determines the operating system running on the computer.</p>
	 * @return The operating system
	 */
	public OS getOS() {
		if ((os.indexOf("mac") >= 0) || (os.indexOf("darwin") >= 0)) {
			return OS.MACOS;
		} else if (os.indexOf("win") >= 0) {
			return OS.WINDOWS;
		} else if (os.indexOf("nux") >= 0) {
			return OS.LINUX;
		} else {
			return OS.OTHER;
		}
	}
	
	/**
	 * <p>Determines the major Java version.</p>
	 * @return The major Java version
	 */
	public int getJavaVersion() {
		if (javaVersion.startsWith("1.")) {
			javaVersion = javaVersion.substring(2, 3);
		} else {
			int dot = javaVersion.indexOf(".");
			
			if (dot != -1) {
				javaVersion = javaVersion.substring(0, dot);
			}
		}
		
		return Integer.parseInt(javaVersion);
	}
}
