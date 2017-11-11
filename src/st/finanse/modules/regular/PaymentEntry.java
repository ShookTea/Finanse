package st.finanse.modules.regular;

import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.modules.finanse.Entry;
import st.finanse.modules.finanse.MonthEntry;

import java.time.DayOfWeek;
import java.time.LocalDate;

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

    public boolean setPayed(LocalDate payedDate) {
        if (!isPayed) {
            isPayed = true;
            this.paymentDate = payedDate;
            Month m = new Month(paymentDate);
            MonthEntry monthEntry = Project.PROJECT.getEntryByMonth(m);
            if (monthEntry == null) {
                return false;
            }
            boolean holiday = paymentDate.getDayOfWeek() == DayOfWeek.SUNDAY;
            int day = paymentDate.getDayOfMonth();
            Entry financeEntry = new Entry(title, day, amount, holiday, monthEntry);
            monthEntry.getEntries().addAll(financeEntry);
            return true;
        }
        return false;
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
                    && isPayed == pe.isPayed
                    && title.equals(pe.title);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentEntry(");
        sb.append(title);
        sb.append(") [amount = ");
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

    public void setTitle(String title) {
        this.title = title;
    }

    private final Amount amount;
    private final LocalDate entryDate;
    private LocalDate paymentDate;
    private boolean isPayed;
    private String title = "";

}
