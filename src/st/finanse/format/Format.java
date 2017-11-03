package st.finanse.format;

import st.finanse.Project;

import java.io.File;

public interface Format {
    public Project loadFrom(File file);
    public void saveTo(Project project, File file);
}
