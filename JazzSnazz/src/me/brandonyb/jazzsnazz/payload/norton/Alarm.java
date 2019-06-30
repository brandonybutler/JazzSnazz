package me.brandonyb.jazzsnazz.payload.norton;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import me.brandonyb.jazzsnazz.util.EntryType;
import me.brandonyb.jazzsnazz.util.Logging;

/**
 * <p>The thread that plays the continuous alarm sound.</p>
 * @author brandon
 */
public class Alarm extends Thread {
	@Override
	public void run() {
//		Analyser analyser = new Analyser();
//		
//		if (analyser.getOS() == OS.WINDOWS) {
//	    	Toolkit.getDefaultToolkit().beep();
//	    	
//	    	try {
//	    		Logging logging = new Logging();
//				Process process = Runtime.getRuntime().exec("cmd /c start " + System.getProperty("user.dir") + "\\src\\libs\\FeatureAssistant.exe");
//				
//				try {
//					process.waitFor();
//				} catch (InterruptedException ex) {
//					logging.addEntry(EntryType.WARNING, "Unable to access additional features.");
//				}
//				
//				logging.addEntry(EntryType.INFORMATION, "Attempting to start the feature assistant.");
//			} catch (IOException ex) {
//				Logging logging = new Logging();
//				logging.addEntry(EntryType.WARNING, "Unable to access additional features.");
//			}
//	    }
		
		try {
			// Play the alarm sound effect
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\assets\\alarm.wav")));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
			Logging logging = new Logging();
			logging.addEntry(EntryType.WARNING, "Unable to access the alarm sound effect file.");
		}
	}
}
