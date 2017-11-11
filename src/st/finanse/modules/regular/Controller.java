package st.finanse.modules.regular;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import st.finanse.Project;
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
    @FXML private Button createEntry;

    private RegularPayment toDisplay = null;

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
        TextInputDialog tid = new TextInputDialog("Rachunek za prąd");
        tid.setTitle("Dodawanie nowej płatności regularnej");
        tid.setHeaderText("Płatność regularna");
        tid.setContentText("Nazwa płatności:");
        tid.showAndWait().ifPresent(name -> {
            RegularPayment rp = new RegularPayment(name);
            Project.PROJECT.REGULAR_PAYMENTS.addAll(rp);
            updateList();
        });
    }

    @FXML
    private void selectPayment() {
        String item = regularList.getSelectionModel().getSelectedItem();
        toDisplay = Project.PROJECT.getRegularPaymentByName(item);
        updateTable();
    }

    @Override
    public void update() {
        updateList();
        updateTable();
    }

    private void updateList() {
        regularList.getItems().clear();
        Project.PROJECT.REGULAR_PAYMENTS.stream().forEach(r -> regularList.getItems().add(r.name));
    }

    private void updateTable() {
        if (toDisplay == null) {
            setBlockedForm(true);
            title.setText("Rachunki");
            return;
        }
        setBlockedForm(false);
        title.setText(toDisplay.name);
    }

    private void setBlockedForm(boolean isBlocked) {
        entryDate.setDisable(isBlocked);
        paymentDate.setDisable(isBlocked);
        amount.setDisable(isBlocked);
        isPayed.setDisable(isBlocked);
        createEntry.setDisable(isBlocked);
    }
}
