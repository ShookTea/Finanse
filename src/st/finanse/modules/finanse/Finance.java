package st.finanse.modules.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.Project;
import st.finanse.data.Month;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Finance {
    public Finance() {}

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
        List<String> ret = new ArrayList<>();
        months.stream()
                .forEach(month -> Arrays.stream(month.getEntries())
                        .filter(entry -> entry.getTitle().contains(part))
                        .forEach(entry -> {
                            if (!ret.contains(entry.getTitle())) {
                                ret.add(entry.getTitle());
                            }
                        }));
        return ret;
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
        if (ob instanceof Finance) {
            return months.equals(((Finance) ob).months);
        }
        return false;
    }

    private final ObservableList<MonthEntry> months = FXCollections.observableArrayList();
}
