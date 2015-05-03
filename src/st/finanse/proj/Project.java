package st.finanse.proj;

import java.io.File;
import st.finanse.mod.finance.Finance;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import st.finanse.Format;
import st.finanse.Month;
import st.finanse.gui.Frame;

/**
 * Klasa zarządzająca projektem.
 * @author Norbert "ShookTea" Kowalik
 */
public class Project {
    public static Project project = new Project();
    public Project() {
        finances = new ArrayList();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        Frame.removeAllJIF();
    }
    
    public Finance createFinance(int month, int year, BigDecimal start) {
        Finance f = new Finance(month, year, start.setScale(2, RoundingMode.HALF_UP));
        finances.add(f);
        return f;
    }
    
    public Finance createFinance(Finance before) {
        if (before.isClosed()) {
            int[] after = Month.getAfter(before.getYear(), before.getMonth());
            return this.createFinance(after[1], after[0], before.getCash());
        }
        else {
            return before;
        }
    }
    
    public Object[][] createFinanceModel() {
        Object[][] ret = new Object[finances.size()][4];
        for (int i = 0; i < ret.length; i++) {
            Finance f = finances.get(i);
            ret[i][0] = f.getYear();
            ret[i][1] = Month.getAllPolish()[f.getMonth()];
            ret[i][2] = (f.isClosed() ? "Tak" : "Nie");
            ret[i][3] = "Pokaż";
        }
        return ret;
    }
    
    public Finance getFinance(int i) {
        return finances.get(i);
    }
    
    public Finance getFinance(int month, int year) {
        for (Finance f : finances) {
            if (f.getMonth() == month && f.getYear() == year) {
                return f;
            }
        }
        return null;
    }
    
    public Finance getLastClosedFinance() {
        Finance ret = null;
        for (Finance finance : finances) {
            if (finance.isClosed()) {
                ret = finance;
            }
        }
        return ret;
    }
    
    public final ArrayList<Finance> finances;
    public final DecimalFormat df = new DecimalFormat();
    
    public static boolean save(Project p, File file, Format format) {
        if (file.getName().toUpperCase().endsWith(format.getFileEnd().toUpperCase())) {
            try {
                format.save(p, file);
                return true;
            } catch (Exception ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public static Project load(File file) {
        Format f = null;
        for (Format form : Format.getAllFormats()) {
            if (file.getName().toUpperCase().endsWith(form.getFileEnd().toUpperCase())) {
                f = form;
            }
        }
        if (f != null) {
            try {
                return f.load(file);
            } catch (Exception ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
