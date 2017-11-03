package st.finanse.format;

import st.finanse.Project;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    }

    public static interface FnsxVersion {
        public Project load(DataInputStream dis) throws IOException;
    }
}
