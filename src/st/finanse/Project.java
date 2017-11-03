package st.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.data.Month;

import java.io.File;

public class Project {
    private Project() {}

    public final ObservableList<Month> FINANSE_MONTHS = FXCollections.observableArrayList();

    public static Project PROJECT = new Project();

    public static void loadProject(File f) {

    }
}
