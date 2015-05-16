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
    
    private final ArrayList<Line> lines = new ArrayList();
    
    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
