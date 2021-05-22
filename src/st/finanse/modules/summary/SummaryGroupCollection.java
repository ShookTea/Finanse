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

    @Override
    public Iterator<SummaryGroup> iterator() {
        return summaryGroups.values().iterator();
    }
}
