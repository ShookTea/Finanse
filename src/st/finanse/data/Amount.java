package st.finanse.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {
    private Amount(BigDecimal bd) {
        cash = bd.setScale(2, RoundingMode.HALF_UP);
    }

    public Amount(double d) {
        this(BigDecimal.valueOf(d));
    }

    public Amount(String s) {
        this(new BigDecimal(s));
    }

    public String getCash() {
        return cash.toString();
    }

    public Amount add(Amount amount) {
        return new Amount(cash.add(amount.cash));
    }

    public Amount subtract(Amount amount) {
        return new Amount(cash.subtract(amount.cash));
    }

    public int signum() {
        return cash.signum();
    }

    public Amount switchSign() {
        return new Amount(0).subtract(this);
    }

    private final BigDecimal cash;
}
