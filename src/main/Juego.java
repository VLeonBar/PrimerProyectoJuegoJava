/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.*;
import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author victor
 */
public class Juego extends JDialog implements ActionListener {

    private JMenuBar mnuBarra;
    private JMenu mnuJuego, mnuOpciones;
    private JMenuItem mnuRecord, mnuNuevo, mnuControles, mnuSalir, mnuAyuda, mnuBorrar;
//    private JPanel pnlPausa;
    private boolean flagMovimiento, flagColision = true;
    private JLabel lblAvion, lblBarrera, lblBarrera2, lblSuelo, lblCielo, lblContador, lblFinal, lblContinue;
    private JButton btnSi, btnNo, btnContinuar, btnOpciones, btnSalir;
    private ArrayList<JLabel> alBarreras, alBarreras2;
    private Timer tmrMovimiento;
    private int bolaY = 400, cont = 0, randSizeY, randLocY, record = 0;
    private String path = System.getProperty("user.dir") + "/src/main/archivos/record.txt";

    public Juego(Inicio ventanaInicio) {
        super(ventanaInicio, "Airplane Dodge", true);
        this.setLayout(null);

        ImageIcon imgCielo = new ImageIcon(Juego.class.getResource("/main/imagenes/cielo.png"));
        ImageIcon imgSuelo = new ImageIcon(Juego.class.getResource("/main/imagenes/suelo.png"));
        ImageIcon imgBarrera = new ImageIcon(Juego.class.getResource("/main/imagenes/columna.png"));
        ImageIcon imgAvion = new ImageIcon(Juego.class.getResource("/main/imagenes/avion.png"));
        ImageIcon imgSube = new ImageIcon(Juego.class.getResource("/main/imagenes/avionsube.png"));
        ImageIcon imgBaja = new ImageIcon(Juego.class.getResource("/main/imagenes/avionbaja.png"));
        ImageIcon imgExplosion = new ImageIcon(Juego.class.getResource("/main/imagenes/explosion.png"));
        ImageIcon imgFinal = new ImageIcon(Juego.class.getResource("/main/imagenes/gameover.png"));
        creaRecord(record);
        alBarreras = new ArrayList();
        alBarreras2 = new ArrayList();
        //MENU JUEGO
        mnuRecord = new JMenuItem("Récord");
        mnuRecord.setMnemonic('R');
        mnuRecord.addActionListener(this);

        mnuNuevo = new JMenuItem("Nuevo juego");
        mnuNuevo.setMnemonic('N');
        mnuNuevo.addActionListener(this);

        mnuSalir = new JMenuItem("Salir");
        mnuSalir.setMnemonic('S');
        mnuSalir.addActionListener(this);
        

        mnuJuego = new JMenu("Juego");
        mnuJuego.setMnemonic('J');
        mnuJuego.add(mnuNuevo);
        mnuJuego.add(mnuRecord);
        mnuJuego.addSeparator();
        mnuJuego.add(mnuSalir);

        //MENÚ OPCIONES
        mnuControles = new JMenuItem("Controles");
        mnuControles.setMnemonic('C');
        mnuControles.addActionListener(this);

        mnuAyuda = new JMenuItem("Ayuda");
        mnuAyuda.setMnemonic('A');
        mnuAyuda.addActionListener(this);

        mnuBorrar = new JMenuItem("Borrar Récord");
        mnuBorrar.setMnemonic('B');
        mnuBorrar.addActionListener(this);
        mnuOpciones = new JMenu("Opciones");
        
        mnuOpciones.setMnemonic('O');
        mnuOpciones.add(mnuControles);
        mnuOpciones.add(mnuBorrar);
        mnuOpciones.addSeparator();
        mnuOpciones.add(mnuAyuda);

        //MENU PRINCIPAL
        mnuBarra = new JMenuBar();
        mnuBarra.add(mnuJuego);
        mnuBarra.add(mnuOpciones);
        this.setJMenuBar(mnuBarra);

        //PAJARO
        lblAvion = new JLabel(imgAvion);
        lblAvion.setSize(30, 30);
        lblAvion.setLocation(50, bolaY);
        lblAvion.setVisible(true);
        this.add(lblAvion);

        //CONTADOR PUNTUACIÓN
        lblContador = new JLabel("Puntuación : " + Integer.toString(record));
        lblContador.setSize(600, 50);
        lblContador.setLocation(350, 50);
        lblContador.setVisible(true);
        this.add(lblContador);

        //GAME OVER
        lblFinal = new JLabel(imgFinal);
        lblFinal.setSize(500, 300);
        lblFinal.setLocation(150, 200);
        lblFinal.setVisible(false);
        this.add(lblFinal);

        //CONTINUE?
        lblContinue = new JLabel("¿Volver a jugar?");
        lblContinue.setSize(200, 50);
        lblContinue.setLocation(330, 450);
        lblContinue.setVisible(false);
        this.add(lblContinue);

        //BOTON SI
        btnSi = new JButton("Si");
        btnSi.setSize(100, 50);
        btnSi.setLocation(280, 500);
        btnSi.setFocusable(true);
        btnSi.setVisible(false);
        btnSi.addActionListener(this);
        this.add(btnSi);

        //BOTON NO
        btnNo = new JButton("No");
        btnNo.setSize(100, 50);
        btnNo.setLocation(390, 500);
        btnNo.setVisible(false);
        btnNo.addActionListener(this);
        this.add(btnNo);

        //BOTONES PAUSA
        //BOTON CONTINUA
        btnContinuar = new JButton("Continuar");
        btnContinuar.setSize(100, 40);
        btnContinuar.setFocusable(true);
        btnContinuar.setLocation(350, 250);
        btnContinuar.setVisible(false);
        btnContinuar.addActionListener(this);
        this.add(btnContinuar);
        //BOTON OPCIONES
        btnOpciones = new JButton("Opciones");
        btnOpciones.setSize(100, 40);
        btnOpciones.setLocation(350, 300);
        btnOpciones.setVisible(false);
        btnOpciones.addActionListener(this);
        this.add(btnOpciones);
        //BOTON SALIR
        btnSalir = new JButton("Salir");
        btnSalir.setSize(100, 40);
        btnSalir.setLocation(350, 350);
        btnSalir.setVisible(false);
        btnSalir.addActionListener(this);
        this.add(btnSalir);
        //SUELO
        lblSuelo = new JLabel(imgSuelo);
        lblSuelo.setSize(900, 400);
        lblSuelo.setLocation(0, 670);
        lblSuelo.setVisible(true);
        this.add(lblSuelo);
        //CIELO
        lblCielo = new JLabel(imgCielo);
        lblCielo.setSize(900, 100);
        lblCielo.setLocation(0, 0);
        lblCielo.setVisible(true);
        this.add(lblCielo);

        this.getContentPane().addKeyListener(new KeyHelper());
        this.getContentPane().setFocusable(true);

        tmrMovimiento = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (cont == 0 || (cont % 90 == 0)) {
                    creaBarrera();
                }
                cont++;
                for (JLabel barrera : alBarreras) {
                    barrera.setLocation(barrera.getX() - 5, 100);
                    if (barrera.getX() < 50) {
                        record++;
                        lblContador.setText("Puntuación : " + Integer.toString(record));
                    }
                    if (lblAvion.getBounds().intersects(barrera.getBounds())) {
                        creaRecord(record);
                        flagColision = false;
                    }

                }
                for (JLabel barrera : alBarreras2) {
                    barrera.setLocation(barrera.getX() - 5, barrera.getY());
                    if (barrera.getX() < 50) {
                        record++;
                        lblContador.setText("Puntuación : " + Integer.toString(record));
                    }
                    if (lblAvion.getBounds().intersects(barrera.getBounds())) {
                        creaRecord(record);
                        flagColision = false;
                    }
                }

                if (flagMovimiento) {
                    lblAvion.setIcon(imgSube);
                    lblAvion.setLocation(50, lblAvion.getY() - 5);
                    if (lblAvion.getY() == 100) {
                        lblAvion.setIcon(imgAvion);
                        lblAvion.setLocation(50, lblAvion.getY() + 5);
                    }
                }
                if (!flagMovimiento) {
                    lblAvion.setIcon(imgBaja);
                    lblAvion.setLocation(50, lblAvion.getY() + 5);
                    if (lblAvion.getY() == 640) {
                        lblAvion.setIcon(imgAvion);
                        lblAvion.setLocation(50, lblAvion.getY() - 5);
                    }
                }
                if (!flagColision) {
                    lblContador.setLocation(335, 420);
                    lblAvion.setIcon(imgExplosion);
                    hazVisible(true);
                    tmrMovimiento.stop();
                }

            }

