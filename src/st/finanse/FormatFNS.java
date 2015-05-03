package st.finanse;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import st.finanse.proj.Project;

/**
 * Klasa zapisująca i wczytująca dane w formacie FNS.
 * @author Norbert "ShookTea" Kowalik
 */
public class FormatFNS extends Format {

    @Override
    public String getFileEnd() {
        return ".FNS";
    }

    @Override
    public void save(Project p, File f) throws Exception {
        FileOutputStream fos = new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);
        
        
        
        dos.close();
        fos.close();
    }

    @Override
    public Project load(File f) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
