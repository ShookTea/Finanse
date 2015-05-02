package st.finanse.proj;

import st.finanse.mod.finance.Finance;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import st.finanse.Month;

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
    
    public Finance getLastClosedFinance() {
        Finance ret = null;
        for (int i = 0; i < finances.size(); i++) {
            if (finances.get(i).isClosed()) {
                ret = finances.get(i);
            }
        }
        return ret;
    }
    
    private final ArrayList<Finance> finances;
    public final DecimalFormat df = new DecimalFormat();
}
