package st.finanse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.format.Format;
import st.finanse.gui.MainWindowController;
import st.finanse.modules.finanse.MonthEntry;
import st.finanse.modules.regular.RegularPayment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Project {
    public Project() {}

    @Override
    public String toString() {
        String ret = "MODULE FINANCE";
        for (MonthEntry entry : FINANSE_MONTHS) {
            ret = ret + "\n" + entry.toString();
        }
        ret += "MODULE REGULAR";
        for (RegularPayment entry : REGULAR_PAYMENTS) {
            ret = ret + "\n" + entry.toString();
        }
        return ret;
    }

    public MonthEntry getEntryByMonth(Month m) {
        MonthEntry[] me = FINANSE_MONTHS.stream()
                .filter(e -> e.month.equals(m))
                .toArray(MonthEntry[]::new);
        if (me.length == 1) return me[0];
        return null;
    }

    public List<String> getTitleTip(String part) {
        List<String> ret = new ArrayList<>();
        FINANSE_MONTHS.stream()
                .forEach(month -> month.getEntries()
                        .filtered(entry -> entry.getTitle().contains(part))
                        .forEach(entry -> {
                            if (!ret.contains(entry.getTitle())) {
                                ret.add(entry.getTitle());
                            }
                        }));
        return ret;
    }

    public RegularPayment getRegularPaymentByName(String item) {
        RegularPayment[] rp = REGULAR_PAYMENTS.stream()
                .filter(e -> e.name.equals(item))
                .toArray(RegularPayment[]::new);
        if (rp.length == 1) return rp[0];
        return null;
    }

    public final ObservableList<MonthEntry> FINANSE_MONTHS = FXCollections.observableArrayList();
    public final ObservableList<RegularPayment> REGULAR_PAYMENTS = FXCollections.observableArrayList();
    public File file = null;

    public static Project PROJECT = new Project();

    public static void loadProject(File f) throws IOException {
        if (f == null) throw new IOException("Nie podano pliku do wczytania");
        PROJECT = Format.loadProject(f);
        if (PROJECT == null) createNewProject();
        else PROJECT.file = f;
    }

    public static void saveProject(File f) throws IOException {
        if (f == null) throw new IOException("Nie podano pliku do zapisania");
        Format.saveProject(PROJECT, f);
    }

    public static void createNewProject() {
        PROJECT = new Project();
        Month month = new Month();
        PROJECT.FINANSE_MONTHS.add(new MonthEntry(month, new Amount(0), false));
    }

    public static void tryCreatingNewProject() {
        Optional<ButtonType> buttonType = Start.showConfirmationAlert("Tworzenie nowego projektu", "Czy na pewno chcesz utworzyć nowy projekt? Stracisz wszystkie niezapisane zmiany.");
        if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
            Project.createNewProject();
            MainWindowController.updateAll();
        }
    }

    public static void trySavingProject(boolean saveAs) {
        if (saveAs || Project.PROJECT.file == null) {
            chooseFileToSave();
        }
        doSave();
    }

    private static void chooseFileToSave() {
        File toSave = Start.displaySaveDialogFileChooser();
        if (toSave != null) {
            Project.PROJECT.file = toSave;
        }
    }

    private static void doSave() {
        try {
            Project.saveProject(Project.PROJECT.file);
        } catch (IOException e) {
            Start.showExceptionAlert(e);
        }
    }
}
