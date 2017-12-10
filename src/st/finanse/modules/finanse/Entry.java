package st.finanse.modules.finanse;

import st.finanse.data.Amount;

public class Entry {
    public Entry(String title, int day, Amount amount, boolean holiday, MonthEntry parent) {
        this.day = day;
        this.title = title;
        this.amount = amount;
        this.parent = parent;
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

    public String getDate() {
        return createString(day, 2) + "-" + createString(parent.month.getMonth(), 2) + "-" + createString(parent.month.getYear(), 4);
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

    public Color getColor() {
        return color;
    }

    private String createString(int d, int c) {
        String r = "" + d;
        while (r.length() < 2) r = "0" + r;
        return r;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof Entry) {
            Entry e = (Entry)ob;
            return day == e.day
                    && title.equals(e.title)
                    && amount.equals(e.amount)
                    && color.equals(e.color);
        }
        return false;
    }

    private final int day;
    private final String title;
    private final Amount amount;
    private final Color color;
    private final MonthEntry parent;

    public static enum Color {
        BLUE, RED, DEFAULT
    }
}
