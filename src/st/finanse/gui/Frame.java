package st.finanse.gui;

import java.io.File;
import javax.swing.JFileChooser;
import st.finanse.mod.finance.NewFinance;
import st.finanse.mod.finance.AllFinance;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import st.finanse.Format;
import st.finanse.UpdateI;
import st.finanse.mod.finance.FinanceSum;
import st.finanse.proj.Project;

/**
 *
 * @author ShookTea
 */
public class Frame extends javax.swing.JFrame {
    public static final Frame frame = new Frame();
    /**
     * Creates new form Frame
     */
    private Frame() {
        initComponents();
        jfc = new JFileChooser();
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setMultiSelectionEnabled(false);
    }
    
    private void initJfcFilters(boolean save) {
        jfc.resetChoosableFileFilters();
        for (Format f : Format.getChosableFormat()) {
            jfc.addChoosableFileFilter(f.createFileFilter());
        }
        if (save) {
            jfc.setFileFilter(Format.getDefaultFormat().createFileFilter());
        }
        else {
            jfc.setFileFilter(Format.getAllFileFilter());
            jfc.addChoosableFileFilter(Format.getDefaultFormat().createFileFilter());
        }
    }
    
    private final JFileChooser jfc;
    
    public static void addJIF(JInternalFrame jif) {
        frame.desktop.add(jif);
        jif.setVisible(true);
    }
    
    public static void updateAll() {
        for (JInternalFrame jif : frame.desktop.getAllFrames()) {
            if (jif instanceof UpdateI) {
                ((UpdateI)jif).updateData();
            }
        }
    }
    
    public static void removeAllJIF() {
        for (JInternalFrame jif : frame.desktop.getAllFrames()) {
            jif.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        newFile = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveFile = new javax.swing.JMenuItem();
        saveAsFile = new javax.swing.JMenuItem();
        openFile = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        quitFile = new javax.swing.JMenuItem();
        finanse = new javax.swing.JMenu();
        allFinanse = new javax.swing.JMenuItem();
        newFinanse = new javax.swing.JMenuItem();
        sumFinanse = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Finanse");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                windowClosingListener(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        file.setText("Plik");

        newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFile.setText("Nowy");
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });
        file.add(newFile);
        file.add(jSeparator1);

        saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveFile.setText("Zapisz");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        file.add(saveFile);

        saveAsFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsFile.setText("Zapisz jako");
        saveAsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsFileActionPerformed(evt);
            }
        });
        file.add(saveAsFile);

        openFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFile.setText("Wczytaj");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        file.add(openFile);
        file.add(jSeparator2);

        quitFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitFile.setText("Zakończ");
        quitFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitFileActionPerformed(evt);
            }
        });
        file.add(quitFile);

        jMenuBar1.add(file);

        finanse.setText("Finanse");

        allFinanse.setText("Wszystkie tabele");
        allFinanse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allFinanseActionPerformed(evt);
            }
        });
        finanse.add(allFinanse);

        newFinanse.setText("Nowa tabela");
        newFinanse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFinanseActionPerformed(evt);
            }
        });
        finanse.add(newFinanse);

        sumFinanse.setText("Podsumowanie");
        sumFinanse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumFinanseActionPerformed(evt);
            }
        });
        finanse.add(sumFinanse);

        jMenuBar1.add(finanse);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newFinanseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFinanseActionPerformed
        addJIF(new NewFinance());
    }//GEN-LAST:event_newFinanseActionPerformed

    private void allFinanseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allFinanseActionPerformed
        addJIF(new AllFinance());
    }//GEN-LAST:event_allFinanseActionPerformed

    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileActionPerformed
        
        int i = Project.project.changed ? JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz zapisać obecny projekt przed utworzeniem nowego?",
                "Potwierdzenie",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) : JOptionPane.NO_OPTION;
        if (i != JOptionPane.CANCEL_OPTION) {
            if (i == JOptionPane.YES_OPTION) {
                saveFileActionPerformed(null);
            }
            Project.project = new Project();
        }
    }//GEN-LAST:event_newFileActionPerformed

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
        int i = Project.project.changed ? JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz zapisać obecny projekt przed wczytaniem innego?",
                "Potwierdzenie",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) : JOptionPane.NO_OPTION;
        if (i != JOptionPane.CANCEL_OPTION) {
            if (i == JOptionPane.YES_OPTION) {
                saveFileActionPerformed(null);
            }
            initJfcFilters(false);
            if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = jfc.getSelectedFile();
                Project.load(f);
                Project.project.file = f;
                Project.project.format = Format.getFormatByFile(f);
            }
            Frame.removeAllJIF();
            Frame.updateAll();
        }
    }//GEN-LAST:event_openFileActionPerformed

    private void saveAsFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsFileActionPerformed
        initJfcFilters(true);
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = jfc.getSelectedFile();
            Format form = Format.getFormatByFile(f);
            if (f != null && form != null) {
                Project.save(f, form);
                Project.project.file = f;
                Project.project.format = form;
            }
        }
    }//GEN-LAST:event_saveAsFileActionPerformed

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileActionPerformed
        if (Project.project.file == null || Project.project.format == null) {
            saveAsFileActionPerformed(null);
        }
        else {
            Project.save(Project.project.file, Project.project.format);
        }
    }//GEN-LAST:event_saveFileActionPerformed

    private void windowClosingListener(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosingListener
        int i = Project.project.changed ? JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz zapisać obecny projekt przed zamknięciem programu?",
                "Potwierdzenie",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) : JOptionPane.NO_OPTION;
        if (i != JOptionPane.CANCEL_OPTION) {
            if (i == JOptionPane.YES_OPTION) {
                saveFileActionPerformed(null);
            }
            this.dispose();
        }
    }//GEN-LAST:event_windowClosingListener

    private void quitFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitFileActionPerformed
        windowClosingListener(null);
    }//GEN-LAST:event_quitFileActionPerformed

    private void sumFinanseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumFinanseActionPerformed
        Frame.addJIF(new FinanceSum());
    }//GEN-LAST:event_sumFinanseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem allFinanse;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu file;
    private javax.swing.JMenu finanse;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JMenuItem newFinanse;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JMenuItem quitFile;
    private javax.swing.JMenuItem saveAsFile;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JMenuItem sumFinanse;
    // End of variables declaration//GEN-END:variables
}
