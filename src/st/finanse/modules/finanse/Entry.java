package st.finanse.modules.finanse;

import st.finanse.data.Amount;

public class Entry {
    public Entry(String title, int day, Amount amount, boolean markRed) {
        this.day = day;
        this.title = title;
        this.amount = amount;
        this.markRed = markRed;
    }

    public final int day;
    public final String title;
    public final Amount amount;
    public final boolean markRed;
}
