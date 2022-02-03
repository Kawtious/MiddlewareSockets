/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Presentacion;

import java.awt.Color;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Version: 0.0.1 | Check changelog for a list of changes
 *
 * It is recommended to run this program with JDK 17
 *
 * @author Equipo 5
 */
public class FrmRegistroVentas extends javax.swing.JFrame {

    private final static int UI_THEME = 1;
    private final static String VERSION = "0.0.1";

    private static FrmRegistroVentas instance;

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
            switch (UI_THEME) {
                case 0 ->
                    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                case 1 ->
                    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
                case 2 ->
                    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatIntelliJLaf());
                case 3 ->
                    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarculaLaf());
                default ->
                    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarculaLaf());
            }

            UIManager.put("Component.arrowType", "triangle");
            UIManager.put("ScrollBar.thumbArc", 3);
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
            UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
            UIManager.put("TabbedPane.selectedBackground", Color.white);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        try {
            URL getVersion = new URL("https://raw.githubusercontent.com/Kawtious/MiddlewareSockets/main/VERSION");
            Scanner getVersionText = new Scanner(getVersion.openStream());
            String currentGithubVersion = getVersionText.nextLine();

            // read from your scanner
            if (!VERSION.equals(currentGithubVersion)) {
                System.out.println("[Client] Outdated client! Newest version is " + currentGithubVersion);
            }
        } catch (IOException ex) {
            // there was some connection problem, or the file did not exist on the server,
            // or your URL was not in the right format.
            // think about what to do now, and put it here.
            // for now, close the program.
            System.out.println("[Client] Error: Could not establish a connection to repository to verify running version.");
        }

        System.out.println("[Client] Running Version: " + VERSION);
        System.out.println("[Client] Check CHANGELOG for a list of changes");

        /* Create and display the form */
        SwingUtilities.invokeLater(() -> {
            FrmRegistroVentas.getInstance().setVisible(true);
        });
    }

    /**
     * Singleton pattern to keep a single instance of this class program running
     *
     * @return The instance of the program is returned, if there's none a new one is created
     */
    public static FrmRegistroVentas getInstance() {
        if (instance == null) {
            instance = new FrmRegistroVentas();
        }
        return instance;
    }

    /**
     * Creates new form FrmRegistroVentas
     */
    private FrmRegistroVentas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}