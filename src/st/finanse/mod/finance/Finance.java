package st.finanse.mod.finance;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import st.finanse.mod.finance.FinanceTab.TableModel;
import st.finanse.proj.Project;

/**
 * Klasa zarządzająca pojedynczym miesiącem finansowym
 * @author Norbert "ShookTea" Kowalik
 */
public class Finance {
    public Finance(int month, int year, BigDecimal start) {
        this.month = month;
        this.year = year;
        this.start = start;
        this.entries = new ArrayList();
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getYear() {
        return year;
    }
    
    public BigDecimal getStart() {
        return start;
    }
    
    public BigDecimal getCash() {
        BigDecimal cash = start;
        for (FinanceEntry entry : entries) {
            cash.add(entry.cash);
        }
        return cash;
    }
    
    public void addEntry(int day, String title, boolean isEvent, BigDecimal cash) {
        entries.add(new FinanceEntry(day, title, isEvent, cash));
    }
    
    public void removeEntry(int id) {
        entries.remove(id);
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void close() {
        isClosed = true;
    }
    
    public BigDecimal getAdds() {
        BigDecimal ret = new BigDecimal("0.0");
        for (FinanceEntry e : entries) {
            if (e.cash.signum() == 1) {
                ret = ret.add(e.cash);
            }
        }
        return ret;
    }
    
    public BigDecimal getSubtracts() {
        BigDecimal ret = new BigDecimal("0.0");
        for (FinanceEntry e : entries) {
            if (e.cash.signum() == -1) {
                ret = ret.subtract(e.cash);
            }
        }
        return ret;
    }
    
    public DefaultTableModel createTableModel() {
        TableModel tm = new TableModel(getTableModelData(), getTableModelTitles(), isClosed);
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).cash.signum() == 1) {
                tm.setRowColour(i, Color.BLUE);
            }
            else if (entries.get(i).isEvent) {
                tm.setRowColour(i, Color.RED);
            }
            else {
                tm.setRowColour(i, Color.WHITE);
            }
        }
        return tm;
    }
    
    private Object[][] getTableModelData() {
        Object[][] ret;
        if (isClosed) {
            ret = new Object[entries.size()][3];
        }
        else {
            ret = new Object[entries.size()][4];
        }
        for (int i = 0; i < ret.length; i++) {
            FinanceEntry e = entries.get(i);
            ret[i][0] = e.day + "-" + (month+1) + "-" + year;
            ret[i][1] = e.title;
            ret[i][2] = Project.project.df.format(e.cash) + " zł";
            if (!isClosed) {
                ret[i][3] = "Usuń";
            }
        }
        return ret;
    }
    
    private String[] getTableModelTitles() {
        if (isClosed) {
            return new String[] {"Data", "Tytuł", "Kwota"};
        }
        else {
            return new String[] {"Data", "Tytuł", "Kwota", "Usuń"};
        }
    }
    
    private boolean isClosed = false;
    private final int month;
    private final int year;
    private final BigDecimal start;
    private final ArrayList<FinanceEntry> entries;
}

class FinanceEntry {
    public FinanceEntry(int day, String title, boolean isEvent, BigDecimal cash) {
        this.day = day;
        this.title = title;
        this.isEvent = isEvent;
        this.cash = cash;
    }
    
    public final int day;
    public final String title;
    public final boolean isEvent;
    public final BigDecimal cash;
}