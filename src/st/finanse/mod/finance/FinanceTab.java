package st.finanse.mod.finance;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
        before = new javax.swing.JButton();
        after = new javax.swing.JButton();

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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Data:");

        day.setModel(new javax.swing.SpinnerNumberModel(Month.getActualDay(), 1, Month.getMaxDay(f.getMonth()), 1));
        day.setNextFocusableComponent(title);

        date2.setText("jLabel2");

        event.setText("Święto (oznacz na czerwono)");

        jLabel2.setText("Tytuł:");

        title.setNextFocusableComponent(value);
        title.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                titleKeyReleased(evt);
            }
        });

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

        before.setText("<");
        before.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforeActionPerformed(evt);
            }
        });

        after.setText(">");
        after.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
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
                            .addComponent(closeAndCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(event))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(before)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date)
                                    .addComponent(initValue))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(after)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date)
                    .addComponent(before)
                    .addComponent(after))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(initValue)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
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
        jScrollPane1.getViewport().setViewPosition(new Point(0, table.getHeight()));
        day.requestFocus();
        day.getModel().setValue(((int)day.getModel().getValue()) - 1);
    }//GEN-LAST:event_addActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        f.close();
        Frame.updateAll();
    }//GEN-LAST:event_closeActionPerformed

    private void closeAndCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAndCreateActionPerformed
        f.close();
        Finance newF = Project.project.createFinance(f);
        Frame.addJIF(new FinanceTab(newF));
        Frame.updateAll();
    }//GEN-LAST:event_closeAndCreateActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int r = table.rowAtPoint(evt.getPoint());
        int c = table.columnAtPoint(evt.getPoint());
        if (c == 3) { //Usuń
            f.removeEntry(r);
            Frame.updateAll();
        }
    }//GEN-LAST:event_tableMouseClicked

    private void beforeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beforeActionPerformed
        f = Finance.getBefore(f);
        Frame.updateAll();
    }//GEN-LAST:event_beforeActionPerformed

    private void afterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afterActionPerformed
        f = Finance.getAfter(f);
        Frame.updateAll();
    }//GEN-LAST:event_afterActionPerformed

    private void titleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            String act = title.getText();
            String base = Finance.getTitleBase(act);
            if (!base.equals(act)) {
                title.setText(base);
                title.setSelectionStart(act.length());
                title.setSelectionEnd(base.length());
            }
        }
    }//GEN-LAST:event_titleKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actual;
    private javax.swing.JButton add;
    private javax.swing.JLabel adds;
    private javax.swing.JButton after;
    private javax.swing.JButton before;
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

    private Finance f;
    
    @Override
    public void updateData() {
        String dS = Month.getAllPolish()[f.getMonth()] + " " + f.getYear();
        date.setText(dS);
        date2.setText(dS);
        String inS = Project.project.df.format(f.getStart());
        initValue.setText("Kwota początkowa: " + inS + " zł");
        createTableModel();
        day.getModel().setValue(f.getLastDay() + 1);
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
        BigDecimal cashFinal = f.getCash();
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
        
        before.setEnabled(Finance.getBefore(f) != null);
        after.setEnabled(Finance.getAfter(f) != null);
    }
    
    private void createTableModel() {
        table.setModel(f.createTableModel());
        CellRenderer cr = new CellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cr);
        }
    }
    
    private class CellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            TableModel model = (TableModel) table.getModel();
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(model.getRowColour(row));
            if (model.getRowColour(row).equals(Color.WHITE)) {
                c.setForeground(Color.BLACK);
            }
            else {
                c.setForeground(Color.WHITE);
            }
            return c;
        }
    }
    
    public static class TableModel extends DefaultTableModel {
        public TableModel(Object[][] ob, String[] titles, boolean isClosed) {
            super(ob, titles);
            rowColours = Arrays.asList(new Color[ob.length]);
            if (isClosed) {
                types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                canEdit = new boolean [] {
                    false, false, false
                };
            }
            else {
                types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                canEdit = new boolean [] {
                    false, false, false, false
                };
            }
        }
        
        Class[] types;
        boolean[] canEdit;
        List<Color> rowColours;

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }

        public void setRowColour(int row, Color c) {
            rowColours.set(row, c);
            fireTableRowsUpdated(row, row);
        }

        public Color getRowColour(int row) {
            return rowColours.get(row);
        }
    }
}
