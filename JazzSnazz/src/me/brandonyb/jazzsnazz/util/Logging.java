package me.brandonyb.jazzsnazz.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>Relates to creating and managing the application logs for the program.</p>
 * @author brandon
 */
public final class Logging {
    // Refers to the file system location of the log file
    private String logLocation;
    
    /**
     * <p>Initializes the application log.</p>
     * @param path
     * @throws java.io.IOException
     */
    @SuppressWarnings({"unused", "resource"})
	public void initializeLog(final String path) throws IOException {
        // Gets the current date and time
        String currentDateTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime());
        
        // Format the file name as path/log-datetime.txt
        String filename = String.format("%s/log-%s.txt", path, currentDateTime);
        
        // Set the log location variable as the new filename
        this.logLocation = filename;
        
        // Create the file
        this.writeToLog("--- LOG BEGINS BELOW THIS LINE ---");
        
        // Get read/write access to the log file and attempt to lock it
        File file = new File(filename);
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = channel.lock();
            
        try {
            lock = channel.tryLock();
        } catch (OverlappingFileLockException | IOException ex) {
            this.addEntry(EntryType.ERROR, "Failed to lock the log file!");
        }
    }
    
    /**
     * <p>Writes a new line to the log.</p>
     * @param message The message that you wish to write.
     */
    private void writeToLog(final String message) throws IOException {
       if (this.getLogLocation() == null) {
          throw new IOException();
       } else {
          // Opens a stream to the file and writes the desired message to it.
          try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getLogLocation()), "utf-8"))) {
              writer.write(message + "\n");
          }
       }
    }
    
    /**
     * <p>Returns the full file system path of the log file's location.</p>
     * @return A file system path.
     */
    private String getLogLocation() {
        return this.logLocation;
    }
    
    /**
     * <p>Records a new entry into the application logs.</p>
     * @param level The severity of the entry.
     * @param message The log entry message.
     */
    public void addEntry(final EntryType level, final String message) {
        // Gets the current date and time
        String currentDateTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime());
        
        switch (level) {
            // Determines the level of severity specified for the entry
            case CRITICAL:
                // Create a new entry in the log and format it to contain the date and time when
                // it was recorded and the message specified
                System.out.printf("[%s] [CRITICAL]: %s%n", currentDateTime, message);
                
                // Attempt to write the line to the log, but if it doesn't exist it'll just continue on.
                try {
                    this.writeToLog(String.format("[%s] [CRITICAL]: %s%n", currentDateTime, message));
                } catch (IOException ex) {
                }

                break;
            case ERROR:
                // Create a new entry in the log and format it to contain the date and time when
                // it was recorded and the message specified
                System.out.printf("[%s] [ERROR]: %s%n", currentDateTime, message);
                
                // Attempt to write the line to the log, but if it doesn't exist it'll just continue on.
                try {
                    this.writeToLog(String.format("[%s] [ERROR]: %s%n", currentDateTime, message));
                } catch (IOException ex) {
                }
                
                break;
            case WARNING:
                // Create a new entry in the log and format it to contain the date and time when
                // it was recorded and the message specified
                System.out.printf("[%s] [WARNING]: %s%n", currentDateTime, message);
                
                // Attempt to write the line to the log, but if it doesn't exist it'll just continue on.
                try {
                    this.writeToLog(String.format("[%s] [WARNING]: %s%n", currentDateTime, message));
                } catch (IOException ex) {
                }
                
                break;
            case INFORMATION:
                // Create a new entry in the log and format it to contain the date and time when
                // it was recorded and the message specified
                System.out.printf("[%s] [INFORMATION]: %s%n", currentDateTime, message);
                
                // Attempt to write the line to the log, but if it doesn't exist it'll just continue on.
                try {
                    this.writeToLog(String.format("[%s] [INFORMATION]: %s%n", currentDateTime, message));
                } catch (IOException ex) {
                }
                
                break;
            case SUCCESS:
                // Create a new entry in the log and format it to contain the date and time when
                // it was recorded and the message specified
                System.out.printf("[%s] [SUCCESS]: %s%n", currentDateTime, message);
                
                // Attempt to write the line to the log, but if it doesn't exist it'll just continue on.
                try {
                    this.writeToLog(String.format("[%s] [SUCCESS]: %s%n", currentDateTime, message));
                } catch (IOException ex) {
                }
                
                break;
            default:
                // If for some reason an invalid parameter was specified, it will throw an
                // IllegalArgumentException.
                throw new IllegalArgumentException("An invalid entry type was specified.");
        }
    }
}