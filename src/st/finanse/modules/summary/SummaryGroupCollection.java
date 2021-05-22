package st.finanse.modules.summary;

import st.finanse.data.Month;

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
                .sorted()
                .map(group -> group.getYear() + "")
                .toArray(String[]::new);

        SummaryGroup group = new SummaryGroup(
                SummaryGroup.SUMMARY_YEAR,
                years
        );

        summaryGroups.put(SummaryGroup.SUMMARY_YEAR, group);
    }

    @Override
    public Iterator<SummaryGroup> iterator() {
        return summaryGroups.values().stream().sorted().iterator();
    }
}
