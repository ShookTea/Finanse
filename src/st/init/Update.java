package st.init;

import java.io.IOException;
import java.util.Properties;

/**
 * Klasa startowa dla operacji autoupdate. Wyszukuje aktualizację. Jeżeli aktualizacja
 * zostanie znaleziona, pobiera ją i instaluje. W przeciwnym razie uruchamia program.
 * @author ShookTea
 */
public class Update {
    public static void main(String[] args) throws IOException {
        initCfg();
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
    
    public static String cfg;
    public static String jar;
    public static String version;
    public static String history;
    public static String start;
}
