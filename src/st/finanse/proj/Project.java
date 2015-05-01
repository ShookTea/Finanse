package st.finanse.proj;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    
    private final ArrayList<Finance> finances;
}
