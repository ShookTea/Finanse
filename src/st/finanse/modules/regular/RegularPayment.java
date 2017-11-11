package st.finanse.modules.regular;

import java.util.ArrayList;
import java.util.List;

public class RegularPayment {
    public RegularPayment(String name) {
        this.name = name;
    }

    public final List<PaymentEntry> payments = new ArrayList<>();
    public final String name;
}
