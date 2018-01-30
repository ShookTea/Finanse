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
import st.finanse.data.Month;
import st.finanse.gui.MainWindowController;
import st.finanse.gui.Updateable;
import st.finanse.modules.finance.FinanceData;
import st.finanse.modules.finance.MonthEntry;
import st.finanse.modules.regular.PaymentEntry;
import st.finanse.modules.regular.RegularPayment;

import java.util.*;

public class Controller implements Updateable {
    @FXML private LineChart<String, Double> accountBilanse;
    @FXML private LineChart<String, Double> regularPayings;
    @FXML private BarChart<String, Double> gainsLoss;

    private FinanceData finance;

    @FXML
    private void initialize() {
        MainWindowController.UPDATEABLES.add(this);
        update();
    }

    @Override
    public void update() {
        finance = Project.PROJECT.finance;
        createBilanseData();
        createGainsLossData();
        createRegularPayingsData();
        gainsLoss.setBarGap(1.0);
    }

    private void createRegularPayingsData() {
        ObservableList<Series<String, Double>> result = FXCollections.observableArrayList();
        RegularPayment[] payments = Project.PROJECT.regular.getRegularPayments();
        Series<String, Double>[] series = new Series[payments.length];
        List<Month> months = new ArrayList<>();
        Arrays.stream(payments)
                .map(p -> p.getPayments())
                .forEach(pes -> Arrays.stream(pes)
                    .forEach(pe -> months.add(new Month(pe.getPaymentDate())))
                );
        months.sort(Month::compareTo);

        for (int i = 0; i < payments.length; i++) {
            series[i] = new Series<>();
            series[i].setName(payments[i].name);
        }

        for (Month month : months) {
            for (int i = 0; i < payments.length; i++) {
                RegularPayment payment = payments[i];
                Map<Month, Amount> entries = payment.getEntriesByMonth();
                if (entries.containsKey(month)) {
                    series[i].getData().add(new Data(month.toString(), entries.get(month).toDouble()));
                }
            }
        }
        result.addAll(series);
        regularPayings.setData(result);
    }

    private void createBilanseData() {
        ObservableList<Series<String, Double>> result = FXCollections.observableArrayList();
        Series<String, Double> bilanse = new Series<>();
        bilanse.setName("Stan konta na koniec miesiąca");
        for (MonthEntry monthEntry : finance.getMonthEntries()) {
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
        for (MonthEntry monthEntry : finance.getMonthEntries()) {
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
