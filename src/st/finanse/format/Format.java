package st.finanse.format;

import st.finanse.Project;

import java.io.*;

public interface Format {
    public Project loadFrom(File file) throws IOException;
    public void saveTo(Project project, File file) throws IOException;

    public static Project loadProject(File f) throws IOException {
        if (!f.exists() || f.isDirectory()) return null;
        if (f.getName().toUpperCase().endsWith(".FNS")) {
            return new FNS().loadFrom(f);
        }

        return null;
    }

    public static void saveProject(Project project, File file) throws IOException {
        if (file.exists() && file.isDirectory()) return;
        if (!file.exists()) {
            file.createNewFile();
        }
        else if (file.getName().toUpperCase().endsWith(".FNS")) {
            file = new File(file.getAbsolutePath() + "X");
        }
    }
}
