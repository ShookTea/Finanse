package st.finanse.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import st.finanse.Project;
import st.finanse.Start;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainWindowController implements Updateable {

    public static final List<Updateable> UPDATEABLES = new ArrayList<>();

    public static void updateAll() {
        for (Updateable up : UPDATEABLES) up.update();
    }

    @Override
    public void update() {
        updateAll();
    }

    @FXML
    private void initialize() {

    }

    @FXML
    public void exit() {
        Optional<ButtonType> buttonType = Start.showConfirmationAlert("Zamykanie", "Czy chcesz zapisać dane przed zamknięciem programu?");
        if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
            saveFile();
        }
        else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            return;
        }
        System.exit(0);
    }

    @FXML
    private void loadFile() {
        Optional<ButtonType> buttonType = Start.showConfirmationAlert("Wczytywanie", "Czy chcesz zapisać dane przed wczytaniem pliku?");
        if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
            saveFile();
        }
        else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            return;
        }

        File toOpen = Start.displayOpenDialogFileChooser();
        if (toOpen != null) {
            try {
                Project.loadProject(toOpen);
                update();
            } catch (IOException e) {
                Start.showExceptionAlert(e);
            }
        }
    }

    @FXML
    private void newFile() {
        Project.tryCreatingNewProject();
    }

    @FXML
    private void saveFile() {
        if (Project.PROJECT.file == null) {
            chooseFileToSave();
        }
        trySave();
    }

    @FXML
    private void saveFileAs() {
        chooseFileToSave();
        trySave();
    }

    private void chooseFileToSave() {
        File toSave = Start.displaySaveDialogFileChooser();
        if (toSave != null) {
            Project.PROJECT.file = toSave;
        }
    }

    private void trySave() {
        try {
            Project.saveProject(Project.PROJECT.file);
        } catch (IOException e) {
            Start.showExceptionAlert(e);
        }
    }
}
