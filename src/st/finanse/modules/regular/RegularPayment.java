package st.finanse.modules.regular;

import st.finanse.Project;
import st.finanse.data.Month;
import st.finanse.gui.MainWindowController;
import st.finanse.modules.finanse.MonthEntry;

import java.util.ArrayList;
import java.util.List;

public class RegularPayment {
    public RegularPayment(String name) {
        this.name = name;
    }

    public void addPayment(PaymentEntry entry) {
        if (!entry.isPayed()) {
            payments.add(entry);
            return;
        }
        if (payments.stream().filter(e -> e.equalsIgnoreIsPayed(entry)).count() == 0) {
            payments.add(entry);

        }
        else {
            int index = -1;
            for (int i = payments.size() - 1; i >= 0 && index == -1; i--) {
                if (payments.get(i).equalsIgnoreIsPayed(entry) && !payments.get(i).isPayed()) {
                    index = i;
                }
            }
            if (index != -1) {
                payments.set(index, entry);
            }
        }
        Month m = new Month(entry.getPaymentDate());
        MonthEntry me = Project.PROJECT.getEntryByMonth(m);
        if (me != null) {
            me.addPayment(name, entry);
            MainWindowController.updateAll();
        }
    }

    @Override
    public String toString() {
        String ret = "RegularPayment " + name;
        for (PaymentEntry pe : payments) {
            ret += "\n\t" + pe.toString();
        }
        return ret;
    }

    public PaymentEntry[] getPayments() {
        return payments.toArray(new PaymentEntry[0]);
    }

    private final List<PaymentEntry> payments = new ArrayList<>();
    public final String name;
}
