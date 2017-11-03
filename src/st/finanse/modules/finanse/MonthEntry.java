package st.finanse.modules.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.data.Month;

public class MonthEntry {
    public MonthEntry(Month month) {
        this.month = month;
    }

    public Month getMonth() {
        return month;
    }

    private final Month month;
    private final ObservableList<Entry> entries = FXCollections.observableArrayList();
}
