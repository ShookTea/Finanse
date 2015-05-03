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
    public abstract void save(Project p, File f) throws Exception;
    public abstract Project load(File f) throws Exception;
    
    public static Format[] getAllFormats() {
        return new Format[] {};
    }
}
