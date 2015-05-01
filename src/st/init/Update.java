package st.init;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa startowa dla operacji autoupdate. Wyszukuje aktualizację. Jeżeli aktualizacja
 * zostanie znaleziona, pobiera ją i instaluje. W przeciwnym razie uruchamia program.
 * @author ShookTea
 */
public class Update {
    public static void main(String[] args) throws IOException {
        Update.args = args;
        uf = new UpdateForm();
        uf.setVisible(true);
        initCfg();
        try {
            if (isUpdateOnline()) {
                uf.showUpdateInfo(version, versionOnline, historyOnline);
            }
            else {
                uf.dispose();
                startApp();
            }
        } catch (IOException ex) {
            //Brak połączenia z internetem: pobranie aktualizacji niemożliwe
            //Uruchom program normalnie
            uf.dispose();
            startApp();
        }
    }
    
    private static void initCfg() throws IOException {
        Properties props = new Properties();
        props.load(Update.class.getResourceAsStream("/st/init/init.cfg"));
        cfg = props.getProperty("cfg", "");
        jar = props.getProperty("jar", "");
        version = props.getProperty("version", "");
        history = props.getProperty("history", "");
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
        return versionOnline.equals(version);
    }
    
    private static void startApp() {
        try {
            Object ob = Class.forName(start).newInstance();
            if (ob instanceof StartI) {
                ((StartI)ob).start(args);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex1) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex1);
            System.exit(1);
        }
    }
    
    private static UpdateForm uf;
    public static String[] args;
    
    public static String cfg;
    public static String jar;
    public static String jarOnline;
    public static String version;
    public static String versionOnline;
    public static String history;
    public static String historyOnline;
    public static String start;
}
