/*
 * Copyright 2019 brandon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.brandonyb.jazzsnazz;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import me.brandonyb.jazzsnazz.exceptions.PayloadActivityException;
import me.brandonyb.jazzsnazz.payload.PayloadList;
import me.brandonyb.jazzsnazz.payload.PayloadManager;
import me.brandonyb.jazzsnazz.util.Analyser;
import me.brandonyb.jazzsnazz.util.EntryType;
import me.brandonyb.jazzsnazz.util.Logging;
import me.brandonyb.jazzsnazz.util.OS;

/**
 * <p>The main application class.</p>
 * @author brandon
 */
public class Initialize {
    /**
     * <p>The entry point of the application.</p>
     * @param args The command line arguments
     */
    public static void main(final String[] args) {
    	// Set the Java security policy location and enable the security manager
    	System.setProperty("java.security.policy", System.getProperty("user.dir") + "\\appsecurity.policy");
    	System.setSecurityManager(new SecurityManager());
    	
        // Launch the 1st payload and log that the application was started.
        Logging logging = new Logging();
        logging.addEntry(EntryType.SUCCESS, "Application started.");
        
        Analyser analyser = new Analyser();
        
        if (!(analyser.getOS() == OS.WINDOWS)) {
        	// Display a warning if the OS version is not Windows.
        	logging.addEntry(EntryType.INFORMATION, "Running this application on Windows is recommended for most support.");
        } else if (!(analyser.getJavaVersion() == 8)) {
        	// Display an error if the Java version is not 8.
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Java version error");
        	alert.setHeaderText("Java version is incorrect");
        	alert.setContentText("The Java SE Runtime 8 is required to run this application. Specifically, it is recommended that you use Java SE 8 Update 212 to run this application.");
        	
        	alert.showAndWait();
        }
        
        PayloadManager manager = new PayloadManager();
        
        try {
			manager.initialize(PayloadList.NORTON_NOTIFICATION);
		} catch (PayloadActivityException ex) {
			System.exit(0);
		}
    }
}
