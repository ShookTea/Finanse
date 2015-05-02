package st.finanse;

import java.util.Calendar;

/**
 * Enum miesięcy.
 * @author Norbert "ShookTea" Kowalik
 */
public enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
    
    public static String[] getAllPolish() {
        return new String[] {
            "Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec",
            "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"
        };
    }
    
    public static int getActualDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getMaxDay(int month) {
        if (month == 1) { //luty
            int year = Calendar.getInstance().get(Calendar.YEAR);
            if (year % 100 == 0) { //podzielny przez sto
                year /= 100;
            }
            if (year % 4 == 0) { //rok przestępny
                return 29;
            }
            return 28;
        }
        return maxDays[month];
    }
    
    
    private static final int[] maxDays = {31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
}
