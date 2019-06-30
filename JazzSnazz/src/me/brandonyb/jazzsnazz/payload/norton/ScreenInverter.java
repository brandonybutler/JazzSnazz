package me.brandonyb.jazzsnazz.payload.norton;

import java.io.IOException;

import me.brandonyb.jazzsnazz.util.Analyser;
import me.brandonyb.jazzsnazz.util.EntryType;
import me.brandonyb.jazzsnazz.util.Logging;
import me.brandonyb.jazzsnazz.util.OS;

/**
 * <p>The thread that inverts the screen every 3 seconds.</p>
 * @author brandon
 */
public class ScreenInverter extends Thread {
	@Override
	public void run() {
		Logging logging = new Logging();
        logging.addEntry(EntryType.SUCCESS, "The screen inverter thread has started.");
		
		while (true) {
			// Attempt to sleep the thread
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				return;
			}
			
			// Verify that the operating system is Windows because it needs to support Visual C++
			// and Windows header files.
			Analyser analyser = new Analyser();
			
			if (analyser.getOS() == OS.WINDOWS) {
				try {
					logging.addEntry(EntryType.INFORMATION, "Attempting to invert the screen now.");	
					Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\scripts\\ScreenInvert.exe");
				} catch (IOException ex) {
					logging.addEntry(EntryType.WARNING, "Unable to invert the screen.");
				}
			} else {
				break;
			}
		}
	}
}
