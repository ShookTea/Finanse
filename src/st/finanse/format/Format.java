package st.finanse.format;

import st.finanse.Project;

import java.io.File;
import java.io.IOException;

public interface Format {
    public Project loadFrom(File file);
    public void saveTo(Project project, File file);

    public static Project loadProject(File f) {
        return null;
    }

    public static void saveProject(Project project, File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        
    }
}
