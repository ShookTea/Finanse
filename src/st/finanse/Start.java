package st.finanse;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.File;

public class Start extends Application {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
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
