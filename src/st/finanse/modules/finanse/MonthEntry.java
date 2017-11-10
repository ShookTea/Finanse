package st.finanse.modules.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.data.Amount;
import st.finanse.data.Month;

public class MonthEntry {
    public MonthEntry(Month month, Amount startAmount, boolean isClosed) {
        this.month = month;
        this.startingAmount = startAmount;
        this.isClosed = isClosed;
    }

    public Amount getEarnedAmount() {
        Amount ret = new Amount("0.00");
        for (Entry entry : entries) {
            if (entry.getAmount().sign() > 0) {
                ret = ret.add(entry.getAmount());
            }
        }
        return ret;
    }

    public Amount getLostAmount() {
        Amount ret = new Amount("0.00");
        for (Entry entry : entries) {
            if (entry.getAmount().sign() < 0) {
                ret = ret.add(entry.getAmount());
            }
        }
        return ret.switchSign();
    }

    public Amount getCurrentAmount() {
        return startingAmount.add(getEarnedAmount()).subtract(getLostAmount());
    }

    @Override
    public String toString() {
        String ret = "MONTH-ENTRY: " + month.toString() + " starting: " + startingAmount + " isClosed: " + isClosed;
        for (Entry entry : entries) {
            ret = ret + "\n\t" + entry.toString();
        }
        return ret;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void close() {
        isClosed = true;
    }

    public ObservableList<Entry> getEntries() {
        return isClosed ? FXCollections.observableArrayList(entries) : entries;
    }

    public final Month month;
    public final Amount startingAmount;
    private boolean isClosed;
    private final ObservableList<Entry> entries = FXCollections.observableArrayList();

}
