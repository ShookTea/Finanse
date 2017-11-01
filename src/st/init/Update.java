package st.init;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Update extends Application {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel()); //NimbusLAF
        if (args.length > 0) {
            toOpen = new File(args[0]);
        }
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }

    private static File toOpen = null;
}
