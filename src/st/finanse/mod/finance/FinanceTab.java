package st.finanse.mod.finance;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import st.finanse.Month;
import st.finanse.UpdateI;
import st.finanse.gui.Frame;
import st.finanse.proj.Project;

/**
 *
 * @author ShookTea
 */
public class FinanceTab extends javax.swing.JInternalFrame implements UpdateI {

    public FinanceTab(Finance f) {
        this.f = f;
        initComponents();
        this.getRootPane().setDefaultButton(add);
        updateData();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        date = new javax.swing.JLabel();
        initValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        day = new javax.swing.JSpinner();
        date2 = new javax.swing.JLabel();
        event = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        value = new javax.swing.JFormattedTextField();
        add = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bilance = new javax.swing.JLabel();
        actual = new javax.swing.JLabel();
        adds = new javax.swing.JLabel();
        subtracts = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        closeAndCreate = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tabela finansowa");

        date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date.setText("jLabel1");

        initValue.setText("jLabel1");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Data", "Tytuł", "Kwota", "Usuń"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Data:");

        day.setModel(new javax.swing.SpinnerNumberModel(Month.getActualDay(), 1, Month.getMaxDay(f.getMonth()), 1));
        day.setNextFocusableComponent(title);

        date2.setText("jLabel2");

        event.setText("Święto (oznacz na czerwono)");

        jLabel2.setText("Tytuł:");

        title.setNextFocusableComponent(value);

        jLabel3.setText("Kwota:");

        value.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        value.setNextFocusableComponent(add);

        add.setText("Dodaj");
        add.setNextFocusableComponent(day);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        bilance.setText("Bilans:");

        actual.setText("Aktualna kwota:");

        adds.setForeground(new java.awt.Color(51, 102, 0));
        adds.setText("Zyski:");

        subtracts.setForeground(new java.awt.Color(153, 0, 0));
        subtracts.setText("Straty:");

        close.setText("Zamknij miesiąc");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        closeAndCreate.setText("Zamknij miesiąc i utwórz nowy");
        closeAndCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAndCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add))
                    .addComponent(date)
                    .addComponent(initValue)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(event))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bilance)
                            .addComponent(actual))
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subtracts)
                            .addComponent(adds))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(closeAndCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(date)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(initValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date2)
                    .addComponent(event))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(close)
                    .addComponent(adds)
                    .addComponent(actual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bilance)
                    .addComponent(subtracts)
                    .addComponent(closeAndCreate))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        f.addEntry(Integer.parseInt(day.getValue().toString()), title.getText(), event.isSelected(), new BigDecimal(value.getValue().toString()).setScale(2, RoundingMode.HALF_UP));
        title.setText("");
        value.setText("");
        Frame.updateAll();
        day.requestFocus();
    }//GEN-LAST:event_addActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        f.close();
        Frame.updateAll();
    }//GEN-LAST:event_closeActionPerformed

    private void closeAndCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAndCreateActionPerformed
        f.close();
        Frame.updateAll();
    }//GEN-LAST:event_closeAndCreateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actual;
    private javax.swing.JButton add;
    private javax.swing.JLabel adds;
    private javax.swing.JLabel bilance;
    private javax.swing.JButton close;
    private javax.swing.JButton closeAndCreate;
    private javax.swing.JLabel date;
    private javax.swing.JLabel date2;
    private javax.swing.JSpinner day;
    private javax.swing.JCheckBox event;
    private javax.swing.JLabel initValue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel subtracts;
    private javax.swing.JTable table;
    private javax.swing.JTextField title;
    private javax.swing.JFormattedTextField value;
    // End of variables declaration//GEN-END:variables

    private final Finance f;
    
    @Override
    public void updateData() {
        String dS = Month.getAllPolish()[f.getMonth()] + " " + f.getYear();
        date.setText(dS);
        date2.setText(dS);
        String inS = Project.project.df.format(f.getStart());
        initValue.setText("Kwota początkowa: " + inS + " zł");
        createTableModel();
        day.requestFocus();
        day.setEnabled(!f.isClosed());
        title.setEditable(!f.isClosed());
        value.setEditable(!f.isClosed());
        add.setEnabled(!f.isClosed());
        close.setEnabled(!f.isClosed());
        closeAndCreate.setEnabled(!f.isClosed());
        event.setEnabled(!f.isClosed());
        title.setBackground(f.isClosed() ? Color.LIGHT_GRAY : Color.WHITE);
        value.setBackground(f.isClosed() ? Color.LIGHT_GRAY : Color.WHITE);
        
        BigDecimal cashAdd = f.getAdds();
        BigDecimal cashSub = f.getSubtracts();
        BigDecimal cashBil = cashAdd.subtract(cashSub);
        BigDecimal cashFinal = f.getStart().add(cashBil);
        adds.setText("Przychody: " + Project.project.df.format(cashAdd) + " zł");
        subtracts.setText("Straty: " + Project.project.df.format(cashSub) + " zł");
        bilance.setText("Bilans: " + Project.project.df.format(cashBil) + " zł");
        actual.setText("Aktualna kwota: " + Project.project.df.format(cashFinal) + " zł");
        
        if (cashBil.signum() == -1) {
            bilance.setForeground(new java.awt.Color(153, 0, 0));
        }
        else if (cashBil.signum() == 1) {
            bilance.setForeground(new java.awt.Color(51, 102, 0));
        }
        else {
            bilance.setForeground(Color.BLACK);
        }
    }
    
    private void createTableModel() {
        table.setModel(f.createTableModel());
    }

}
