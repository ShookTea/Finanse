package st.finanse.modules.summary;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import st.finanse.Project;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;
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
            Tab tab = new Tab(group.getTabLabel());
            tabPane.getTabs().add(tab);
        }
    }

    private SummaryGroupCollection getGroups() {
        SummaryGroupCollection groups = new SummaryGroupCollection();

        for (MonthEntry entry : Project.PROJECT.finance.getMonthEntries()) {
            SummaryGroup group = groups.getByMonth(entry.month);
        }

        return groups;
    }
}
