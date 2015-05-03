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
public class FormatFNS extends Format {

    @Override
    public String getFileEnd() {
        return ".FNS";
    }
    
    @Override
    public String getDescription() {
        return "Plik programu Finanse 2014 (.FNS)";
    }

    @Override
    public void save(Project p, File file) throws Exception {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
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
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        Project p = new Project();
        boolean EOF = false;
        while (!EOF) {
            try {
                if (dis.readUTF().equals("FINANCE.START")) {
                    loadMonth(p, dis);
                }
                //TITLE.BASE.START jest wyliczany automatycznie.
            }
            catch (EOFException ex) {
                EOF = true;
            }
        }
        
        
        dis.close();
        fis.close();
        return p;
    }
    
    private void loadMonth(Project p, DataInputStream dis) throws Exception {
        String monthS = dis.readUTF();
        int month = Month.getMonthID(monthS);
        int year = dis.readInt();
        BigDecimal start = new BigDecimal(dis.readUTF());
        boolean closed = dis.readBoolean();
        
        Finance f = p.createFinance(month, year, start);
        
        while (dis.readUTF().equals("FINANCE_ENTRY")) {
            String date = dis.readUTF();
            int day = Integer.parseInt(date.split(":")[0].trim());
            String title = dis.readUTF();
            BigDecimal cash = new BigDecimal(dis.readUTF());
            boolean event = false;
            String next = dis.readUTF();
            if (next.equals("EVENT")) {
                event = true;
                dis.readUTF(); //usunięcie "FINANCE_ENTRY_STOP"
            }
            f.addEntry(day, title, event, cash);
        }
        if (closed) {
            f.close();
        }
    }
}
