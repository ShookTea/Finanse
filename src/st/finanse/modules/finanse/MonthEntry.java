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
            if (entry.amount.signum() > 0) {
                ret = ret.add(entry.amount);
            }
        }
        return ret;
    }

    public Amount getLostAmount() {
        Amount ret = new Amount("0.00");
        for (Entry entry : entries) {
            if (entry.amount.signum() < 0) {
                ret = ret.add(entry.amount);
            }
        }
        return ret.switchSign();
    }

    public Amount getCurrentAmount() {
        return startingAmount.add(getEarnedAmount()).subtract(getLostAmount());
    }

    public final Month month;
    public final Amount startingAmount;
    public final boolean isClosed;
    public final ObservableList<Entry> entries = FXCollections.observableArrayList();
}
