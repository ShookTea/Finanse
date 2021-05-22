package st.finanse.modules.summary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SummaryGroup implements Comparable<SummaryGroup> {
    public static final int SUMMARY_YEAR = -1;

    private final int year;
    private final String[] columns;
    private final List<SummaryEntry> entries;

    public SummaryGroup(int year, String[] columns) {
        this.year = year;
        this.columns = columns;
        this.entries = new ArrayList<>();
    }

    /**
     * If group contains summary for all years, return -1
     */
    public int getYear() {
        return year;
    }

    public String getTabLabel() {
        if (getYear() == SUMMARY_YEAR) {
            return "Razem";
        }
        return "" + getYear();
    }

    public String[] getColumns() {
        return columns;
    }

    public SummaryEntry getEntryByName(String name) {
        Optional<SummaryEntry> found = entries.stream()
                .filter(entry -> entry.getName().equals(name))
                .findFirst();

        if (found.isPresent()) {
            return found.get();
        }

        SummaryEntry newVal = new SummaryEntry(name, this.getColumns().length);
        entries.add(newVal);
        return newVal;
    }

    public List<SummaryEntry> getEntries() {
        return entries;
    }

    @Override
    public int compareTo(SummaryGroup o) {
        return year - o.year;
    }
}
