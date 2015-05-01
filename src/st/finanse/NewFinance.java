package st.finanse;

import java.math.BigDecimal;
import java.util.Calendar;
import st.finanse.proj.Project;

/**
 *
 * @author ShookTea
 */
public class NewFinance extends javax.swing.JInternalFrame {

    /** Creates new form NewFinance */
    public NewFinance() {
        initComponents();
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        y = Integer.parseInt(("" + y).substring(2));
        year.setValue(y);
        month.setSelectedIndex(c.get(Calendar.MONTH));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valueGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        year = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        valueFromMonth = new javax.swing.JRadioButton();
        valueNew = new javax.swing.JRadioButton();
        value = new javax.swing.JFormattedTextField();
        create = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Nowa tabela");

        jLabel1.setText("Rok:       20");

        year.setModel(new javax.swing.SpinnerNumberModel(15, 0, 99, 1));

        jLabel2.setText("Miesiąc:");

        month.setModel(new javax.swing.DefaultComboBoxModel(Month.getAllPolish()));

        jLabel3.setText("Kwota początkowa:");

        valueGroup.add(valueFromMonth);
        valueFromMonth.setText("Z poprzedniego miesiąca (musi być zamknięty)");

        valueGroup.add(valueNew);
        valueNew.setSelected(true);
        valueNew.setText("Nowa wartość:");

        value.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));

        create.setText("Stwórz tabelę");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(month, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(valueNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(value, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                            .addComponent(valueFromMonth)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(create)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueFromMonth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueNew)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(create)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        int y = Integer.parseInt("20" + year.getValue());
        int m = month.getSelectedIndex();
        BigDecimal cash;
        if (valueNew.isSelected()) {
            cash = new BigDecimal(value.getValue().toString());
        }
        else {
            cash = new BigDecimal("0.0");
        }
        Project.project.createFinance(m, y, cash);
    }//GEN-LAST:event_createActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton create;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox month;
    private javax.swing.JFormattedTextField value;
    private javax.swing.JRadioButton valueFromMonth;
    private javax.swing.ButtonGroup valueGroup;
    private javax.swing.JRadioButton valueNew;
    private javax.swing.JSpinner year;
    // End of variables declaration//GEN-END:variables
}