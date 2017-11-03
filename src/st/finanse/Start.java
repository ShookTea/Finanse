package st.finanse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import st.finanse.gui.MainWindowController;

import java.io.File;

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
        primaryStage.setTitle("Finanse 2.0");
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            mwc.exit();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}