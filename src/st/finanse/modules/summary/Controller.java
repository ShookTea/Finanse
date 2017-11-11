package st.finanse.modules.summary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;
import st.finanse.modules.finanse.MonthEntry;

public class Controller implements Updateable {
    @FXML private LineChart<String, Double> accountBilanse;

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        update();
    }

    @Override
    public void update() {
        accountBilanse.setData(createBilanseData());
    }

    private ObservableList<Series<String, Double>> createBilanseData() {
        ObservableList<Series<String, Double>> result = FXCollections.observableArrayList();
        Series<String, Double> bilanse = new Series<>();
        bilanse.setName("Stan konta na koniec miesiąca");
        for (MonthEntry monthEntry : Project.PROJECT.FINANSE_MONTHS) {
            bilanse.getData().add(new Data(monthEntry.month.toString(), monthEntry.getCurrentAmount().toDouble()));
        }
        result.addAll(bilanse);
        return result;
    }
}
