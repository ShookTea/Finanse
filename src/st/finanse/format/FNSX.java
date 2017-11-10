package st.finanse.format;

import st.finanse.Project;
import st.finanse.modules.finanse.Entry;
import st.finanse.modules.finanse.MonthEntry;

import java.io.*;

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

        dos.close();
        fos.close();
    }

    private void writeFinanceModule(Project project, DataOutputStream dos) throws IOException {
        dos.writeUTF("MOD_FINANCE.START");
        for (MonthEntry monthEntry : project.FINANSE_MONTHS) {
            dos.writeUTF("TABLE.START");
            dos.writeInt(monthEntry.month.getMonth() - 1);
            dos.writeInt(monthEntry.month.getYear());
            dos.writeUTF(monthEntry.startingAmount.toString());
            dos.writeBoolean(monthEntry.isClosed());

            for (Entry entry : monthEntry.getEntries()) {
                dos.writeUTF("ENTRY");
                dos.writeUTF(entry.getTitle());
                dos.writeInt(entry.getDay());
                dos.writeUTF(entry.getAmount().toString());
                dos.writeBoolean(entry.getColor() == Entry.Color.RED);
            }

            dos.writeUTF("TABLE.STOP");
        }
        dos.writeUTF("MOD_FINANCE.STOP");
    }

    public static interface FnsxVersion {
        public Project load(DataInputStream dis) throws IOException;
    }

    public static final int NEWEST_VERSION = 1;
}
