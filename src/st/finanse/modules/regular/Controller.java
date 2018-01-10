package st.finanse.modules.regular;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;

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
    private RegularData regular = Project.PROJECT.regular;

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
        LocalDate entry = entryDate.getValue();
        LocalDate payment = paymentDate.getValue();
        Amount pay = new Amount(amount.getText());

        entryDate.setValue(null);
        paymentDate.setValue(null);
        amount.setText("");
        isPayed.setSelected(false);

        PaymentEntry pe = new PaymentEntry(toDisplay.name, pay, entry, payment);
        toDisplay.addPayment(pe);
        updateTable();
    }

    @FXML
    private void createRegularPayment() {
        TextInputDialog tid = new TextInputDialog("Rachunek za prąd");
        tid.setTitle("Dodawanie nowej płatności regularnej");
        tid.setHeaderText("Płatność regularna");
        tid.setContentText("Nazwa płatności:");
        tid.showAndWait().ifPresent(name -> {
            RegularPayment rp = new RegularPayment(name);
            regular.addRegularPayment(rp);
            updateList();
        });
    }

    @FXML
    private void selectPayment() {
        String item = regularList.getSelectionModel().getSelectedItem();
        toDisplay = regular.getRegularPaymentByName(item);
        updateRightPart();
    }

    @FXML
    private void selectedEntry() {
        int row = entryTable.getSelectionModel().getFocusedIndex();
        if (toDisplay == null || row < 0 || row >= toDisplay.getPayments().length) return;

        PaymentEntry entry = toDisplay.getPayments()[row];
        if (!entry.isPayed()) {
            entryDate.setValue(entry.getEntryDate());
            amount.setText(entry.getAmount().toUnformattedString());
            isPayed.setSelected(true);
            paymentDate.setValue(LocalDate.now());
        }

    }

    @Override
    public void update() {
        regular = Project.PROJECT.regular;
        updateList();
        updateRightPart();
    }

    private void updateList() {
        regularList.getItems().clear();
        regular.regularPaymentStream().forEach(r -> regularList.getItems().add(r.name));
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
        entryTable.getItems().setAll(toDisplay.getPayments());
    }

    private void setBlockedForm(boolean isBlocked) {
        entryDate.setDisable(isBlocked);
        amount.setDisable(isBlocked);
        isPayed.setDisable(isBlocked);
        createEntry.setDisable(isBlocked);
        if (isBlocked) {
            paymentDate.setDisable(true);
        }
        else {
            paymentDate.disableProperty().bind(isPayed.selectedProperty().not());
        }
    }
}
