package st.finanse.format;

import st.finanse.Project;
import st.finanse.data.Amount;
import st.finanse.data.Month;
import st.finanse.modules.finanse.Entry;
import st.finanse.modules.finanse.MonthEntry;
import st.finanse.modules.regular.PaymentEntry;
import st.finanse.modules.regular.RegularPayment;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.time.LocalDate;

public class FNSX_2 implements FNSX.FnsxVersion {
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
                case "MOD_REGULAR.START":
                    moduleRegular(project, dis);
                    break;
            }
        }
        return project;
    }

    private void moduleFinance(Project project, DataInputStream dis) throws IOException {
        String tag = dis.readUTF();
        while (!tag.equals("MOD_FINANCE.STOP")) { //w takim wypadku tag = TABLE.START
            int monthNumber = dis.readInt();
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

    private void moduleRegular(Project project, DataInputStream dis) throws IOException {
        String tag = dis.readUTF();
        while (!tag.equals("MOD_REGULAR.STOP")) { //w takim wypadku tag = REGULAR.START
            String title = dis.readUTF();
            RegularPayment regular = new RegularPayment(title);

            String entryTag = dis.readUTF(); //ENTRY lub REGULAR.STOP
            while (!entryTag.equals("REGULAR.STOP")) { //w takim wypadku ENTRY
                Amount cash = new Amount(dis.readUTF());
                LocalDate entryDate = readDate(dis);
                LocalDate payDate = null;
                boolean isPayed = dis.readBoolean();
                if (isPayed) {
                    payDate = readDate(dis);
                }
                PaymentEntry entry = new PaymentEntry(cash, entryDate, payDate);
                regular.addPayment(entry);

                entryTag = dis.readUTF(); //ENTRY lub REGULAR.STOP
            }
            project.addRegularPayment(regular);
            tag = dis.readUTF(); //MOD_REGULAR.STOP lub REGULAR.START
        }
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        int day = dis.readInt();
        int month = dis.readInt();
        int year = dis.readInt();
        LocalDate date = LocalDate.of(year, month, day);
        return date;
    }
}
