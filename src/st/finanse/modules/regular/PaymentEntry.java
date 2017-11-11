package st.finanse.modules.regular;

import st.finanse.data.Amount;

import java.time.LocalDate;
import java.util.Comparator;

public class PaymentEntry implements Comparable<PaymentEntry> {
    private PaymentEntry(Amount amount, LocalDate entryDate, LocalDate paymentDate, boolean isPayed) {
        this.amount = amount;
        this.entryDate = entryDate;
        this.paymentDate = paymentDate;
        this.isPayed = isPayed;
    }

    public PaymentEntry(Amount amount, LocalDate entryDate, LocalDate paymentDate) {
        this(amount, entryDate, paymentDate, paymentDate != null);
    }

    public PaymentEntry(Amount amount, LocalDate entryDate) {
        this(amount, entryDate, null);
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(LocalDate payedDate) {
        if (!isPayed) {
            isPayed = true;
            this.paymentDate = payedDate;
        }
    }

    @Override
    public int compareTo(PaymentEntry o) {
        if (this.equals(o)) return 0;
        int entry = entryDate.compareTo(o.entryDate);
        if (entry != 0) return entry;
        entry = Boolean.compare(isPayed, o.isPayed);
        if (entry != 0) return entry;
        entry = paymentDate.compareTo(o.paymentDate);
        if (entry != 0) return entry;
        return amount.compareTo(o.amount);
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof PaymentEntry) {
            PaymentEntry pe = (PaymentEntry)ob;
            return amount.equals(pe.amount)
                    && entryDate.equals(pe.entryDate)
                    && paymentDate.equals(pe.paymentDate)
                    && isPayed == pe.isPayed;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentEntry [amount = ");
        sb.append(amount.toFormattedString());
        sb.append("; entryDate = ");
        sb.append(entryDate.toString());
        if (isPayed) {
            sb.append("; paymentDate = ");
            sb.append(paymentDate.toString());
        }
        else {
            sb.append("; payed = false");
        }
        sb.append("]");
        return sb.toString();
    }

    private final Amount amount;
    private final LocalDate entryDate;
    private LocalDate paymentDate;
    private boolean isPayed;

}
