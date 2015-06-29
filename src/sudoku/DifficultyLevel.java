/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Username
 */
public class DifficultyLevel extends javax.swing.JFrame {

    private static String dificultyString;
    private static Frame frame;
    private Buttons button;
    private static int sizeOf;

    public static int getSizeOf() {
        return sizeOf;
    }    
    public static String getDificultyString() {
        return dificultyString;
    }
    public DifficultyLevel(final Frame neki) {
        this.setAlwaysOnTop(true);
        frame = neki;
        frame.setEnabled(false);
        neki.setFocusable(false);
        initComponents();
        velicina3.setSelected(true);
        sizeOf = 9;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Easy = new javax.swing.JButton();
        Custom = new javax.swing.JButton();
        Medium = new javax.swing.JButton();
        Hard = new javax.swing.JButton();
        velicina2 = new javax.swing.JCheckBox();
        velicina3 = new javax.swing.JCheckBox();
        velicina4 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        Easy.setText("Easy");
        Easy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EasyActionPerformed(evt);
            }
        });

        Custom.setText("Custom");
        Custom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomActionPerformed(evt);
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

        velicina2.setText("4x4");
        velicina2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velicina2ActionPerformed(evt);
            }
        });

        velicina3.setText("9x9");
        velicina3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velicina3ActionPerformed(evt);
            }
        });

        velicina4.setText("16x16");
        velicina4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velicina4ActionPerformed(evt);
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
                    .addComponent(Custom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(velicina2)
                    .addComponent(velicina3)
                    .addComponent(velicina4)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Easy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Medium, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(velicina2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(velicina3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Custom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Hard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(velicina4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EasyActionPerformed
        if(!(velicina2.isSelected()||velicina3.isSelected()||velicina4.isSelected()))
        {
            this.toBack();
            JOptionPane.showMessageDialog(new JFrame(), "Select sudoku size",
            "Warning", 0);
            frame.setEnabled(false);
            this.toFront();
        }
        else
        {
            frame.dispose();
            frame = new Frame();
            frame.setVisible(true);
            button = new Buttons((int) ((sizeOf*sizeOf)/2));
            dificultyString = "Lako"+ ((int)Math.sqrt(sizeOf));
            button.setVisible(true);
            frame.add(button);
            frame.pack();
            this.dispose();
            frame.timer.start();
        }
    }//GEN-LAST:event_EasyActionPerformed

    private void MediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediumActionPerformed
        if(!(velicina2.isSelected()||velicina3.isSelected()||velicina4.isSelected())){
                this.toBack();
                JOptionPane.showMessageDialog(new JFrame(), "Select sudoku size",
                "Warning", 0);
        }
        else
        {
            frame.dispose();
            frame = new Frame();
            frame.setVisible(true);
            button = new Buttons((int) (sizeOf*sizeOf)*2/3);
            dificultyString = "Srednje"+ ((int)Math.sqrt(sizeOf));
            button.setVisible(true);
            frame.add(button);
            frame.pack();
            this.dispose();
            frame.setVisible(true);
            frame.timer.restart();
        }
    }//GEN-LAST:event_MediumActionPerformed

    private void CustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomActionPerformed
        if(!(velicina2.isSelected()||velicina3.isSelected()||velicina4.isSelected())){
            this.toBack();
            JOptionPane.showMessageDialog(new JFrame(), "Select sudoku size",
            "Warning", 0);
        }
        else{
            String delete;
            boolean flag = false;
            do
            {
                delete = JOptionPane.showInputDialog("How much fields to delete?");
                if(String.valueOf(delete).equals("null"))
                    break;
                for(int i = 0; i < delete.length(); i++)
                    if(delete.charAt(i) <= '0' || delete.charAt(i) >= '9')
                    {
                        flag = false;
                        break;
                    }else
                        flag = true;

                if(flag 
                    && !(Integer.parseInt(delete) >= 0 
                    && Integer.parseInt(delete) <= sizeOf*sizeOf))
                            flag = false;
                if(!flag)
                    JOptionPane.showMessageDialog(null, 
                        "Please insert number between 0 and " + (sizeOf*sizeOf));
            }while(!flag);
            if(!String.valueOf(delete).equals("null")){
                frame.dispose();
                frame = new Frame();
                frame.setVisible(true);
                button = new Buttons(Integer.parseInt(delete));
                dificultyString = "Test"+ ((int)Math.sqrt(sizeOf));
                button.setVisible(true);
                frame.add(button);
                frame.pack();
                this.dispose();
                frame.timer.start();
            }
        }
    }//GEN-LAST:event_CustomActionPerformed

    private void HardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HardActionPerformed
        if(!(velicina2.isSelected()||velicina3.isSelected()||velicina4.isSelected())){
            this.toBack();
            JOptionPane.showMessageDialog(new JFrame(), "Select sudoku size",
            "Warning", 0);
        }else{
            frame.dispose();
            frame = new Frame();
            frame.setVisible(true);
            button = new Buttons((int) (sizeOf*sizeOf)*3/4);
            dificultyString = "Tesko"+ ((int)Math.sqrt(sizeOf));
            button.setVisible(true);
            frame.add(button);
            frame.pack();
            this.dispose();
            frame.timer.start();
        }
    }//GEN-LAST:event_HardActionPerformed

    private void velicina2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velicina2ActionPerformed
        if(velicina3.isSelected())
            velicina3.setSelected(false);
        else if(velicina4.isSelected())
            velicina4.setSelected(false);
        sizeOf = 4;
    }//GEN-LAST:event_velicina2ActionPerformed

    private void velicina3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velicina3ActionPerformed
        if(velicina2.isSelected())
            velicina2.setSelected(false);
        else if(velicina4.isSelected())
            velicina4.setSelected(false);
        sizeOf = 9;
    }//GEN-LAST:event_velicina3ActionPerformed

    private void velicina4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velicina4ActionPerformed
        if(velicina3.isSelected())
            velicina3.setSelected(false);
        else if(velicina2.isSelected())
            velicina2.setSelected(false);
        sizeOf = 16;
    }//GEN-LAST:event_velicina4ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        frame.setEnabled(true);
        frame.toFront();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Custom;
    private javax.swing.JButton Easy;
    private javax.swing.JButton Hard;
    private javax.swing.JButton Medium;
    private javax.swing.JCheckBox velicina2;
    private javax.swing.JCheckBox velicina3;
    private javax.swing.JCheckBox velicina4;
    // End of variables declaration//GEN-END:variables
}