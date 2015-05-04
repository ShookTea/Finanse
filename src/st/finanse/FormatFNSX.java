package st.finanse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import st.finanse.mod.finance.Finance;
import st.finanse.mod.finance.Finance.FinanceEntry;
import st.finanse.proj.Project;

/**
 * Klasa zapisująca i wczytująca dane w formacie FNS.
 * @author Norbert "ShookTea" Kowalik
 */
public class FormatFNSX extends Format {

    @Override
    public String getFileEnd() {
        return ".FNSX";
    }
    
    @Override
    public String getDescription() {
        return "Plik programu Finanse 2015 (.FNSX)";
    }

    @Override
    public void save(Project p, File file) throws Exception {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);
        
        dos.writeUTF("FNSX");
        dos.writeInt(1);
        
        //Moduł Finanse
        dos.writeUTF("MOD_FINANCE.START");
        for (Finance f : Project.project.finances) {
            dos.writeUTF("TABLE.START");
            dos.writeInt(f.getMonth());
            dos.writeInt(f.getYear());
            dos.writeUTF(f.getStart().toString());
            dos.writeBoolean(f.isClosed());
            for (FinanceEntry e : f.getEntries()) {
                dos.writeUTF("ENTRY");
                dos.writeUTF(e.title);
                dos.writeInt(e.day);
                dos.writeUTF(e.cash.toString());
                dos.writeBoolean(e.isEvent);
            }
            dos.writeUTF("TABLE.STOP");
        }
        dos.writeUTF("MOD_FINANCE.STOP");
    }

    @Override
    public Project load(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        Project p = new Project();
        if (dis.readUTF().equals("FNSX") && dis.readInt() == 1) {
            boolean EOF = false;
            while (!EOF) {
                try {
                    String utf = dis.readUTF();
                    if (utf.equals("MOD_FINANCE.START")) {
                        while (dis.readUTF().equals("TABLE.START")) {
                            loadMonth(p, dis);
                        }
                    }
                }
                catch (EOFException ex) {
                    EOF = true;
                }
            }
        }
        
        dis.close();
        fis.close();
        return p;
    }
    
    private void loadMonth(Project p, DataInputStream dis) throws Exception {
        int month = dis.readInt();
        int year = dis.readInt();
        BigDecimal start = new BigDecimal(dis.readUTF());
        boolean closed = dis.readBoolean();
        
        Finance f = p.createFinance(month, year, start);
        
        while (dis.readUTF().equals("ENTRY")) {
            String title = dis.readUTF();
            int day = dis.readInt();
            BigDecimal cash = new BigDecimal(dis.readUTF());
            boolean event = dis.readBoolean();
            f.addEntry(day, title, event, cash);
        }
        if (closed) {
            f.close();
        }
    }
}
