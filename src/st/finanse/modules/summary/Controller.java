package st.finanse.modules.summary;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;
import st.finanse.modules.finance.Entry;
import st.finanse.modules.finance.MonthEntry;

import java.util.Comparator;

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

        for (SummaryGroup group : getGroups()) {
            Tab tab = new Tab(group.getTabLabel(), createTableView(group));
            tabPane.getTabs().add(tab);
        }
    }

    private TableView<SummaryEntry> createTableView(SummaryGroup summaryGroup) {
        TableView<SummaryEntry> tableView = new TableView<>();
        tableView.getItems().addAll(summaryGroup.getEntries());
        tableView.setEditable(false);

        TableColumn<SummaryEntry, String> entryNameColumn = new TableColumn<>("Wpis");
        entryNameColumn.setCellValueFactory(val -> val.getValue().nameProperty());
        entryNameColumn.setComparator(getEntryComparator());
        tableView.getColumns().add(entryNameColumn);

        for (int i = 0; i < summaryGroup.getColumns().length; i++) {
            final int index = i;
            TableColumn<SummaryEntry, Amount> singleValueColumn = new TableColumn<>(summaryGroup.getColumns()[index]);
            singleValueColumn.setCellValueFactory(val -> val.getValue().valueOnIndexProperty(index));
            tableView.getColumns().add(singleValueColumn);
        }

        TableColumn<SummaryEntry, Amount> endingSum = new TableColumn<>("Razem");
        endingSum.setCellValueFactory(val -> val.getValue().sumValueProperty());
        tableView.getColumns().add(endingSum);

        return tableView;
    }

    private Comparator<String> getEntryComparator() {
        return (s1, s2) ->
                s1.replaceAll("[^A-Za-z0-9\\s]", "").compareToIgnoreCase(
                        s2.replaceAll("[^A-Za-z0-9\\s]", "")
                );
    }

    private SummaryGroupCollection getGroups() {
        SummaryGroupCollection groups = new SummaryGroupCollection();

        for (MonthEntry monthEntry : Project.PROJECT.finance.getMonthEntries()) {
            SummaryGroup group = groups.getByMonth(monthEntry.month);

            for (Entry entry : monthEntry.getEntries()) {
                group.getEntryByName(entry.getTitle())
                        .addAmount(monthEntry.month.getMonth() - 1, entry.getAmount());
            }
        }

        groups.buildDefaultGroup();
        return groups;
    }
}
