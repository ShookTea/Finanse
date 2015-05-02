package st.finanse.mod.finance;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
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
    
    public DefaultTableModel createTableModel() {
        if (isClosed) {
            return new DefaultTableModel(getTableModelData(), getTableModelTitles()) {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
        }
        else {
            return new DefaultTableModel(getTableModelData(), getTableModelTitles())  {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
        }
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
            ret[i][2] = Project.project.df.format(e.cash + " zł");
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