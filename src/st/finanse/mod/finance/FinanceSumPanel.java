package st.finanse.mod.finance;

import java.math.BigDecimal;
import st.finanse.Month;
import st.finanse.UpdateI;
import st.finanse.mod.finance.Finance.FinanceEntry;

/**
 *
 * @author ShookTea
 */
public class FinanceSumPanel extends javax.swing.JPanel implements UpdateI {

    public FinanceSumPanel(Finance[] finances) {
        this.months = finances;
        initComponents();
        updateData();
    }
    
    private final Finance[] months;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", null, null, null},
                {"b", null, null, null},
                {null, "c", null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateData() {
        table.setModel(new javax.swing.table.DefaultTableModel(tableValue(), tableHeader()) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
    }
    
    private Object[][] tableValue() {
        String[] keys = Finance.getKeys();
        int height = keys.length;
        int width = months.length + 2;
        Object[][] value = new Object[height][width];
        for (int k = 0; k < height; k++) {
            value[k][0] = keys[k];
            BigDecimal yearly = new BigDecimal("0.0");
            for (int i = 0; i < months.length; i++) {
                Finance month = months[i];
                BigDecimal bd = new BigDecimal("0.0");
                for (FinanceEntry fe : month.getEntries()) {
                    if (fe.title.equals(keys[k])) {
                        bd = bd.add(fe.cash);
                        yearly = yearly.add(fe.cash);
                    }
                }
                value[k][i+1] = bd;
            }
            value[k][months.length + 1] = yearly;
        }
        return value;
    }
    
    private String[] tableHeader() {
        String[] header = new String[months.length + 2];
        header[0] = "Tytuły";
        for (int i = 0; i < months.length; i++) {
            header[i+1] = Month.getAllPolish()[months[i].getMonth()];
        }
        header[header.length - 1] = "Rok";
        return header;
    }

}