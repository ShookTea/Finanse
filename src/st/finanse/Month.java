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
    
    public static int getMonthID(String name) {
        switch (name.toLowerCase()) {
            case "styczeń":
                return 0;
            case "luty":
                return 1;
            case "marzec":
                return 2;
            case "kwiecień":
                return 3;
            case "maj":
                return 4;
            case "czerwiec":
                return 5;
            case "lipiec":
                return 6;
            case "sierpień":
                return 7;
            case "wrzesień":
                return 8;
            case "październik":
                return 9;
            case "listopad":
                return 10;
            case "grudzień":
                return 11;
            default:
                return 12;
        }
    }
    
    public static int[] getBefore(int year, int month) {
        if (month == 0) {
            return new int[] {year-1, 11};
        }
        else {
            return new int[] {year, month-1};
        }
    }
    
    public static int[] getAfter(int year, int month) {
        if (month == 11) {
            return new int[] {year+1, 0};
        }
        else {
            return new int[] {year, month+1};
        }
    }
    
    
    private static final int[] maxDays = {31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
}
