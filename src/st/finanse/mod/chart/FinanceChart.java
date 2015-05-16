package st.finanse.mod.chart;

import java.awt.Graphics2D;
import st.finanse.mod.finance.Finance;

/**
 *
 * @author Norbert "ShookTea" Kowalik
 */
public class FinanceChart extends LineChart {
    public FinanceChart(Finance f) {
        this.f = f;
        update();
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics2D g) {
        
    }
    
    private final Finance f;
}
