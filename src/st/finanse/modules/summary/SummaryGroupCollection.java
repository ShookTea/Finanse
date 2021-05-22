package st.finanse.modules.summary;

import st.finanse.data.Month;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

public class SummaryGroupCollection implements Iterable<SummaryGroup> {
    private final TreeMap<Integer, SummaryGroup> summaryGroups;

    public SummaryGroupCollection() {
        summaryGroups = new TreeMap<>();
    }

    public SummaryGroup getByMonth(Month month) {
        if (!summaryGroups.containsKey(month.getYear())) {
            SummaryGroup newSummaryGroup = new SummaryGroup(
                    month.getYear(),
                    Month.monthsNamesList
                            .stream()
                            .map(name -> name + " " + month.getYear())
                            .toArray(String[]::new)
            );
            summaryGroups.put(month.getYear(), newSummaryGroup);
        }

        return summaryGroups.get(month.getYear());
    }

    public void buildDefaultGroup() {
        if (summaryGroups.containsKey(SummaryGroup.SUMMARY_YEAR)) {
            return;
        }

        String[] years = summaryGroups
                .values()
                .stream()
                .sorted(Comparator.reverseOrder()) // make newest on right side
                .map(group -> group.getYear() + "")
                .toArray(String[]::new);

        SummaryGroup summaryGroup = new SummaryGroup(
                SummaryGroup.SUMMARY_YEAR,
                years
        );

        for (SummaryGroup group : summaryGroups.values()) {
            int yearIndex = getYearIndex(years, group.getYear());
            for (SummaryEntry entry : group.getEntries()) {
                summaryGroup.getEntryByName(entry.getName())
                        .addAmount(yearIndex, entry.getSumValue());
            }
        }

        summaryGroups.put(SummaryGroup.SUMMARY_YEAR, summaryGroup);
    }

    private int getYearIndex(String[] years, int year) {
        for (int i = 0; i < years.length; i++) {
            if (years[i].equals("" + year)) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<SummaryGroup> iterator() {
        return summaryGroups.values().stream().sorted().iterator();
    }
}
