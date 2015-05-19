package st.finanse.mod.chart;

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
        super.update();
    }
    
    private final Finance f;
}
