package interactivesudokugame;

import static interactivesudokugame.GameFrame.getGrid;
import static interactivesudokugame.GameFrame.selectedGridColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author brorie3
 */
public class ThemePickerWindow extends javax.swing.JFrame {

    private GameFrame gameFrame;
    private Color color;

    /**
     * Creates new form ThemePickerWindow
     */
    public ThemePickerWindow() {
        initComponents();
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public ThemePickerWindow(GameFrame gameFrame) {
        initComponents();
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setTitle("Personalize Settings");

        this.gameFrame = gameFrame;

    }
    
    public Color getColor() {
        return color;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Background Color: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light", "Dark (Default)", "Blue", "Red", "Green", "Orange", "Yellow", "Pink" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Grid Color: ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "White", "Black", "Red", "Green", "Blue", "Orange", "Yellow" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Font Size: ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Small", "Medium (Default)", "Large" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, 146, Short.MAX_VALUE))))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(332, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        JComboBox cb = (JComboBox) evt.getSource();
        String item = String.valueOf(cb.getSelectedItem());
        JButton[][] grid = getGrid();
        //Orange, Yellow, Pink
        switch (item) {

            case "Light":
                gameFrame.getContentPane().setBackground(Color.white);
                break;
            case "Dark (Default)":
                gameFrame.getContentPane().setBackground(Color.black);
                break;
            case "Blue":
                gameFrame.getContentPane().setBackground(Color.blue);
                break;
            case "Red":
                gameFrame.getContentPane().setBackground(Color.red);
                break;
            case "Green":
                gameFrame.getContentPane().setBackground(Color.green);
                break;
            case "Orange":
                gameFrame.getContentPane().setBackground(Color.orange);
                break;
            case "Yellow":
                gameFrame.getContentPane().setBackground(Color.yellow);
                break;
            case "Pink":
                gameFrame.getContentPane().setBackground(Color.pink);
                break;
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        String item = String.valueOf(cb.getSelectedItem());
        JButton[][] grid = getGrid();
        switch (item) {
            //White, Black, Red, Green, Blue, Orange, Yellow, Pink
            case "White":
                color = Color.white;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.white);
                            grid[i][j].setForeground(Color.black);
                        }
                    }
                }

                break;
            case "Black":
                color = Color.BLACK;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.black);
                            grid[i][j].setForeground(Color.white);
                        }
                    }
                }
                break;
            case "Red":
                color = Color.RED;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.red);
                            grid[i][j].setForeground(Color.black);
                        }
                    }
                }
                break;
            case "Green":
                color = Color.green;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.green);
                            grid[i][j].setForeground(Color.black);
                        }
                    }
                }
                break;
            case "Blue":
                color = Color.blue;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.blue);
                            grid[i][j].setForeground(Color.white);
                        }
                    }
                }
                break;
            case "Orange":
                color = Color.orange;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.orange);
                            grid[i][j].setForeground(Color.black);
                        }
                    }
                }
                break;
            case "Yellow":
                color = Color.yellow;
                selectedGridColor(color);
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (grid[i][j].getBackground() != Color.gray) {
                            grid[i][j].setBackground(Color.yellow);
                            grid[i][j].setForeground(Color.black);
                        }
                    }
                }

                break;
            
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        String item = String.valueOf(cb.getSelectedItem());
        JButton[][] grid = getGrid();
        switch (item) {
            case "Small":
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        grid[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                    }
                }
                break;
            case "Medium (Default)":
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        grid[i][j].setFont(new Font("Arial", Font.PLAIN, 30));
                    }
                }
                break;
            case "Large":
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        grid[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                    }
                }
                break;
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThemePickerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemePickerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemePickerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemePickerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemePickerWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}