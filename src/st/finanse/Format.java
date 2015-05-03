package st.finanse;

import java.io.File;
import st.finanse.proj.Project;

/**
 * Klasa zarządzająca zapisywaniem i wczytywaniem danych.
 * @author Norbert "ShookTea" Kowalik
 */
public abstract class Format {
    public Format() {}
    
    public abstract String getFileEnd();
    public abstract String getDescription();
    public abstract void save(Project p, File f) throws Exception;
    public abstract Project load(File f) throws Exception;
    
    public javax.swing.filechooser.FileFilter createFileFilter() {
        return new FileFilter(getFileEnd(), getDescription());
    }
    
    public static Format[] getAllFormats() {
        return new Format[] {new FormatFNS()};
    }
    
    private class FileFilter extends javax.swing.filechooser.FileFilter {
        
        public FileFilter(String end, String desc) {
            this.end = end;
            this.desc = desc;
        }
        
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toUpperCase().endsWith(end.toUpperCase());
        }

        @Override
        public String getDescription() {
            return desc;
        }
        
        private final String end;
        private final String desc;
    }
}
