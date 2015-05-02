package st.finanse.mod.finance;

import java.math.BigDecimal;
import java.util.ArrayList;

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