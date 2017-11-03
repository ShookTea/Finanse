package st.finanse.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount implements Comparable<Amount> {
    private Amount(BigDecimal bd) {
        cash = bd.setScale(2, RoundingMode.HALF_UP);
    }

    public Amount(double d) {
        this(BigDecimal.valueOf(d));
    }

    public Amount(String s) {
        this(new BigDecimal(s));
    }

    @Override
    public String toString() {
        return cash.toString();
    }

    public Amount add(Amount amount) {
        return new Amount(cash.add(amount.cash));
    }

    public Amount subtract(Amount amount) {
        return new Amount(cash.subtract(amount.cash));
    }

    public int sign() {
        return cash.signum();
    }

    public Amount switchSign() {
        return new Amount(0).subtract(this);
    }

    @Override
    public int compareTo(Amount o) {
        return cash.compareTo(o.cash);
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof Amount) {
            return ((Amount) ob).compareTo(this) == 0;
        }
        return false;
    }

    private final BigDecimal cash;
}
