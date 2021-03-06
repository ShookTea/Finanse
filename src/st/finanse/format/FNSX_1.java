package st.finanse.format;

import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.modules.finance.Entry;
import st.finanse.modules.finance.MonthEntry;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FNSX_1 implements FNSX.FnsxVersion {
    @Override
    public Project load(DataInputStream dis) throws IOException {
        boolean cont = true;
        Project project = new Project();
        while (cont) {
            String header = "";
            try {
                header = dis.readUTF();
            } catch (EOFException ex) {
                cont = false;
            }

            switch(header) {
                case "MOD_FINANCE.START":
                    moduleFinance(project, dis);
                    break;
            }
        }
        return project;
    }

    private void moduleFinance(Project project, DataInputStream dis) throws IOException {
        String tag = dis.readUTF();
        while (!tag.equals("MOD_FINANCE.STOP")) { //w takim wypadku tag = TABLE.START
            int monthNumber = dis.readInt() + 1;
            int year = dis.readInt();
            Month month = new Month(year, monthNumber);
            Amount onBegin = new Amount(dis.readUTF());
            boolean isClosed = dis.readBoolean();
            MonthEntry monthEntry = new MonthEntry(month, onBegin, isClosed);

            String entryTag = dis.readUTF(); //ENTRY lub TABLE.STOP
            while (!entryTag.equals("TABLE.STOP")) { //w takim wypadku ENTRY
                String title = dis.readUTF();
                int day = dis.readInt();
                Amount cash = new Amount(dis.readUTF());
                boolean isHoliday = dis.readBoolean();
                Entry entry = new Entry(title, day, cash, isHoliday, monthEntry);
                monthEntry.addEntry(entry);
                entryTag = dis.readUTF(); //ENTRY lub TABLE.STOP
            }
            project.finance.addMonthEntry(monthEntry);
            tag = dis.readUTF(); //MOD_FINANCE.STOP lub TABLE.START
        }
    }
}
