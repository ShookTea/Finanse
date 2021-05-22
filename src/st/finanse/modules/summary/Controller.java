package st.finanse.modules.summary;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import st.finanse.Project;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;
import st.finanse.modules.finance.Entry;
import st.finanse.modules.finance.MonthEntry;

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

        TableColumn<SummaryEntry, String> entryNameColumn = new TableColumn<>("Wpis");
        entryNameColumn.setCellValueFactory(val -> val.getValue().nameProperty());
        tableView.getColumns().add(entryNameColumn);

        tableView.getItems().addAll(summaryGroup.getEntries());
        return tableView;
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

        return groups;
    }
}
