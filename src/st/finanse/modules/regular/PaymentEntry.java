package st.finanse.modules.regular;

import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.gui.MainWindowController;
import st.finanse.modules.finanse.Entry;
import st.finanse.modules.finanse.MonthEntry;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PaymentEntry implements Comparable<PaymentEntry> {
    public PaymentEntry(String name, Amount amount, LocalDate entryDate, LocalDate paymentDate) {
        this.title = name;
        this.amount = amount.absolute();
        this.entryDate = entryDate;
        this.isPayed = false;
        if (paymentDate != null) {
            setPayed(paymentDate);
        }
    }

    public PaymentEntry(Amount amount, LocalDate entryDate, LocalDate paymentDate) {
        this("", amount, entryDate, paymentDate);
    }

    public Amount getAmount() {
        return amount;
    }

    public String getEntryDateString() {
        return createStringFromDate(entryDate);
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public String getPaymentDateString() {
        return paymentDate == null ? "(nie opłacono)" : createStringFromDate(paymentDate);
    }

    private String createStringFromDate(LocalDate ld) {
        return ld.getDayOfMonth() + " " + new Month(ld).getMonthAccusative() + " " + ld.getYear();
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
        Project.PROJECT.requestSaving();
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
        if (this.equalsIgnoreIsPayed(ob)) {
            PaymentEntry pe = (PaymentEntry)ob;
            return isPayed == pe.isPayed
                    && ((paymentDate == null) == (ob == null))
                    && (paymentDate == null || paymentDate.equals(pe.paymentDate));
        }
        return false;
    }

    public boolean equalsIgnoreIsPayed(Object ob) {
        if (ob instanceof PaymentEntry) {
            PaymentEntry pe = (PaymentEntry)ob;
            return amount.equals(pe.amount)
                    && entryDate.equals(pe.entryDate)
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