            public void creaBarrera() {
                randSizeY = (int) (Math.random() * 480 + 1);
                randLocY = randSizeY + 225;

                lblBarrera = new JLabel(imgBarrera);
                lblBarrera.setSize(50, randSizeY);
                lblBarrera.setLocation(900, 100);
                alBarreras.add(lblBarrera);

                lblBarrera2 = new JLabel(imgBarrera);
                lblBarrera2.setSize(50, (800));
                lblBarrera2.setLocation(900, randLocY);
                alBarreras2.add(lblBarrera2);

//NO HACE FALTA INDEX PORQUE HACE REFERENCIA A SU PROPIA POSICION DE MEMORIA
                Juego.this.add(lblBarrera);
                Juego.this.add(lblBarrera2);
                ;
            }
        });
        tmrMovimiento.start();

        this.getContentPane().addKeyListener(new KeyHelper());
        this.getContentPane().addMouseListener(new MouseHelper());
    }

    public void hazVisible(boolean flag) {
        lblFinal.setVisible(flag);
        btnSi.setVisible(flag);
        btnNo.setVisible(flag);
        lblContinue.setVisible(flag);
        btnSi.setSelected(flag);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSi) {
            for (JLabel barrera : alBarreras) {
                barrera.setVisible(false);
            }
            for (JLabel barrera : alBarreras2) {
                barrera.setVisible(false);
            }
            alBarreras.clear();
            alBarreras2.clear();
            hazVisible(false);
            record = 0;
            lblContador.setText("Puntuación : " + Integer.toString(record));
            lblContador.setLocation(350, 50);
            flagColision = true;
            tmrMovimiento.start();
        }
        if (ae.getSource() == btnNo) {
            this.dispose();
        }
        if (ae.getSource() == btnContinuar) {
            setVisible2(false);
            tmrMovimiento.start();
        }
        if (ae.getSource() == btnOpciones) {
            System.err.println("HOLAAAAAAAAA");
        }
        if (ae.getSource() == btnSalir) {
            int respuesta=JOptionPane.showConfirmDialog(null, "¿Desea salir al menú principal?","Salir",JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                Juego.this.dispose();
            }
            if (respuesta == JOptionPane.NO_OPTION){}
        }
        //Botones Menú
        if (ae.getSource() == mnuNuevo) {
            
        }
        if (ae.getSource() == mnuRecord) {
        }
        if (ae.getSource() == mnuBorrar) {
            borraRecord();
        }
        if (ae.getSource() == mnuAyuda) {
            
        }
        if (ae.getSource() == mnuControles) {
            
        }
        if (ae.getSource() == mnuSalir) {
            
        }
    }

    public void setVisible2(boolean flag) {
        btnContinuar.setVisible(flag);
        btnOpciones.setVisible(flag);
        btnSalir.setVisible(flag);
    }

    public void creaRecord(int newrecord) {
        boolean flag = true;
        try (Scanner f = new Scanner(new File(path))) {
            while (f.hasNext()) {
                int oldrecord = Integer.parseInt(f.nextLine());
                if (newrecord > oldrecord) {
                    flag = false;
                }
            }
        } catch (Exception ex) {
            System.err.println("No se pudo leer el archivo por " + ex.getMessage());
        }
        if (!flag) {
            try (PrintWriter f = new PrintWriter(new FileWriter(path, false))) {
                f.println(newrecord);
            } catch (Exception ex) {
                System.err.println("No se ha podido escribir el archivo.");
            }
        }
    }

    public void borraRecord() {
        try (PrintWriter f = new PrintWriter(new FileWriter(path, false))) {
            f.println("0");
        } catch (Exception ex) {
            System.err.println("No se ha podido escribir el archivo.");
        }
    }

    public class MouseHelper extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            flagMovimiento = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            flagMovimiento = false;
        }
    }

    public class KeyHelper extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                flagMovimiento = true;
            }
            if ((e.getKeyCode() == KeyEvent.VK_ESCAPE) || (e.getKeyCode() == KeyEvent.VK_P)) {
                tmrMovimiento.stop();
                setVisible2(true);
                Juego.this.repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                flagMovimiento = false;
            }
        }

    }
}
