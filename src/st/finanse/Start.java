package st.finanse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import st.finanse.gui.MainWindowController;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class Start extends Application {

    public static void main(String[] args) throws Exception {
        Project.createNewProject();
        if (args.length > 0) {
            File toOpen = new File(args[0]);
            Project.loadProject(toOpen);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        STAGE = primaryStage;
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
            Scanner scanner = new Scanner(Start.class.getResourceAsStream("/repository.strep"));
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

    public static Optional<ButtonType> showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType yes = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("Nie", ButtonBar.ButtonData.NO);
        ButtonType cancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no, cancel);
        return alert.showAndWait();
    }

    public static File displayOpenDialogFileChooser() {
        return createFileChooser().showOpenDialog(STAGE);
    }

    public static File displaySaveDialogFileChooser() {
        return createFileChooser().showSaveDialog(STAGE);
    }

    private static FileChooser createFileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Wybierz plik");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Wszystkie pliki programu Finanse", "*.fns", "*.fnsx"),
                new FileChooser.ExtensionFilter("Pliki FNS programu Finanse", "*.fns"),
                new FileChooser.ExtensionFilter("Pliki FNSX programu Finanse", "*.fnsx")
        );
        return fc;
    }

    private static String version = "";
    private static Stage STAGE = null;
}