package st.finanse.modules.summary;

public class SummaryGroup implements Comparable<SummaryGroup> {
    private final int year;
    private final String[] columns;

    public SummaryGroup(int year, String[] columns) {
        this.year = year;
        this.columns = columns;
    }

    /**
     * If group contains summary for all years, return -1
     */
    public int getYear() {
        return year;
    }

    public String getTabLabel() {
        if (getYear() == -1) {
            return "Razem";
        }
        return "" + getYear();
    }

    public String[] getColumns() {
        return columns;
    }

    @Override
    public int compareTo(SummaryGroup o) {
        return year - o.year;
    }
}
