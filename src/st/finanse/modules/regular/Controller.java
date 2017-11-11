package st.finanse.modules.regular;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.BigDecimalStringConverter;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Controller implements Updateable {

    @FXML private ListView<String> regularList;
    @FXML private TableView<PaymentEntry> entryTable;
    @FXML private Label title;

    @FXML private DatePicker entryDate;
    @FXML private TextField amount;
    @FXML private CheckBox isPayed;
    @FXML private DatePicker paymentDate;
    @FXML private Button createEntry;

    @FXML private TableColumn<PaymentEntry, String> entryDateColumn;
    @FXML private TableColumn<PaymentEntry, String> paymentDateColumn;
    @FXML private TableColumn<PaymentEntry, String> amountColumn;

    private RegularPayment toDisplay = null;

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        entryDateColumn.setCellValueFactory(new PropertyValueFactory("entryDateString"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDateString"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amount.setTextFormatter(Amount.createAmountFormatter());
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
        updateRightPart();
    }

    @Override
    public void update() {
        updateList();
        updateRightPart();
    }

    private void updateList() {
        regularList.getItems().clear();
        Project.PROJECT.REGULAR_PAYMENTS.stream().forEach(r -> regularList.getItems().add(r.name));
    }

    private void updateRightPart() {
        entryTable.getItems().clear();
        setBlockedForm(toDisplay == null);
        if (toDisplay == null) {
            title.setText("Rachunki");
        }
        else {
            title.setText(toDisplay.name);
            updateTable();
        }
    }

    private void updateTable() {
        entryTable.getItems().addAll(toDisplay.getPayments());
    }

    private void setBlockedForm(boolean isBlocked) {
        entryDate.setDisable(isBlocked);
        paymentDate.setDisable(isBlocked);
        amount.setDisable(isBlocked);
        isPayed.setDisable(isBlocked);
        createEntry.setDisable(isBlocked);
    }
}
