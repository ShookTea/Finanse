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
        return new FileFilter(getDescription(), getFileEnd());
    }
    
    public static Format[] getAllFormats() {
        return new Format[] {new FormatFNS(), new FormatFNSX()};
    }
    
    public static Format getDefaultFormat() {
        return new FormatFNSX();
    }
    
    public static Format[] getChosableFormat() {
        return new Format[] {new FormatFNS()};
    }
    
    public static javax.swing.filechooser.FileFilter getAllFileFilter() {
        return new FileFilter("Wszystkie pliki programu Finanse (.FNSX, .FNS)", ".FNSX", ".FNS");
    }
    
    public static Format getFormatByFile(File f) {
        for (Format form : getAllFormats()) {
            if (form.createFileFilter().accept(f)) {
                return form;
            }
        }
        return null;
    }
    
    public static class FileFilter extends javax.swing.filechooser.FileFilter {
        
        public FileFilter(String desc, String... end) {
            this.ends = end;
            this.desc = desc;
        }
        
        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            for (String end : ends) {
                if (f.getName().toUpperCase().endsWith(end.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return desc;
        }
        
        public String getEnd() {
            return ends[0];
        }
        
        private final String ends[];
        private final String desc;
    }
}
