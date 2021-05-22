package st.finanse.modules.summary;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import st.finanse.data.Amount;

public class SummaryEntry {
    private final String name;
    private final Amount[] amounts;

    public SummaryEntry(String name, int dataColumnsCount) {
        this.name = name;
        this.amounts = new Amount[dataColumnsCount + 1];
        for (int i = 0; i < amounts.length; i++) {
            amounts[i] = new Amount("0");
        }
    }

    public String getName() {
        return name;
    }

    public ObservableValue<String> nameProperty() {
        return new SimpleStringProperty(name);
    }

    public ObservableValue<Amount> valueOnIndexProperty(int index) {
        return new SimpleObjectProperty<>(amounts[index]);
    }

    public Amount getSumValue() {
        return amounts[amounts.length - 1];
    }

    public ObservableValue<Amount> sumValueProperty() {
        return new SimpleObjectProperty<>(getSumValue());
    }

    public void addAmount(int column, Amount amount) {
        if (column < 0 || column >= (amounts.length - 1)) {
            throw new IndexOutOfBoundsException();
        }
        amounts[column] = amounts[column].add(amount);
        amounts[amounts.length - 1] = amounts[amounts.length - 1].add(amount);
    }
}
