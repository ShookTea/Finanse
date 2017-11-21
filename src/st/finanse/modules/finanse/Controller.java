package st.finanse.modules.finanse;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;

import java.util.*;

public class Controller implements Updateable {
    @FXML private TreeView<String> monthTree;
    @FXML private Label monthTitle;
    @FXML private Label monthTitleInForm;
    @FXML private Label endingAmount;
    @FXML private Label startAmount;
    @FXML private Label monthGains;
    @FXML private Label monthLoses;
    @FXML private Label bilanse;
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

    @Override
    public void update() {
        reloadTree();
        reloadForm();
    }

    @FXML
    private void addEntry() {
        boolean markRed = isHoliday.isSelected();
        int day = entryDay.getValue();
        String title = entryTitle.getText();
        Amount amount = new Amount(entryAmount.getText());

        entryTitle.setText("");
        entryAmount.setText("");
        Entry entry = new Entry(title, day, amount, markRed, currentEntry.get());
        currentEntry.get().getEntries().add(entry);
        MainWindowController.updateAll();
        entryTitle.requestFocus();
    }

    @FXML
    private void monthClosed(ActionEvent event) {
        MonthEntry me = currentEntry.get();
        Amount endingAmount = me.getCurrentAmount();
        Month nextMonth = me.month.getNextMonth();
        me.close();
        MonthEntry newEntry = new MonthEntry(nextMonth, endingAmount, false);
        Project.PROJECT.FINANSE_MONTHS.add(newEntry);
        currentEntry.set(newEntry);
        MainWindowController.updateAll();
    }

    private void monthChosen(Observable observable) {
        ReadOnlyObjectProperty<TreeItem> robp = (ReadOnlyObjectProperty) observable;
        TreeItem selected = robp.get();
        if (selected != null && selected.isLeaf()) {
            String monthName = selected.getValue().toString();
            int year = Integer.parseInt(selected.getParent().getValue().toString().substring(4));
            Month m = new Month(year, monthName);
            currentEntry.set(Project.PROJECT.getEntryByMonth(m));
        }
    }

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        currentEntry.addListener(e -> reloadForm());
        reloadTree();
        table.setFixedCellSize(25.0);
        table.setPlaceholder(new Label("Brak danych w tabeli"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        deleteColumn.setCellFactory(createRemoveButton());
        entryDay.valueProperty().addListener((a, b, c) -> checkHoliday(c));
        entryAmount.setTextFormatter(Amount.createAmountFormatter());
    }

    private void checkHoliday(int day) {
        if (currentEntry.get() == null) return;
        Month m = currentEntry.get().month;
        isHoliday.setSelected(m.isSunday(day));
    }

    private void reloadForm() {
        MonthEntry monthEntry = currentEntry.get();
        boolean locked = monthEntry == null ? true : monthEntry.isClosed();
        setFormDisabled(locked);
        deleteColumn.setVisible(!locked);

        Month month = monthEntry == null ? new Month(2000, 1) : monthEntry.month;
        monthTitle.setText(month.getMonthName() + " " + month.getYear());
        monthTitleInForm.setText(month.getMonthAccusative() + " " + month.getYear());
        table.getItems().clear();
        int maxDays = 30;
        int defaultDay = 1;
        if (monthEntry != null) {
            table.getItems().addAll(monthEntry.getEntries().sorted(Comparator.comparingInt(Entry::getDay)));
            maxDays = month.getMaxDays();
            if (monthEntry.getEntries().size() == 0) {
                defaultDay = 1;
            }
            else {
                defaultDay = monthEntry.getEntries().sorted(Comparator.comparingInt(Entry::getDay).reversed()).get(0).getDay();
                if (defaultDay > maxDays) defaultDay = maxDays;
            }
            startAmount.setText("Kwota początkowa: " + monthEntry.startingAmount.toFormattedString());
            endingAmount.setText("Kwota " + (monthEntry.isClosed() ? "końcowa" : "aktualna") + ": " + monthEntry.getCurrentAmount().toFormattedString());
            monthGains.setText("Przychody: " + monthEntry.getEarnedAmount().toFormattedString());
            monthLoses.setText("Wydatki: " + monthEntry.getLostAmount().toFormattedString());
            Amount bilanseAmount = monthEntry.getEarnedAmount().subtract(monthEntry.getLostAmount());
            bilanse.setText("Bilans: " + bilanseAmount.toFormattedString());
            switch (bilanseAmount.sign()) {
                case -1: bilanse.setTextFill(monthLoses.getTextFill()); break;
                case 1:  bilanse.setTextFill(monthGains.getTextFill()); break;
                default: bilanse.setTextFill(Paint.valueOf("#000000"));
            }
        }
        else {
            table.getItems().clear();
            endingAmount.setText("Kwota aktualna: ---");
            startAmount.setText("Kwota początkowa: ---");
            monthGains.setText("Przychody: ---");
            monthLoses.setText("Wydatki: ---");
            bilanse.setText("Bilans: ---");
        }
        entryDay.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, maxDays, defaultDay));
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

    private Callback<TableColumn<Entry, String>, TableCell<Entry, String>> createRemoveButton() {
        Callback<TableColumn<Entry, String>, TableCell<Entry, String>> ret = new Callback<TableColumn<Entry, String>, TableCell<Entry, String>>() {
            @Override
            public TableCell call(final TableColumn<Entry, String> param) {
                final TableCell<Entry, String> cell = new TableCell<Entry, String>() {
                    final Button btn = new Button("Usuń");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Entry person = getTableView().getItems().get(getIndex());
                                currentEntry.get().getEntries().remove(person);
                                MainWindowController.updateAll();
                            });
                            btn.setPrefHeight(USE_COMPUTED_SIZE);
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        return ret;
    }
}
