package st.finanse.modules.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.modules.regular.PaymentEntry;

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
        Project.PROJECT.requestSaving();
    }

    public ObservableList<Entry> getEntries() {
        return entries;
    }

    public void addPayment(String title, PaymentEntry pe) {
        int day = pe.getPaymentDate().getDayOfMonth();
        boolean isHoliday = month.isSunday(day);
        Amount amount = pe.getAmount().switchSign();
        Entry entry = new Entry(title, day, amount, isHoliday, this);
        entries.add(entry);
        Project.PROJECT.requestSaving();
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof MonthEntry) {
            MonthEntry me = (MonthEntry)ob;
            return month.equals(me.month)
                    && startingAmount.equals(me.startingAmount)
                    && (isClosed == me.isClosed)
                    && entries.equals(me.entries);
        }
        return false;
    }

    public final Month month;
    public final Amount startingAmount;
    private boolean isClosed;
    private final ObservableList<Entry> entries = FXCollections.observableArrayList();

}
