package st.finanse.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import st.finanse.Project;
import st.finanse.Start;

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
        System.exit(0);
    }

    @FXML
    private void loadFile() {

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

    }

    @FXML
    private void saveFileAs() {

    }
}
