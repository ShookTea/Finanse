package st.finanse.modules.regular;

import java.util.ArrayList;
import java.util.List;

public class RegularPayment {
    public RegularPayment(String name) {
        this.name = name;
    }

    public void addPayment(PaymentEntry entry) {
        payments.add(entry);
        entry.setTitle(name);
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
