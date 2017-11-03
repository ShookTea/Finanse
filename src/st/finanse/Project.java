package st.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import st.finanse.data.Month;
import st.finanse.format.Format;

import java.io.File;
import java.io.IOException;

public class Project {
    private Project() {}

    public final ObservableList<Month> FINANSE_MONTHS = FXCollections.observableArrayList();

    public static Project PROJECT = new Project();

    public static void loadProject(File f) {
        PROJECT = Format.loadProject(f);
        if (PROJECT == null) createNewProject();
    }

    public static void saveProject(File f) throws IOException {
        Format.saveProject(PROJECT, f);
    }

    public static void createNewProject() {
        PROJECT = new Project();
    }
}
