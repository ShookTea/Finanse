package st.finanse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import st.finanse.gui.MainWindowController;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

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
        FileChooser fc = createFileChooser();
        fc.setInitialFileName("*.fnsx");
        return fc.showSaveDialog(STAGE);
    }

    private static FileChooser createFileChooser() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Wybierz plik");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        List<String> combination = new ArrayList<>();
        combination.addAll(fnsExt);
        combination.addAll(fnsxExt);
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Wszystkie pliki programu Finanse", combination),
                new FileChooser.ExtensionFilter("Pliki FNS programu Finanse", fnsExt),
                new FileChooser.ExtensionFilter("Pliki FNSX programu Finanse", fnsxExt)
        );
        return fc;
    }

    private static final List<String> fnsExt = Arrays.asList("*.fns", "*.fnS", "*.fNs", "*.fNS", "*.Fns", "*.FnS", "*.FNs", "*.FNS");
    private static final List<String> fnsxExt = Arrays.asList("*.fnsx", "*.fnSx", "*.fNsx", "*.fNSx", "*.Fnsx", "*.FnSx", "*.FNsx", "*.FNSx",
                                                                  "*.fnsX", "*.fnSX", "*.fNsX", "*.fNSX", "*.FnsX", "*.FnSX", "*.FNsX", "*.FNSX");


    public static void showExceptionAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Wystąpił błąd");
        alert.setContentText("Skopiuj treść poniżej i wyślij ją twórcy programu.");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label("Stos wyjątku:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    private static String version = "";
    private static Stage STAGE = null;
}