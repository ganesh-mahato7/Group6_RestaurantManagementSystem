/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ManageCategoryController;
import javax.swing.*;

public class ManageCategory extends JFrame {
    // ======= CONTROLLER =======
   private final ManageCategoryController controller;


    public ManageCategory() {
        initComponents();
        controller = new ManageCategoryController(this); // Pass view to controller
        controller.open(); // Load categories and attach listeners

        setTitle("Manage Category");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ManageCategory = new javax.swing.JLabel();
        ViewCategory = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ViewCategoryTable = new javax.swing.JTable();
        Instruction = new javax.swing.JLabel();
        AddNewCategory = new javax.swing.JLabel();
        AddCategory = new javax.swing.JTextField();
        Save = new javax.swing.JButton();
        Clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ManageCategory.setText("Manage Category");

        ViewCategory.setText("View Category");

        ViewCategoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Category"
            }
        ));
        jScrollPane1.setViewportView(ViewCategoryTable);

        Instruction.setText("*Click on row to delete category");

        AddNewCategory.setText("Add new category");

        AddCategory.addActionListener(this::AddCategoryActionPerformed);

        Save.setText("Save");
        Save.addActionListener(this::SaveActionPerformed);

        Clear.setText("Clear");
        Clear.addActionListener(this::ClearActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Instruction)
                .addGap(111, 111, 111))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ManageCategory)
                            .addComponent(AddNewCategory))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ManageCategory)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewCategory)
                    .addComponent(AddNewCategory))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Save)
                            .addComponent(Clear))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Instruction)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        AddCategory.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        
    }//GEN-LAST:event_SaveActionPerformed

    private void AddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCategoryActionPerformed

    }//GEN-LAST:event_AddCategoryActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddCategory;
    private javax.swing.JLabel AddNewCategory;
    private javax.swing.JButton Clear;
    private javax.swing.JLabel Instruction;
    private javax.swing.JLabel ManageCategory;
    private javax.swing.JButton Save;
    private javax.swing.JLabel ViewCategory;
    private javax.swing.JTable ViewCategoryTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    // ======= GETTER METHODS FOR CONTROLLER =======
    public JTable getViewCategoryTable() {
        return ViewCategoryTable;
    }

    public JTextField getAddCategoryField() {
        return AddCategory;
    }

    public JButton getSaveButton() {
        return Save;
    }

    public JButton getClearButton() {
        return Clear;
    }

    // ======= MAIN =======
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ManageCategory().setVisible(true));
    }
}