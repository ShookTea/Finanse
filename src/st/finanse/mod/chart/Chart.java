package st.finanse.mod.chart;

import java.awt.Graphics2D;
import st.finanse.gui.Frame;

/**
 *
 * @author Norbert "ShookTea" Kowalik
 */
public abstract class Chart {
    public abstract void update();
    public abstract void draw(Graphics2D g);
    
    public void createJIF() {
        Frame.addJIF(new ChartWindow(this));
    }
}
