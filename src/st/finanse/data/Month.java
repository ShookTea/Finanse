package st.finanse.data;

public class Month {
    public Month(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthName() {
        if (monthName == null) {
            monthName = reloadMonthName();
        }
        return monthName;
    }

    private String reloadMonthName() {
        switch (month) {
            case  1: return "Styczeń";
            case  2: return "Luty";
            case  3: return "Marzec";
            case  4: return "Kwiecień";
            case  5: return "Maj";
            case  6: return "Czerwiec";
            case  7: return "Lipiec";
            case  8: return "Sierpień";
            case  9: return "Wrzesień";
            case 10: return "Październik";
            case 11: return "Listopad";
            case 12: return "Grudzień";
            default: return "[null]";
        }
    }

    public int getMaxDays() {
        if (maxDays == -1) {
            maxDays = reloadMaxDays();
        }
        return maxDays;
    }

    private int reloadMaxDays() {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear() ? 29 : 28;
            default:
                return -1;
        }
    }

    private boolean isLeapYear() {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        else {
            return year % 4 == 0;
        }
    }

    private final int year;
    private final int month;
    private String monthName = null;
    private int maxDays = -1;
}
