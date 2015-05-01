package st.init;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Klasa startowa dla operacji autoupdate. Wyszukuje aktualizację. Jeżeli aktualizacja
 * zostanie znaleziona, pobiera ją i instaluje. W przeciwnym razie uruchamia program.
 * @author ShookTea
 */
public class Update {
    public static void main(String[] args) throws IOException {
        uf = new UpdateForm();
        uf.setVisible(true);
        initCfg();
        try {
            System.out.println(isUpdateOnline());
        } catch (IOException ex) {
            //Brak połączenia z internetem: pobranie aktualizacji niemożliwe
            //Uruchom program normalnie
            uf.dispose();
        }
    }
    
    private static void initCfg() throws IOException {
        Properties props = new Properties();
        props.load(Update.class.getResourceAsStream("/st/init/init.cfg"));
        cfg = props.getProperty("cfg");
        jar = props.getProperty("jar");
        version = props.getProperty("version");
        history = props.getProperty("history");
        start = props.getProperty("start");
    }
    
    private static boolean isUpdateOnline() throws IOException {
        URL url = new URL(cfg);
        InputStream is = url.openStream();
        Properties online = new Properties();
        online.load(is);
        jarOnline = online.getProperty("jar");
        versionOnline = online.getProperty("version");
        historyOnline = online.getProperty("history");
        is.close();
        return versionOnline.equalsIgnoreCase(version);
    }
    
    private static UpdateForm uf;
    
    public static String cfg;
    public static String jar;
    public static String jarOnline;
    public static String version;
    public static String versionOnline;
    public static String history;
    public static String historyOnline;
    public static String start;
}
