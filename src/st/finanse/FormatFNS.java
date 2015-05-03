package st.finanse;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import st.finanse.mod.finance.Finance;
import st.finanse.mod.finance.Finance.FinanceEntry;
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
    public void save(Project p, File file) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);
        
        for (Finance f : Project.project.finances) {
            dos.writeUTF("FINANCE.START");
            dos.writeUTF(Month.getAllPolish()[f.getMonth()]);
            dos.writeInt(f.getYear());
            dos.writeUTF(f.getStart().toString());
            dos.writeBoolean(f.isClosed());
            for (FinanceEntry fe : f.getEntries()) {
                dos.writeUTF("FINANCE_ENTRY");
                dos.writeUTF(fe.day + ":" + f.getMonth() + ":" + f.getYear());
                dos.writeUTF(fe.title);
                dos.writeUTF(fe.cash.toString());
                if (fe.isEvent) {
                    dos.writeUTF("EVENT");
                }
                dos.writeUTF("FINANCE_ENTRY_STOP");
            }
            dos.writeUTF("FINANCE.STOP");
        }
        
        dos.writeUTF("TITLE.BASE.START");
        for (String key : Finance.getKeys()) {
            dos.writeUTF(key);
            dos.writeInt(Finance.getWeight(key));
        }
        dos.writeUTF("TITLE.BASE.STOP");
        
        dos.close();
        fos.close();
    }

    @Override
    public Project load(File f) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
