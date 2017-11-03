package st.finanse.modules.finanse;

import st.finanse.data.Amount;

public class Entry {
    public Entry(String title, int day, Amount amount, boolean holiday) {
        this.day = day;
        this.title = title;
        this.amount = amount;
        if (amount.sign() > 0) {
            color = Color.BLUE;
        }
        else if (holiday) {
            color = Color.RED;
        }
        else {
            color = Color.DEFAULT;
        }
    }

    @Override
    public String toString() {
        return "[" + day + "] - " + title + ": " + amount + " - " + color;
    }

    public final int day;
    public final String title;
    public final Amount amount;
    public final Color color;

    public static enum Color {
        BLUE, RED, DEFAULT
    }
}
