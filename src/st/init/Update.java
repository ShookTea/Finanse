package st.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Klasa startowa dla operacji autoupdate. Wyszukuje aktualizację. Jeżeli aktualizacja
 * zostanie znaleziona, pobiera ją i instaluje. W przeciwnym razie uruchamia program.
 * @author ShookTea
 */
public class Update implements Runnable {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName()); //NimbusLAF
        Update.args = args;
        initCfg();
        boolean isCheckNeeded = isCheckNeeded();
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        prefs.putInt("day", day);
        prefs.putInt("month", month);
        if (isCheckNeeded) {
            update(true);
        }
        else {
            startApp();
        }
    }
    
    private static boolean isCheckNeeded() {
        Calendar c = Calendar.getInstance();
        String trybe = prefs.get("check", "run");
        switch (trybe) {
            case "run":
                return true;
            case "day":
                return prefs.getInt("day", 0) != c.get(Calendar.DAY_OF_MONTH);
            case "month":
                return prefs.getInt("month", 0) != c.get(Calendar.MONTH);
            default:
                return true;
        }
    }
    
    private static void update(boolean run) {
        try {
            uf = new UpdateForm();
            uf.setVisible(true);
            if (isUpdateOnline()) {
                uf.showUpdateInfo(version, versionOnline, historyOnline);
            }
            else if (run) {
                startApp();
            }
        } catch (IOException ex) {
            //Brak połączenia z internetem: pobranie aktualizacji niemożliwe
            //Uruchom program normalnie
            if (run) {
                startApp();
            }
        }
    }
    
    public static void update() {
        update(false);
    }
    
    private static void initCfg() throws IOException {
        Properties props = new Properties();
        props.load(Update.class.getResourceAsStream("/st/init/init.cfg"));
        cfg = props.getProperty("cfg", "");
        version = props.getProperty("version", "");
        start = props.getProperty("start", "");
    }
    
    private static boolean isUpdateOnline() throws IOException {
        URL url = new URL(cfg);
        InputStream is = url.openStream();
        Properties online = new Properties();
        online.load(is);
        jarOnline = online.getProperty("jar", "");
        versionOnline = online.getProperty("version", "");
        historyOnline = online.getProperty("history", "");
        is.close();
        return !versionOnline.equals(version);
    }
    
    public static void startApp() {
        if (!started) {
            if (uf != null) {
                uf.dispose();
            }
            try {
                Object ob = Class.forName(start).newInstance();
                if (ob instanceof StartI) {
                    ((StartI)ob).start(args);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex1) {
                Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex1);
                System.exit(1);
            }
            started = true;
        }
    }
    
    public static void initUpdate() {
        new Thread(new Update()).start();
    }
    
    @Override
    public void run() {
        try {
            URL url = new URL(jarOnline);
            allSize = url.openConnection().getContentLength();
            size = 0;
            InputStream is = url.openStream();
            File jarfile = getJAR();
            FileOutputStream out = new FileOutputStream(jarfile, true);
            int x;
            byte[] data = new byte[1024];
            while((x = is.read(data, 0, 1024)) >= 0) {
                size += x;
                out.write(data, 0, x);
                uf.updateBar(size, allSize);
            }
            JOptionPane.showMessageDialog(uf, "Zakończono pobieranie aktualizacji. Program powinien automatycznie uruchomić się ponownie.", "Zakończono", JOptionPane.INFORMATION_MESSAGE);
            String line = "java -jar " + jarfile.getAbsolutePath();
            for (String arg : args) {
                if (!arg.contains(" ")) {
                    line += (" " + arg);
                }
                else {
                    line += ("\"" + arg + "\"");
                }
            }
            Runtime.getRuntime().exec(line);
            System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static File getJAR() throws URISyntaxException {
        return new File(Update.class.getProtectionDomain().getCodeSource().getLocation().toURI().g‌​etPath());
    }
    
    private static int size, allSize;
    
    private static UpdateForm uf;
    private static boolean started = false;
    private static String[] args;
    
    private static String cfg;
    private static String jarOnline;
    private static String version;
    private static String versionOnline;
    private static String historyOnline;
    private static String start;
    
    public static final Preferences prefs = Preferences.userRoot().node(Update.class.getName());
}
