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
    private void initialize() {}

    @FXML
    public void exit() {
        boolean reallyExit = !Project.PROJECT.isSaveRequired();
        if (!reallyExit) {
            Optional<ButtonType> buttonType = Start.showConfirmationAlert("Zamykanie", "Czy chcesz zapisać dane przed zamknięciem programu?");
            if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
                saveFile();
                reallyExit = true;
            }
            else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.NO) {
                reallyExit = true;
            }
            else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                return;
            }
        }
        if (reallyExit) {
            System.exit(0);
        }
    }

    @FXML
    private void loadFile() {
        Project.tryLoadingProject();
    }

    @FXML
    private void newFile() {
        Project.tryCreatingNewProject();
    }

    @FXML
    private void saveFile() {
        Project.trySavingProject(false);
    }

    @FXML
    private void saveFileAs() {
        Project.trySavingProject(true);
    }
}
