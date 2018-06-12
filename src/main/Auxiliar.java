/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Victor
 */
public class Auxiliar extends JDialog {

    private JLabel lblToDo,lblWorking;

    public Auxiliar(Inicio ventanaInicio) {
        super(ventanaInicio, true);
        ImageIcon imgWorking=new ImageIcon(Auxiliar.class.getResource("/main/imagenes/workinprogress.png"));
        lblWorking = new JLabel(imgWorking);
        lblWorking.setSize(250, 180);
        lblWorking.setLocation(400, 150);
        lblWorking.setVisible(true);
        this.add(lblWorking);
      
        }
}
