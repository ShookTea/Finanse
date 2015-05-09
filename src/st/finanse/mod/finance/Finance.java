package st.finanse.mod.finance;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import st.finanse.Month;
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
    
    public Finance(Finance before) {
        int[] after = Month.getAfter(before.year, before.month);
        this.year = after[0];
        this.month = after[1];
        this.start = before.getCash();
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
            cash = cash.add(entry.cash);
        }
        return cash;
    }
    
    public void addEntry(int day, String title, boolean isEvent, BigDecimal cash) {
        Project.project.changed = true;
        entries.add(new FinanceEntry(day, title, isEvent, cash));
        Finance.addTitleBase(title);
    }
    
    public void removeEntry(int id) {
        Project.project.changed = true;
        Finance.removeTitleBase(entries.get(id).title);
        entries.remove(id);
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void close() {
        Project.project.changed = true;
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
    
    public FinanceEntry[] getEntries() {
        return entries.toArray(new FinanceEntry[entries.size()]);
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
    
    public static Finance getBefore(Finance f) {
        int[] i = Month.getBefore(f.year, f.month);
        return Project.project.getFinance(i[1], i[0]);
    }
    
    public static Finance getAfter(Finance f) {
        int[] i = Month.getAfter(f.year, f.month);
        return Project.project.getFinance(i[1], i[0]);
    }
    
    public static void addTitleBase(String title) {
        if (titles.containsKey(title)) {
            titles.replace(title, titles.get(title) + 1);
        }
        else {
            titles.put(title, 1);
        }
    }
    
    public static void removeTitleBase(String title) {
        if (titles.containsKey(title)) {
            int v = titles.get(title);
            if (v == 1) {
                titles.remove(title);
            }
            else {
                titles.replace(title, v-1);
            }
        }
    }
    
    public static String getTitleBase(String title) {
        String ret = "";
        int v = 0;
        for (String t : titles.keySet()) {
            if (t.startsWith(title) && titles.get(t) > v) {
                ret = t;
                v = titles.get(t);
            }
        }
        if (v == 0) {
            return title;
        }
        return ret;
    }
    
    public static String[] getKeys() {
        return titles.keySet().toArray(new String[titles.size()]);
    }
    
    public static int getWeight(String key) {
        return titles.get(key);
    }
    
    private static HashMap<String, Integer> titles = new HashMap();

    public static class FinanceEntry {
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

}