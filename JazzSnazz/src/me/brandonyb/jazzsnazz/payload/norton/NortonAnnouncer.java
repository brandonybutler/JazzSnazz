package me.brandonyb.jazzsnazz.payload.norton;

import java.io.IOException;

import me.brandonyb.jazzsnazz.util.Analyser;
import me.brandonyb.jazzsnazz.util.EntryType;
import me.brandonyb.jazzsnazz.util.Logging;
import me.brandonyb.jazzsnazz.util.OS;

/**
 * <p>The thread that voices the "Virus alert" message continuously.</p>
 * @author brandon
 */
public final class NortonAnnouncer extends Thread {
	@Override
	public void run() {
		Logging logging = new Logging();
        logging.addEntry(EntryType.SUCCESS, "The Norton announcer thread has started.");
		
		while (true) {
			// Attempt to sleep the thread
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				return;
			}
			
			// Verify that the operating system is Windows because it needs to support Visual Basic Script
			Analyser analyser = new Analyser();
					
			if (analyser.getOS() == OS.WINDOWS) {
				try {
					logging.addEntry(EntryType.INFORMATION, "Attempting to speak now.");	
					Runtime.getRuntime().exec("cscript " + System.getProperty("user.dir") + "\\scripts\\speaker.vbs \"Virus detected\"");
				} catch (IOException ex) {
					logging.addEntry(EntryType.WARNING, "Unable to speak.");
				}
			} else {
				break;
			}
		}
	}
}
