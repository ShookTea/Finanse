package st.finanse.modules.regular;

import java.util.ArrayList;
import java.util.List;

public class RegularPayment {
    public RegularPayment(String name) {
        this.name = name;
    }

    public void addPayment(PaymentEntry entry) {
        if (payments.stream().filter(e -> e.equalsIgnoreIsPayed(entry)).count() == 0) {
            payments.add(entry);
        }
        else {
            int index = -1;
            for (int i = payments.size() - 1; i >= 0 && index == -1; i--) {
                if (payments.get(i).equalsIgnoreIsPayed(entry)) {
                    index = i;
                }
            }
            if (index != -1) {
                payments.set(index, entry);
            }
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
