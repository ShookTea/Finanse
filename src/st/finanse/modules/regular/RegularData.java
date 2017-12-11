package st.finanse.modules.regular;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.Project;

import java.util.stream.Stream;

public class RegularData {
    public RegularData() {}

    public RegularPayment[] getRegularPayments() {
        return regularPayments.toArray(new RegularPayment[0]);
    }

    public void addRegularPayment(RegularPayment rp) {
        regularPayments.add(rp);
        Project.requestSaving();
    }

    public Stream<RegularPayment> regularPaymentStream() {
        return regularPayments.stream();
    }

    public RegularPayment getRegularPaymentByName(String item) {
        RegularPayment[] rp = regularPayments.stream()
                .filter(e -> e.name.equals(item))
                .toArray(RegularPayment[]::new);
        if (rp.length == 1) return rp[0];
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("MODULE REGULAR");
        for (RegularPayment rp : regularPayments) {
            sb.append(rp.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof RegularData) {
            return regularPayments.equals(((RegularData) ob).regularPayments);
        }
        return false;
    }

    private final ObservableList<RegularPayment> regularPayments = FXCollections.observableArrayList();
}
