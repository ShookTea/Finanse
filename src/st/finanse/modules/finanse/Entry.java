package st.finanse.modules.finanse;

import st.finanse.data.Amount;

public class Entry {
    public Entry(String title, int day, Amount amount) {
        this.day = day;
        this.title = title;
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public Amount getAmount() {
        return amount;
    }

    private final int day;
    private final String title;
    private final Amount amount;
}
