/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author victor
 */
public class MainProyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Inicio ventana = new Inicio();
        ventana.setSize(200, 300);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

}
