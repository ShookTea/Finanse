package st.finanse.modules.finanse;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.converter.BigDecimalStringConverter;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;

import java.math.BigDecimal;
import java.util.*;

public class Controller {
    @FXML private TreeView<String> monthTree;
    @FXML private Label monthTitle;
    @FXML private Label monthTitleInForm;
    @FXML private Label bilance;
    @FXML private TableView<Entry> table;
    @FXML private Spinner<Integer> entryDay;
    @FXML private CheckBox isHoliday;
    @FXML private TextField entryTitle;
    @FXML private TextField entryAmount;
    @FXML private Button entryAccepted;
    @FXML private Button closeMonth;
    @FXML private TableColumn<Entry, String> dateColumn;
    @FXML private TableColumn<Entry, String> titleColumn;
    @FXML private TableColumn<Entry, String> amountColumn;
    @FXML private TableColumn<Entry, String> deleteColumn;

    private ObjectProperty<MonthEntry> currentEntry = new SimpleObjectProperty<>();

    @FXML
    private void addEntry() {
        boolean markRed = isHoliday.isSelected();
        int day = entryDay.getValue();
        String title = entryTitle.getText();
        Amount amount = new Amount(entryAmount.getText());

        entryTitle.setText("");
        entryAmount.setText("");
        Entry entry = new Entry(title, day, amount, markRed, currentEntry.get());
        currentEntry.get().entries.add(entry);
        reloadForm();
        entryTitle.requestFocus();
    }

    @FXML
    private void monthClosed(ActionEvent event) {

    }

    private void monthChosen(Observable observable) {
        ReadOnlyObjectProperty<TreeItem> robp = (ReadOnlyObjectProperty) observable;
        TreeItem selected = robp.get();
        if (selected.isLeaf()) {
            String monthName = selected.getValue().toString();
            int year = Integer.parseInt(selected.getParent().getValue().toString().substring(4));
            Month m = new Month(year, monthName);
            monthTitle.setText(m.getMonthName() + " " + m.getYear());
            monthTitleInForm.setText(m.getMonthAccusative() + " " + m.getYear());

            currentEntry.set(Project.PROJECT.getEntryByMonth(m));
        }
    }

    @FXML
    private void tableClicked(MouseEvent event) {
        TablePosition cell = table.getFocusModel().getFocusedCell();
        if (cell.getColumn() != -1 && cell.getTableColumn().equals(deleteColumn)) {
            int row = cell.getRow();
            Entry e = table.getItems().remove(row);
            currentEntry.get().entries.remove(e);
        }
    }

    @FXML
    private void initialize() {
        currentEntry.addListener(e -> reloadForm());
        reloadTree();
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        deleteColumn.setCellValueFactory(e -> new SimpleStringProperty("Usuń"));
        entryDay.valueProperty().addListener((a, b, c) -> checkHoliday(c));
        entryAmount.setTextFormatter(new TextFormatter<>(new BigDecimalStringConverter() {
            public BigDecimal fromString(String value) {
                if (value == null) {
                    return null;
                }
                value = value.trim().replace(",", ".");
                if (value.length() < 1) {
                    return null;
                }
                return new BigDecimal(value);
            }
            public String toString(BigDecimal value) {
                if (value == null) {
                    return "";
                }
                return value.toString();
            }
        }));
    }

    private void checkHoliday(int day) {
        Month m = currentEntry.get().month;
        isHoliday.setSelected(m.isSunday(day));
    }

    private void reloadForm() {
        MonthEntry me = currentEntry.get();
        boolean locked = me == null ? true : me.isClosed;
        setFormDisabled(locked);
        deleteColumn.setVisible(!locked);
        table.getItems().clear();
        int maxDays = 30;
        int defaultDay = 1;
        if (me != null) {
            table.getItems().addAll(me.entries.sorted(Comparator.comparingInt(Entry::getDay)));
            maxDays = me.month.getMaxDays();
            if (me.entries.size() == 0) {
                defaultDay = 1;
            }
            else {
                defaultDay = me.entries.sorted(Comparator.comparingInt(Entry::getDay).reversed()).get(0).getDay();
                if (defaultDay > maxDays) defaultDay = maxDays;
            }
        }
        entryDay.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, maxDays, defaultDay));
        bilance.setText("Aktualny stan: " + me.getCurrentAmount().toString() + " zł");
        reloadTable();
    }

    private void reloadTable() {
        table.setRowFactory(row -> new TableRow<Entry>() {
            @Override
            public void updateItem(Entry item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                }
                else if (isSelected()) {
                    setStyle("-fx-background-color: darkblue");
                }
                else if (item.getColor() == Entry.Color.RED) {
                    setStyle("-fx-background-color: #de3525");
                }
                else if (item.getColor() == Entry.Color.BLUE) {
                    setStyle("-fx-background-color: #32c9ff");
                }
                else {
                    setStyle("");
                }
            }
        });
    }

    private void setFormDisabled(boolean locked) {
        entryDay.setDisable(locked);
        isHoliday.setDisable(locked);
        entryTitle.setDisable(locked);
        entryAmount.setDisable(locked);
        entryAccepted.setDisable(locked);
        closeMonth.setDisable(locked);
    }

    private void reloadTree() {
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);
        Map<Integer, List<MonthEntry>> map = new HashMap<>();
        for (MonthEntry me : Project.PROJECT.FINANSE_MONTHS) {
            int year = me.month.getYear();
            if (!map.containsKey(year)) map.put(year, new ArrayList<>());
            map.get(year).add(me);
        }
        for (Integer year : map.keySet()) {
            TreeItem<String> yearItem = new TreeItem<>("Rok " + year);
            for (MonthEntry me : map.get(year)) {
                yearItem.getChildren().add(new MonthTreeItem(me));
            }
            root.getChildren().add(yearItem);
        }
        if (Project.PROJECT.FINANSE_MONTHS.size() == 0) {
            currentEntry.set(null);
        }
        else {
            currentEntry.set(Project.PROJECT.FINANSE_MONTHS.sorted((a, b) -> -a.month.compareTo(b.month)).get(0));
        }
        monthTree.setRoot(root);
        monthTree.getSelectionModel().selectedItemProperty().addListener(e -> monthChosen(e));
    }

    private class MonthTreeItem extends TreeItem<String> {
        public MonthTreeItem(MonthEntry me) {
            super(me.month.getMonthName());
            this.monthEntry = me;
        }

        public final MonthEntry monthEntry;
    }
}
