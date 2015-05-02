package st.finanse.proj;

import st.finanse.mod.finance.Finance;
import java.math.BigDecimal;
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
    }
    
    public void createFinance(int month, int year, BigDecimal start) {
        finances.add(new Finance(month, year, start));
    }
    
    public Object[][] createFinanceModel() {
        Object[][] ret = new Object[finances.size()][4];
        System.out.println(ret.length);
        for (int i = 0; i < ret.length; i++) {
            Finance f = finances.get(i);
            ret[i][0] = f.getYear();
            ret[i][1] = Month.getAllPolish()[f.getMonth()];
            ret[i][2] = (f.isClosed() ? "Tak" : "Nie");
            ret[i][3] = "Pokaż";
        }
        return ret;
    }
    
    private final ArrayList<Finance> finances;
}
