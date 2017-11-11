package st.finanse.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import st.finanse.Project;
import st.finanse.Start;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainWindowController implements Updateable {

    public static final List<Updateable> UPDATEABLES = new ArrayList<>();

    @Override
    public void update() {
        for (Updateable up : UPDATEABLES) up.update();
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
        System.out.println("OPEN " + toOpen);
    }

    @FXML
    private void newFile() {
        Optional<ButtonType> buttonType = Start.showConfirmationAlert("Tworzenie nowego projektu", "Czy na pewno chcesz utworzyć nowy projekt? Stracisz wszystkie niezapisane zmiany.");
        if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
            Project.createNewProject();
            update();
        }
    }

    @FXML
    private void saveFile() {
        saveFileAs();
    }

    @FXML
    private void saveFileAs() {
        File toSave = Start.displaySaveDialogFileChooser();
        System.out.println("SAVE " + toSave);
    }
}
