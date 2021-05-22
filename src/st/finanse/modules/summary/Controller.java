package st.finanse.modules.summary;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;

public class Controller implements Updateable {
    @FXML private TabPane tabPane;

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        update();
    }

    @Override
    public void update() {
        tabPane.getTabs().clear();
    }
}
