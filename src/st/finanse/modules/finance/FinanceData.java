package st.finanse.modules.finance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.Project;
import st.finanse.data.Month;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceData {
    public FinanceData() {}

    public void addMonthEntry(MonthEntry entry) {
        months.add(entry);
        Project.requestSaving();
    }

    public MonthEntry[] getMonthEntries() {
        return months.toArray(new MonthEntry[0]);
    }

    public int getMonthEntryCount() {
        return months.size();
    }

    public MonthEntry getEntryByMonth(Month m) {
        MonthEntry[] me = months.stream()
                .filter(e -> e.month.equals(m))
                .toArray(MonthEntry[]::new);
        if (me.length == 1) return me[0];
        return null;
    }

    public List<String> getTitleTip(String part) {
        return months.stream()
                .flatMap(month -> Arrays.stream(month.getEntries()))
                .map(Entry::getTitle)
                .distinct()
                .filter(title -> title.contains(part))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("MODULE FINANCE");
        for (MonthEntry entry : months) {
            sb.append("\n").append(entry.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof FinanceData) {
            return months.equals(((FinanceData) ob).months);
        }
        return false;
    }

    private final ObservableList<MonthEntry> months = FXCollections.observableArrayList();
}
