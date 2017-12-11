package st.finanse.format;

import st.finanse.Project;
import st.finanse.modules.finanse.Entry;
import st.finanse.modules.finanse.MonthEntry;
import st.finanse.modules.regular.PaymentEntry;
import st.finanse.modules.regular.RegularPayment;

import java.io.*;
import java.time.LocalDate;

public class FNSX implements Format {
    @Override
    public Project loadFrom(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        Project project = loadProject(dis);
        dis.close();
        fis.close();
        return project;
    }

    private Project loadProject(DataInputStream dis) throws IOException {
        if (dis.readUTF().equals("FNSX")) {
            int version = dis.readInt();
            switch (version) {
                case 1:
                    return new FNSX_1().load(dis);
                case 2:
                    return new FNSX_2().load(dis);
            }
        }
        return new Project();
    }

    //zapis zawsze odbywa się w najnowszej wersji
    @Override
    public void saveTo(Project project, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF("FNSX");
        dos.writeInt(NEWEST_VERSION);

        writeFinanceModule(project, dos);
        writeRegularModule(project, dos);

        dos.close();
        fos.close();
    }

    private void writeFinanceModule(Project project, DataOutputStream dos) throws IOException {
        dos.writeUTF("MOD_FINANCE.START");
        for (MonthEntry monthEntry : project.getMonthEntries()) {
            dos.writeUTF("TABLE.START");
            dos.writeInt(monthEntry.month.getMonth());
            dos.writeInt(monthEntry.month.getYear());
            dos.writeUTF(monthEntry.startingAmount.toUnformattedString());
            dos.writeBoolean(monthEntry.isClosed());

            for (Entry entry : monthEntry.getEntries()) {
                dos.writeUTF("ENTRY");
                dos.writeUTF(entry.getTitle());
                dos.writeInt(entry.getDay());
                dos.writeUTF(entry.getAmount().toUnformattedString());
                dos.writeBoolean(entry.getColor() == Entry.Color.RED);
            }

            dos.writeUTF("TABLE.STOP");
        }
        dos.writeUTF("MOD_FINANCE.STOP");
    }

    private void writeRegularModule(Project project, DataOutputStream dos) throws IOException {
        dos.writeUTF("MOD_REGULAR.START");
        for (RegularPayment payment : project.REGULAR_PAYMENTS) {
            dos.writeUTF("REGULAR.START");
            dos.writeUTF(payment.name);
            for (PaymentEntry entry : payment.getPayments()) {
                dos.writeUTF("ENTRY");
                dos.writeUTF(entry.getAmount().toUnformattedString());
                writeDate(entry.getEntryDate(), dos);
                dos.writeBoolean(entry.isPayed());
                if (entry.isPayed()) {
                    writeDate(entry.getPaymentDate(), dos);
                }
            }
            dos.writeUTF("REGULAR.STOP");
        }
        dos.writeUTF("MOD_REGULAR.STOP");
    }

    private void writeDate(LocalDate date, DataOutputStream dos) throws IOException {
        dos.writeInt(date.getDayOfMonth());
        dos.writeInt(date.getMonthValue());
        dos.writeInt(date.getYear());
    }

    public static interface FnsxVersion {
        public Project load(DataInputStream dis) throws IOException;
    }

    public static final int NEWEST_VERSION = 2;
}
