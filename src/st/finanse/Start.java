package st.finanse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import st.finanse.gui.Frame;
import st.finanse.proj.Project;

/**
 *
 * @author ShookTea
 */
public class Start implements st.init.StartI {

    @Override
    public void start(String[] args) {
        if (System.getProperty("os.name").startsWith("Windows") && (args.length == 0 || !args[0].equals("-t"))) {
            try {
                String java = System.getProperty("java.home");
                String jar = Start.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                Runtime r = Runtime.getRuntime();
                java = java.replace("\\", "\\\\") + "\\\\bin\\\\javaw.exe";
                while (jar.startsWith("\\") || jar.startsWith("/")) {
                    jar = jar.substring(1);
                }
                jar = jar.replace("\\", "\\\\").replace("/", "\\");
                String[] commands = new String[] {
                    "reg add HKCR\\.fns /ve /t REG_SZ /d \"st.finanse\" /f",
                    "reg add HKCR\\.fnsx /ve /t REG_SZ /d \"st.finanse\" /f",
                    "reg add HKCR\\st.finanse /ve /t REG_SZ /d \"Plik programu Finanse\" /f",
                    "reg add HKCR\\st.finanse\\shell /f",
                    "reg add HKCR\\st.finanse\\shell\\Open /f",
                    "reg add HKCR\\st.finanse\\shell\\Open\\Command /ve /t REG_SZ /d \"\\\"" + java + "\\\" -jar \\\"" + jar + "\\\" \\\"%1\\\"\" /f"
                };
                for (String command : commands) {
                    System.out.println(command);
                    r.exec(command);
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (args.length == 1 && !args[0].equals("-t")) {
            File f = new File(args[0]);
            Project.load(f);
        }
        initUI();
        Frame.frame.setVisible(true);
    }
    
    private static void initUI() {
        UIManager.put("FileChooser.openDialogTitleText", "Otwórz");
        UIManager.put("FileChooser.lookInLabelText", "Szukaj w");
        UIManager.put("FileChooser.openButtonText", "Otwórz");
        UIManager.put("FileChooser.saveButtonText", "Zapisz");
        UIManager.put("FileChooser.cancelButtonText", "Anuluj");
        UIManager.put("FileChooser.fileNameLabelText", "Nazwa pliku");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Typy plików");
        UIManager.put("FileChooser.openButtonToolTipText", "Otwórz wybrany plik");
        UIManager.put("FileChooser.saveButtonToolTipText", "Zapisz w wybranym pliku");
        UIManager.put("FileChooser.cancelButtonToolTipText","Anuluj");
        UIManager.put("FileChooser.fileNameHeaderText","Nazwa pliku");
        UIManager.put("FileChooser.upFolderToolTipText", "Poziom wyżej");
        UIManager.put("FileChooser.homeFolderToolTipText","Pulpit");
        UIManager.put("FileChooser.newFolderToolTipText","Stwórz nowy folder");
        UIManager.put("FileChooser.listViewButtonToolTipText","Lista");
        UIManager.put("FileChooser.newFolderButtonText","Stwórz nowy folder");
        UIManager.put("FileChooser.renameFileButtonText", "Zmień nazwę pliku");
        UIManager.put("FileChooser.deleteFileButtonText", "Usuń plik");
        UIManager.put("FileChooser.filterLabelText", "Typy plików");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detale");
        UIManager.put("FileChooser.fileSizeHeaderText","Rozmiar");
        UIManager.put("FileChooser.fileDateHeaderText", "Data modyfikacji");
       
        UIManager.put("OptionPane.yesButtonText", "Tak");
        UIManager.put("OptionPane.noButtonText", "Nie");
        UIManager.put("OptionPane.cancelButtonText", "Anuluj");
        UIManager.put("OptionPane.titleText", "Tytuł");
    }
}
