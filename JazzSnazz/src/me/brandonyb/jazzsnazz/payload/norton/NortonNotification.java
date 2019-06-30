package me.brandonyb.jazzsnazz.payload.norton;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import me.brandonyb.jazzsnazz.payload.Payload;
import me.brandonyb.jazzsnazz.util.EntryType;
import me.brandonyb.jazzsnazz.util.Logging;

public final class NortonNotification extends Application implements Payload {
	// Determines whether the payload is active
	private boolean active = false;
	
    /**
     * <p>Initializes the fake Norton virus notification.</p>
     * @param baseStage The JavaFX stage.
     * @throws Exception 
     */
    @Override
    public void start(final Stage baseStage) throws Exception {
    	// Create the base stage
    	baseStage.initStyle(StageStyle.UTILITY);
    	baseStage.setOpacity(0);
    	baseStage.setHeight(0);
    	baseStage.setWidth(0);
    	baseStage.show();
    	
        // Load the main stage
    	Stage mainStage = new Stage();
        mainStage.setTitle("Norton Internet Security");
        mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.initOwner(baseStage);
        mainStage.setAlwaysOnTop(true);
        
        Parent root = FXMLLoader.load(this.getClass().getResource("/me/brandonyb/jazzsnazz/views/FakePopup.fxml"));
        
        // Start the fade-in transition
        FadeTransition fade = new FadeTransition(Duration.millis(3000), root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        
        // Create the scene
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        
        // Position the window near the bottom-right corner of the primary screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        // (screenBounds.getMinX() + screenBounds.getWidth() - 50) denotes that we should position the
        // notification window at the bottom-right hand corner of the screen, but position it 50 px away
        // from the absolute corner
        mainStage.setX((screenBounds.getMinX() + screenBounds.getWidth() - 50) - 290);
        mainStage.setY((screenBounds.getMinY() + screenBounds.getHeight() - 50) - 162);
        mainStage.setWidth(290);
        mainStage.setHeight(162);
        
        // Show the window
        mainStage.show();
    }
    
    // Called when the payload is started
	@Override
	public void initialize() {
		// Set active to true
		active = true;
		
		// Launch the JavaFX application and inform the logger
		Logging logging = new Logging();
        logging.addEntry(EntryType.SUCCESS, "NORTON_NOTIFICATION payload has been started.");
        
        // Start the announcer
        NortonAnnouncer announcer = new NortonAnnouncer();
        announcer.start();
        
        // Start the alarm
        Alarm alarm = new Alarm();
        alarm.start();
        
        // Start the sound player
        Sound sound = new Sound();
        sound.start();
        
        // Start the screen inverter
        ScreenInverter inverter = new ScreenInverter();
        inverter.start();
        
		super.launch();
	}

	// Called when the payload is terminated
	@Override
	public void cease() {
		// Set active to false
		active = false;
		
		// Stop the announcer
        NortonAnnouncer announcer = new NortonAnnouncer();
        
        try {
			announcer.join();
		} catch (InterruptedException ex) {
			announcer.interrupt();
		}
        
        // Stop the alarm
        Alarm alarm = new Alarm();
        
        try {
        	alarm.join();
		} catch (InterruptedException ex) {
		 	alarm.interrupt();
		}
        
        // Stop the sound
        Sound sound = new Sound();
        
        try {
        	sound.join();
		} catch (InterruptedException ex) {
		 	sound.interrupt();
		}
        
        // Stop the inverter
        ScreenInverter inverter = new ScreenInverter();
        
        try {
        	inverter.join();
		} catch (InterruptedException ex) {
		 	inverter.interrupt();
		}
        
		// Exit the JavaFX application and perform a JVM garbage collection
		Platform.exit();
		System.gc();
		
		Logging logging = new Logging();
        logging.addEntry(EntryType.SUCCESS, "The NORTON_NOTIFICATION payload has finished cleaning up.");
	}

	// Returns whether or not the payload is active
	@Override
	public boolean isActive() {
		return this.active;
	}
}
