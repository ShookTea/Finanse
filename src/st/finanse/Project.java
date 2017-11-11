package st.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.format.Format;
import st.finanse.modules.finanse.MonthEntry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Project {
    public Project() {}

    @Override
    public String toString() {
        String ret = "MODULE FINANCE";
        for (MonthEntry entry : FINANSE_MONTHS) {
            ret = ret + "\n" + entry.toString();
        }
        return ret;
    }

    public MonthEntry getEntryByMonth(Month m) {
        MonthEntry[] me = FINANSE_MONTHS.stream()
                .filter(e -> e.month.equals(m))
                .toArray(MonthEntry[]::new);
        if (me.length == 1) return me[0];
        return null;
    }

    public List<String> getTitleTip(String part) {
        List<String> ret = new ArrayList<>();
        FINANSE_MONTHS.stream()
                .forEach(month -> month.getEntries()
                        .filtered(entry -> entry.getTitle().contains(part))
                        .forEach(entry -> {
                            if (!ret.contains(entry.getTitle())) {
                                ret.add(entry.getTitle());
                            }
                        }));
        return ret;
    }

    public final ObservableList<MonthEntry> FINANSE_MONTHS = FXCollections.observableArrayList();

    public static Project PROJECT = new Project();

    public static void loadProject(File f) throws IOException {
        PROJECT = Format.loadProject(f);
        if (PROJECT == null) createNewProject();
    }

    public static void saveProject(File f) throws IOException {
        Format.saveProject(PROJECT, f);
    }

    public static void createNewProject() {
        PROJECT = new Project();
        Month month = new Month();
        PROJECT.FINANSE_MONTHS.add(new MonthEntry(month, new Amount(0), false));
    }
}
