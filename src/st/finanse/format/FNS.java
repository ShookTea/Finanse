package st.finanse.format;

import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.modules.finanse.Entry;
import st.finanse.modules.finanse.MonthEntry;

import java.io.*;

public class FNS implements Format {
    @Override
    public Project loadFrom(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);

        Project proj = new Project();

        MonthEntry entry = readEntry(dis);
        while (entry != null) {
            proj.FINANSE_MONTHS.add(entry);
            entry = readEntry(dis);
        }

        dis.close();
        fis.close();
        return proj;
    }

    private MonthEntry readEntry(DataInputStream dis) throws IOException {
        String header = dis.readUTF(); //FINANCE.START
        if (!header.equals("FINANCE.START")) return null;
        String monthName = dis.readUTF();
        int year = dis.readInt();
        Amount onBegin = new Amount(dis.readUTF());
        boolean isClosed = dis.readBoolean();
        Month month = new Month(year, monthName);
        MonthEntry monthEntry = new MonthEntry(month, onBegin, isClosed);

        while (dis.readUTF().equals("FINANCE_ENTRY")) {
            String[] date = dis.readUTF().split(":");
            int day = Integer.parseInt(date[0]);
            String title = dis.readUTF();
            Amount cash = new Amount(dis.readUTF());
            boolean holiday = false;
            if (dis.readUTF().equals("EVENT")) {
                holiday = true;
                dis.readUTF();
            }
            Entry e = new Entry(title, day, cash, holiday, monthEntry);
            monthEntry.entries.add(e);
        }
        return monthEntry;
    }

    @Override
    public void saveTo(Project project, File file) throws IOException {
        throw new IOException();
    }
}
