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
import java.util.stream.Stream;

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

    @Override
    public boolean equals(Object ob) {
        if (ob instanceof Project) {
            Project p = (Project)ob;
            return FINANSE_MONTHS.equals(p.FINANSE_MONTHS) && REGULAR_PAYMENTS.equals(p.REGULAR_PAYMENTS);
        }
        return false;
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

    public void addMonthEntry(MonthEntry entry) {
        FINANSE_MONTHS.add(entry);
        needSave = true;
    }

    public MonthEntry[] getMonthEntries() {
        return FINANSE_MONTHS.toArray(new MonthEntry[0]);
    }

    public int getMonthEntryCount() {
        return FINANSE_MONTHS.size();
    }

    public RegularPayment[] getRegularPayments() {
        return REGULAR_PAYMENTS.toArray(new RegularPayment[0]);
    }

    public void addRegularPayment(RegularPayment rp) {
        REGULAR_PAYMENTS.add(rp);
        needSave = true;
    }

    public Stream<RegularPayment> regularPaymentStream() {
        return REGULAR_PAYMENTS.stream();
    }

    public void requestSaving() {
        needSave = true;
    }

    public boolean isSaveRequired() {
        return needSave;
    }

    private final ObservableList<MonthEntry> FINANSE_MONTHS = FXCollections.observableArrayList();
    private final ObservableList<RegularPayment> REGULAR_PAYMENTS = FXCollections.observableArrayList();
    public File file = null;
    private boolean needSave = false;

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
        Month currentMonth = new Month();
        Amount startAmount = new Amount(0);
        createNewProject(currentMonth, startAmount);
    }

    public static void createNewProject(Month firstMonth, Amount startAmount) {
        createNewProject(new MonthEntry(firstMonth, startAmount, false));
    }

    public static void createNewProject(MonthEntry me) {
        PROJECT = new Project();
        PROJECT.FINANSE_MONTHS.add(me);
    }

    public static void tryCreatingNewProject() {
        Optional<ButtonType> buttonType = Start.showConfirmationAlert("Tworzenie nowego projektu", "Czy na pewno chcesz utworzyć nowy projekt? Stracisz wszystkie niezapisane zmiany.");
        if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
            MonthEntry startMonth = Start.showMonthEntryDialog();
            Project.createNewProject(startMonth);
            MainWindowController.updateAll();
        }
    }

    public static void tryLoadingProject() {
        Optional<ButtonType> buttonType = Start.showConfirmationAlert("Wczytywanie", "Czy chcesz zapisać dane przed wczytaniem pliku?");
        if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
            trySavingProject(true);
        }
        else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            return;
        }

        File toOpen = Start.displayOpenDialogFileChooser();
        if (toOpen != null && toOpen.exists()) {
            try {
                Project.loadProject(toOpen);
                MainWindowController.updateAll();
            } catch (IOException e) {
                Start.showExceptionAlert(e);
            }
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
