package st.finanse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import st.finanse.gui.MainWindowController;

import java.io.File;
import java.util.Scanner;

public class Start extends Application {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            File toOpen = new File(args[0]);
            Project.loadProject(toOpen);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Start.class.getResource("/st/finanse/gui/MainWindow.fxml"));
        Scene scene = new Scene(loader.load());
        MainWindowController mwc = loader.getController();

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Finanse " + getVersion());
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            mwc.exit();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String getVersion() {
        if (version == null) {
            version = "";
        }
        if (version.isEmpty()) {
            Scanner scanner = new Scanner(Start.class.getResourceAsStream("/test.strep"));
            String result = "";
            String regex = "\"version\": \"[^\"]*\",";
            while (!result.matches(regex) && scanner.hasNextLine()) {
                result = scanner.nextLine().trim();
            }
            if (result.isEmpty()) {
                version = "2.0";
            }
            else {
                version = result.replaceAll("\"version\": \"([^\"]*)\",", "$1");
            }
        }
        return version;
    }

    private static String version = "";
}