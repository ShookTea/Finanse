package st.finanse.data;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

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
        return toFormattedString();
    }

    public String toFormattedString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(cash.doubleValue());
    }

    public String toUnformattedString() {
        return cash.toString();
    }

    public Amount add(Amount amount) {
        return new Amount(cash.add(amount.cash));
    }

    public Amount subtract(Amount amount) {
        return new Amount(cash.subtract(amount.cash));
    }

    public int sign() {
        int sgn = cash.signum();
        if (sgn < 0) return -1;
        if (sgn > 0) return 1;
        return 0;
    }

    public Amount switchSign() {
        return new Amount(0).subtract(this);
    }

    public Amount absolute() {
        return sign() < 0 ? switchSign() : copy();
    }

    public double toDouble() {
        return cash.doubleValue();
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

    public Amount copy() {
        return new Amount(this.cash);
    }

    private final BigDecimal cash;

    public static TextFormatter createAmountFormatter() {
        return new TextFormatter<>(new BigDecimalStringConverter() {
            public BigDecimal fromString(String value) {
                if (value == null) {
                    return null;
                }
                value = value.trim().replace(",", ".");
                if (value.length() < 1) {
                    return null;
                }
                return new BigDecimal(value);
            }
            public String toString(BigDecimal value) {
                if (value == null) {
                    return "";
                }
                return value.toString();
            }
        });
    }
}
