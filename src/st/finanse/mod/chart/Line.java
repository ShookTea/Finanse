package st.finanse.mod.chart;

import java.awt.Color;

/**
 * Klasa reprezentująca Linię w wykresie liniowym {@link LineChart}.
 * @author Norbert "ShookTea" Kowalik
 */
class Line {
    public Line(String title, Color c, int[][] values) {
        this.title = title;
        this.c = c;
        this.values = values;
    }
    
    public int getMinValue() {
        int minValue = Integer.MAX_VALUE;
        for (int[] x : values) {
            for (int y : x) {
                if (y < minValue) {
                    minValue = y;
                }
            }
        }
        return minValue;
    }
    
    public int getMaxValue() {
        int maxValue = Integer.MIN_VALUE;
        for (int[] x : values) {
            for (int y : x) {
                if (y > maxValue) {
                    maxValue = y;
                }
            }
        }
        return maxValue;
    }
    
    public int getLength() {
        return values.length;
    }
    
    public final Color c;
    public final int[][] values;
    public final String title;
}
