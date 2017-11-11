package st.finanse.modules.regular;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;

public class Controller implements Updateable {

    @FXML private ListView<String> regularList;
    @FXML private TableView<String> entryTable;
    @FXML private Label title;

    @FXML private DatePicker entryDate;
    @FXML private TextField amount;
    @FXML private CheckBox isPayed;
    @FXML private DatePicker paymentDate;

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        update();
    }

    @FXML
    private void createEntry() {

    }

    @FXML
    private void createRegularPayment() {

    }

    @Override
    public void update() {

    }
}
