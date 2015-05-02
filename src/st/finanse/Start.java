package st.finanse;

import st.finanse.gui.Frame;

/**
 *
 * @author ShookTea
 */
public class Start implements st.init.StartI {

    @Override
    public void start(String[] args) {
        Frame.frame.setVisible(true);
    }

}
