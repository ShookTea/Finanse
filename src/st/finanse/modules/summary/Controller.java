package st.finanse.modules.summary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;
import st.finanse.modules.finanse.MonthEntry;

public class Controller implements Updateable {
    @FXML private LineChart<String, Double> accountBilanse;
    @FXML private BarChart<String, Double> gainsLoss;

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        update();
    }

    @Override
    public void update() {
        createBilanseData();
        createGainsLossData();
        gainsLoss.setBarGap(1.0);
    }

    private void createBilanseData() {
        ObservableList<Series<String, Double>> result = FXCollections.observableArrayList();
        Series<String, Double> bilanse = new Series<>();
        bilanse.setName("Stan konta na koniec miesiąca");
        for (MonthEntry monthEntry : Project.PROJECT.FINANSE_MONTHS) {
            bilanse.getData().add(new Data(monthEntry.month.toString(), monthEntry.getCurrentAmount().toDouble()));
        }
        result.addAll(bilanse);
        accountBilanse.setData(result);
    }

    private void createGainsLossData() {
        ObservableList<Series<String, Double>> result = FXCollections.observableArrayList();
        Series<String, Double> gains = new Series<>();
        gains.setName("Przychody");
        Series<String, Double> losses = new Series<>();
        losses.setName("Wydatki");
        Series<String, Double> bilanse = new Series<>();
        bilanse.setName("Bilans");
        for (MonthEntry monthEntry : Project.PROJECT.FINANSE_MONTHS) {
            Amount gained = monthEntry.getEarnedAmount();
            Amount lost = monthEntry.getLostAmount();
            Amount diff = gained.subtract(lost);
            String monthName = monthEntry.month.toString();
            gains.getData().add(new Data(monthName, gained.toDouble()));
            losses.getData().add(new Data(monthName, lost.toDouble()));
            bilanse.getData().add(new Data(monthName, diff.toDouble()));
        }
        result.addAll(gains, losses, bilanse);
        gainsLoss.setData(result);
    }
}
