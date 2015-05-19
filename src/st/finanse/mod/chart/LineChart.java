package st.finanse.mod.chart;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Norbert "ShookTea" Kowalik
 */
public class LineChart extends Chart {
    
    public void addLine(Line l) {
        lines.add(l);
    }
    
    public void removeAllLines() {
        lines.clear();
    }
    
    @Override
    public void update() {
        minValue = Integer.MAX_VALUE;
        maxValue = Integer.MIN_VALUE;
        length = Integer.MIN_VALUE;
        for (Line l : lines) {
            if (l.getMaxValue() > maxValue) {
                maxValue = l.getMaxValue();
            }
            if (l.getLength() > length) {
                length = l.getLength();
            }
            if (l.getMinValue() < minValue) {
                minValue = l.getMinValue();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        
    }
    
    private int length;
    private int maxValue;
    private int minValue;
    private final ArrayList<Line> lines = new ArrayList();
}
