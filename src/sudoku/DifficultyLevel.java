/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Username
 */
public class DifficultyLevel extends javax.swing.JFrame {

    private static String dificultyString;
    private static Frame frame;
    private static boolean visible = true;
    private Buttons button;
    private static boolean buttonFinished = false;

    public static boolean isButtonFinished() {
        return buttonFinished;
    }

    public static void setButtonFinished(boolean buttonFinished) {
        DifficultyLevel.buttonFinished = buttonFinished;
    }
    
    public static boolean getVisible() {
        return visible;
    }

    public static String getDificultyString() {
        return dificultyString;
    }

    public DifficultyLevel(final Frame neki) {
        this.setAlwaysOnTop(true);
        frame = neki;
        neki.setFocusable(false);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Easy = new javax.swing.JButton();
        Test = new javax.swing.JButton();
        Medium = new javax.swing.JButton();
        Hard = new javax.swing.JButton();

        setResizable(false);

        Easy.setText("Easy");
        Easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EasyActionPerformed(evt);
            }
        });

        Test.setText("Test");
        Test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestActionPerformed(evt);
            }
        });

        Medium.setText("Medium");
        Medium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MediumActionPerformed(evt);
            }
        });

        Hard.setText("Hard");
        Hard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Easy, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hard, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Medium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Test, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Easy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Medium, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Test, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EasyActionPerformed
            frame.dispose();
            frame = new Frame();
            frame.setVisible(true);
            button = new Buttons("Lako", 60);
            button.setVisible(true);
            frame.add(button);
            frame.pack();
            this.setVisible(false);
            frame.setVisible(true);
            visible = false;
            buttonFinished = button.isFisnished();
            frame.timer.start();
    }//GEN-LAST:event_EasyActionPerformed

    private void MediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediumActionPerformed
        frame.dispose();
        frame = new Frame();
        frame.setVisible(true);
        button = new Buttons("Srednje", 60);
        button.setVisible(true);
        frame.add(button);
        frame.pack();
        this.setVisible(false);
        frame.setVisible(true);
        visible = false;
        buttonFinished = button.isFisnished();
        frame.timer.start();
    }//GEN-LAST:event_MediumActionPerformed

    private void TestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestActionPerformed
        frame.dispose();
        frame = new Frame();
        frame.setVisible(true);
        button = new Buttons("Test", 5);
        button.setVisible(true);
        frame.add(button);
        frame.pack();
        this.setVisible(false);
        frame.setVisible(true);
        visible = false;
        buttonFinished = button.isFisnished();
        frame.timer.start();
    }//GEN-LAST:event_TestActionPerformed

    private void HardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HardActionPerformed
        frame.dispose();
        frame = new Frame();
        frame.setVisible(true);
        button = new Buttons("Tesko", 60);
        button.setVisible(true);
        frame.add(button);
        frame.pack();
        this.setVisible(false);
        frame.setVisible(true);
        visible = false;
        buttonFinished = button.isFisnished();
        frame.timer.start();
    }//GEN-LAST:event_HardActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Easy;
    private javax.swing.JButton Hard;
    private javax.swing.JButton Medium;
    private javax.swing.JButton Test;
    // End of variables declaration//GEN-END:variables
}