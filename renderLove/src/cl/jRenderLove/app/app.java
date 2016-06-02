package cl.jRenderLove.app;

import cl.jRenderLove.miniGamesClass.AbieGame;
import cl.jRenderLove.model.*;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class app extends javax.swing.JFrame {

    private Data d;
    private Graphics g;
    private Chica girl;
    private Diversion div;
    private Passion pa;
    private Atraccion atrac;
    private Pregunta pregunta;
    private int nivel;
    private int  cont;
    private String strPalabra;
    private GraphicsDevice grafica;


    private void isEnableInteraction(int habilitado) {
        if (habilitado == 1) {
            btnRespuesta01.setEnabled(false);
            btnRespuesta02.setEnabled(false);
            btnRespuesta03.setEnabled(false);

        } else {
            btnRespuesta01.setEnabled(true);
            btnRespuesta02.setEnabled(true);
            btnRespuesta03.setEnabled(true);
        }
    }

    private void enviarDialogo(final String texto,JTextField panelTexto) {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuffer str = new StringBuffer("");
                    for (char x : texto.toCharArray()) {

                        try {
                            str.append(x);
                            panelTexto.setText(str.toString());
      
                            Thread.sleep(100);
                            


                        } catch (InterruptedException ex) {
                            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
                     

                        }        
                    }

                    /*Al terminar de imprimir el texto Cambia el sprite... 
                    un segundo y borra el texto*/
                    lblSprite.setIcon(cargarSprite(1));
                    Thread.sleep(2000);

                    panelTexto.setText("");

                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }) {

        }.start();
    }
    

    private void darPassion(int pasion) {
        try {

            pa = d.getPasion(1);
            if (pa.getPassion() < 100) {
                pa.setPassion(pa.getPassion() + pasion);
                d.updatePasion(pa);
                cargarEstadoChica(pa.getIdChica());

            }

        } catch (SQLException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void darAtraccion(int atraccion) {
        try {

            atrac = d.getAtracion(1);
            if (atrac.getAtraccion() < 100) {
                atrac.setAtraccion(atrac.getAtraccion() + atraccion);
                d.updateAtracion(atrac);
                cargarEstadoChica(atrac.getIdChica());
            }
        } catch (SQLException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void quitarAtraccion(int atraccion) {
        try {

            atrac = d.getAtracion(1);
            if (atrac.getAtraccion() > 0) {
                atrac.setAtraccion(atrac.getAtraccion() - atraccion);
                d.updateAtracion(atrac);
                cargarEstadoChica(atrac.getIdChica());
            }
        } catch (SQLException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void quitarPassion(int pasion) {
        try {

            pa = d.getPasion(1);
            if (pa.getPassion() > 0) {
                pa.setPassion(pa.getPassion() - pasion);
                d.updatePasion(pa);
                cargarEstadoChica(pa.getIdChica());
            }
        } catch (SQLException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void darDiversion(int diversion) {

        try {

            div = d.getDiversion(1);
            if (div.getDiversion() < 10) {
                div.setDiversion(div.getDiversion() + diversion);
                d.updateDiversion(div);
                cargarEstadoChica(div.getIdChica());
            }

        } catch (SQLException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void quitarDiversion(int diversion) {
        try {

            div = d.getDiversion(1);
            if (div.getDiversion() > 0) {
                div.setDiversion(div.getDiversion() - diversion);
                d.updateDiversion(div);
                cargarEstadoChica(div.getIdChica());
            }

        } catch (SQLException ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    private void limipiarBotones() {
       btnRespuesta01.setText("");
       btnRespuesta02.setText("");
       btnRespuesta03.setText("");
    }

    private void activarMiniJuegoAbie() {
           
            pMiniJuego01.setBounds(0, 0,489,300);
            txtMgIngresar.setEnabled(false);
            txtMgIngresar.requestFocus();
            pMiniJuego01.setLocationRelativeTo(null);
            pMiniJuego01.setVisible(true);
            lblSprite02.setIcon(cargarSprite2(9,lblSprite02));
        
            
            if (nivel<50){
                lblNivelMiniJuego.setText("Lv 1");
            }else {
            
                lblNivelMiniJuego.setText("Lv 2");
            }
      
            IntroduccionMiniJuego();
           
            
            
    }

    private void IntroduccionMiniJuego() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    enviarDialogo("Muy bien empezaremos con el juego", txtMgDialogo02);
                    Thread.sleep(5000);
                    enviarDialogo("El juego consiste en lo siguiente", txtMgDialogo02);
                    Thread.sleep(5000);
                    enviarDialogo("Yo dire palabras y tu, las repites", txtMgDialogo02);
                    Thread.sleep(5000);
                    enviarDialogo("Una vez hecho presionas ENTER", txtMgDialogo02);
                    Thread.sleep(5000);
                    enviarDialogo("Pero cuidado tengo una paciencia limitada", txtMgDialogo02);
                    Thread.sleep(5000);
                    enviarDialogo("El juego comienza en", txtMgDialogo02);
                    Thread.sleep(3000);
                    enviarDialogo("3", txtMgDialogo02);
                    Thread.sleep(500);
                    enviarDialogo("2", txtMgDialogo02);
                    Thread.sleep(1000);
                    enviarDialogo("1", txtMgDialogo02);
                    Thread.sleep(1000);
                    enviarDialogo("YA!!", txtMgDialogo02);
                    Thread.sleep(1000);
                    
                    empezarJuego();

                } catch (InterruptedException ex) {
                    Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void empezarJuego() {
                AbieGame palabra = null;
                cont=0;
                        palabra = d.darPalabra();
                        strPalabra=palabra.getPalabra();
                        enviarDialogo(palabra.getPalabra(), txtMgDialogo02);
                        txtMgIngresar.setEnabled(true);
                        cuentaRegresivaMiniGame(500, 1);
                        
                        

            }
        }).start();

    }
    
    public void cuentaRegresivaMiniGame(int segundos,int cuanto_se_resta){
 
        int contador=barTime.getValue();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(contador!=0){
                    try {
                        //SI es que la cuenta regresiva llega a 0
                        if (barTime.getValue()==0){
                            enviarDialogo("Basta!!!!", txtMgDialogo02);
                            txtMgIngresar.setEnabled(false);
                            txtMgIngresar.setText("");
                            Thread.sleep(3000);
                            enviarDialogo("Que aburrido ha sido!", txtMgDialogo02);
                            Thread.sleep(3000);
                            enviarDialogo("no eres lo que yo me esperaba", txtMgDialogo02);
                            Thread.sleep(4000);
                            enviarDialogo("nos veremos luego", txtMgDialogo02);
                            Thread.sleep(3000);
                            enviarDialogo("Adios", txtMgDialogo02);
                            Thread.sleep(3000);
                            quitarAtraccion(30);
                            pMiniJuego01.setVisible(false);
                            barTime.setValue(100);
                            break;    
                        
                        }
                        //Se encarga de frenar el ciclo si esque se termina el
                        //juego :c
                       
                        barTime.setValue(barTime.getValue()-cuanto_se_resta);
                        System.out.println(barTime.getValue()-cuanto_se_resta);
                        Thread.sleep(segundos);
                         if (nivel < 50) {
                            if(cont>10){
                                break;
                            }

                         } else if (nivel > 50) {
                            if(cont>20){
                                break;
                            }
                         }
                        
                        
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }).start();
            
            
    
    }

    private void jugarMiniJuego(int limite) {
        AbieGame palabra = null;
        new Thread (new Runnable() {
            @Override
            public void run() {
                try {
                    lblSprite02.setIcon(cargarSprite2(10,lblSprite02));
                    Thread.sleep(2000);
                    lblSprite02.setIcon(cargarSprite2(9,lblSprite02));
                } catch (InterruptedException ex) {
                    Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        }).start();
        if (cont <= limite) {
            palabra = d.darPalabra();
            strPalabra = palabra.getPalabra();
            enviarDialogo(palabra.getPalabra(), txtMgDialogo02);
            txtMgIngresar.setEnabled(true);
            cont++;

        }else{
            
            new Thread (new Runnable() {
                @Override
                public void run() {
                    try {
                        enviarDialogo("Basta!!!", txtMgDialogo02);
                        Thread.sleep(3000);
                        enviarDialogo("Ha sido muy divertido!!!", txtMgDialogo02);
                        Thread.sleep(3000);
                        enviarDialogo("Lo has hecho muy bien", txtMgDialogo02);
                        Thread.sleep(3000);
                        enviarDialogo("bueno, te veo luego", txtMgDialogo02);
                        Thread.sleep(3000);
                        enviarDialogo("adios!!!", txtMgDialogo02);
                        darAtraccion(40);
                        Thread.sleep(3000);
                        
                        pMiniJuego01.setVisible(false);
                        barTime.setValue(100);
                        
                        
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            
            txtMgIngresar.setEnabled(false);
            txtMgIngresar.setText("");
            
            
        }
    }

  

    public class BackgroundAbie extends javax.swing.JPanel {

        public BackgroundAbie() {
            this.setSize(1600, 900); //se selecciona el tamaño del panel
        }

//Se crea un método cuyo parámetro debe ser un objeto Graphics
        public void paint(Graphics grafico) {
            Dimension height = getSize();

//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
            ImageIcon Img = new ImageIcon(getClass().getResource("g/background.png"));

//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);

            setOpaque(false);
            super.paintComponent(grafico);
        }
    }
    
  

    /**
     * Creates new form app
     */
    public app() {

        initComponents();

        try {
            BackgroundAbie escolar = new BackgroundAbie();
            pBackground.add(escolar);
            pBackground.repaint();
          
            d = new Data();
            cargarDatosChica(1);
            isEnableInteraction(1);
            cargarEstadoChica(1);
            txtDialogo.setEnabled(false);
            lblSprite.setIcon(cargarSprite(1));
  
            grafica=GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice();

            


            
//            setExtendedState(MAXIMIZED_BOTH);
            
//  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexion", "Error", JOptionPane.ERROR_MESSAGE);

            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMiniJuego01 = new javax.swing.JFrame();
        lblSprite02 = new javax.swing.JLabel();
        txtMgDialogo02 = new javax.swing.JTextField();
        txtMgIngresar = new javax.swing.JTextField();
        barTime = new javax.swing.JProgressBar();
        lblNivelMiniJuego = new javax.swing.JLabel();
        pBackground = new javax.swing.JPanel();
        lblSprite = new javax.swing.JLabel();
        barPasion = new javax.swing.JProgressBar();
        barAtraccion = new javax.swing.JProgressBar();
        barDiversion = new javax.swing.JProgressBar();
        btnInteractuar = new javax.swing.JButton();
        btnJugar = new javax.swing.JButton();
        btnRespuesta01 = new javax.swing.JButton();
        btnRespuesta02 = new javax.swing.JButton();
        btnRespuesta03 = new javax.swing.JButton();
        btnDarDiversion = new javax.swing.JButton();
        btnAtraccion = new javax.swing.JButton();
        btnPassion = new javax.swing.JButton();
        btnDiversion = new javax.swing.JButton();
        txtDialogo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblNombre1 = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblOcupacion = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jIFullScreen = new javax.swing.JMenuItem();
        jINormalScreen = new javax.swing.JMenuItem();
        jmSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        pMiniJuego01.setResizable(false);

        lblSprite02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtMgDialogo02.setEditable(false);
        txtMgDialogo02.setBackground(new java.awt.Color(204, 255, 255));
        txtMgDialogo02.setForeground(new java.awt.Color(0, 153, 153));
        txtMgDialogo02.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtMgIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMgIngresarKeyPressed(evt);
            }
        });

        barTime.setBackground(new java.awt.Color(255, 153, 153));
        barTime.setForeground(new java.awt.Color(204, 0, 0));
        barTime.setValue(100);

        lblNivelMiniJuego.setFont(new java.awt.Font("Trajan Pro 3", 1, 18)); // NOI18N
        lblNivelMiniJuego.setForeground(new java.awt.Color(255, 0, 153));
        lblNivelMiniJuego.setText("Nivel");

        javax.swing.GroupLayout pMiniJuego01Layout = new javax.swing.GroupLayout(pMiniJuego01.getContentPane());
        pMiniJuego01.getContentPane().setLayout(pMiniJuego01Layout);
        pMiniJuego01Layout.setHorizontalGroup(
            pMiniJuego01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMiniJuego01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pMiniJuego01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMiniJuego01Layout.createSequentialGroup()
                        .addGroup(pMiniJuego01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(barTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMgIngresar))
                        .addContainerGap())
                    .addGroup(pMiniJuego01Layout.createSequentialGroup()
                        .addGroup(pMiniJuego01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pMiniJuego01Layout.createSequentialGroup()
                                .addComponent(txtMgDialogo02, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pMiniJuego01Layout.createSequentialGroup()
                                .addGap(0, 147, Short.MAX_VALUE)
                                .addComponent(lblNivelMiniJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)))
                        .addComponent(lblSprite02, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        pMiniJuego01Layout.setVerticalGroup(
            pMiniJuego01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMiniJuego01Layout.createSequentialGroup()
                .addGroup(pMiniJuego01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pMiniJuego01Layout.createSequentialGroup()
                        .addComponent(lblNivelMiniJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMgDialogo02, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSprite02, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMgIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(barTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Render Love");
        setPreferredSize(new java.awt.Dimension(779, 750));

        pBackground.setBackground(new java.awt.Color(153, 255, 153));

        lblSprite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSpriteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSpriteMouseExited(evt);
            }
        });

        barPasion.setBackground(new java.awt.Color(255, 102, 255));
        barPasion.setForeground(new java.awt.Color(153, 0, 153));
        barPasion.setValue(20);
        barPasion.setString("Pasión");

        barAtraccion.setBackground(new java.awt.Color(0, 153, 255));
        barAtraccion.setForeground(new java.awt.Color(102, 204, 255));
        barAtraccion.setValue(20);
        barAtraccion.setString("Atracción");

        barDiversion.setBackground(new java.awt.Color(255, 153, 51));
        barDiversion.setForeground(new java.awt.Color(255, 102, 51));
        barDiversion.setMaximum(10);
        barDiversion.setString("Diversión");

        btnInteractuar.setBackground(new java.awt.Color(255, 102, 255));
        btnInteractuar.setForeground(new java.awt.Color(102, 0, 102));
        btnInteractuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/jRenderLove/app/g/talk.png"))); // NOI18N
        btnInteractuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInteractuarActionPerformed(evt);
            }
        });

        btnJugar.setBackground(new java.awt.Color(255, 102, 102));
        btnJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cl/jRenderLove/app/g/heart.png"))); // NOI18N
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        btnRespuesta01.setBackground(new java.awt.Color(153, 255, 255));
        btnRespuesta01.setFont(new java.awt.Font("Trajan Pro 3", 2, 12)); // NOI18N
        btnRespuesta01.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRespuesta01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespuesta01ActionPerformed(evt);
            }
        });

        btnRespuesta02.setBackground(new java.awt.Color(153, 255, 255));
        btnRespuesta02.setFont(new java.awt.Font("Trajan Pro 3", 2, 12)); // NOI18N
        btnRespuesta02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespuesta02ActionPerformed(evt);
            }
        });

        btnRespuesta03.setBackground(new java.awt.Color(153, 255, 255));
        btnRespuesta03.setFont(new java.awt.Font("Trajan Pro 3", 2, 12)); // NOI18N
        btnRespuesta03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespuesta03ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pBackgroundLayout = new javax.swing.GroupLayout(pBackground);
        pBackground.setLayout(pBackgroundLayout);
        pBackgroundLayout.setHorizontalGroup(
            pBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBackgroundLayout.createSequentialGroup()
                .addGroup(pBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(barPasion, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addComponent(barAtraccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(barDiversion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pBackgroundLayout.createSequentialGroup()
                        .addComponent(btnInteractuar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnRespuesta03, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addComponent(btnRespuesta02, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRespuesta01, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(100, 100, 100)
                .addComponent(lblSprite, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(206, 206, 206))
        );
        pBackgroundLayout.setVerticalGroup(
            pBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBackgroundLayout.createSequentialGroup()
                .addComponent(barDiversion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barAtraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barPasion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRespuesta01, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRespuesta02, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRespuesta03, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(pBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInteractuar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(lblSprite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnDarDiversion.setText("Dar Diversion");
        btnDarDiversion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarDiversionActionPerformed(evt);
            }
        });

        btnAtraccion.setText("quitar Atraccion");
        btnAtraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtraccionActionPerformed(evt);
            }
        });

        btnPassion.setText("Dar Passion");
        btnPassion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPassionActionPerformed(evt);
            }
        });

        btnDiversion.setText("Quitar Diversion");
        btnDiversion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiversionActionPerformed(evt);
            }
        });

        txtDialogo.setBackground(new java.awt.Color(51, 51, 51));
        txtDialogo.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 14)); // NOI18N
        txtDialogo.setForeground(new java.awt.Color(255, 255, 255));
        txtDialogo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        lblNombre1.setFont(new java.awt.Font("Trajan Pro 3", 3, 18)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(153, 0, 153));
        lblNombre1.setText("Nombre:");

        lblEdad.setFont(new java.awt.Font("Trajan Pro 3", 3, 18)); // NOI18N
        lblEdad.setForeground(new java.awt.Color(153, 0, 153));
        lblEdad.setText("Edad:");

        lblOcupacion.setFont(new java.awt.Font("Trajan Pro 3", 3, 18)); // NOI18N
        lblOcupacion.setForeground(new java.awt.Color(153, 0, 153));
        lblOcupacion.setText("Ocupacion:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblOcupacion, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNombre1)
                .addGap(51, 51, 51)
                .addComponent(lblEdad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(153, 204, 255));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(0, 153, 153));

        jMenu1.setText("Archivo");

        jIFullScreen.setText("FullScreen");
        jIFullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIFullScreenActionPerformed(evt);
            }
        });
        jMenu1.add(jIFullScreen);

        jINormalScreen.setText("NormalScreen");
        jINormalScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jINormalScreenActionPerformed(evt);
            }
        });
        jMenu1.add(jINormalScreen);

        jmSalir.setText("Salir");
        jmSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jmSalir);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDialogo)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAtraccion, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(btnDarDiversion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDiversion, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(btnPassion, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                .addGap(136, 136, 136)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPassion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDarDiversion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAtraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDiversion, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtDialogo, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pBackground.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInteractuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInteractuarActionPerformed

        if(barDiversion.getValue()>0){
        pregunta = d.takePregunta(1);
        enviarDialogo(pregunta.getPregunta(),txtDialogo);
        lblSprite.setIcon(cargarSprite(2));
        isEnableInteraction(2);
        btnRespuesta01.setText(pregunta.getRespuesta());
        btnRespuesta02.setText(pregunta.getRespuesta2());
        btnRespuesta03.setText(pregunta.getRespuesta3());
        }else{
            enviarDialogo("Estoy Aburrida!!!,No me molestes mas", txtDialogo);
            lblSprite.setIcon(cargarSprite(3));
        }

    }//GEN-LAST:event_btnInteractuarActionPerformed

    private void btnPassionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPassionActionPerformed
        darPassion(10);
    }//GEN-LAST:event_btnPassionActionPerformed

    private void btnAtraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtraccionActionPerformed
        quitarAtraccion(10);
    }//GEN-LAST:event_btnAtraccionActionPerformed

    private void btnDiversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiversionActionPerformed
        quitarDiversion(1);
    }//GEN-LAST:event_btnDiversionActionPerformed

    private void btnDarDiversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarDiversionActionPerformed
        darDiversion(1);
    }//GEN-LAST:event_btnDarDiversionActionPerformed

    private void lblSpriteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSpriteMouseClicked
        if (txtDialogo.getText().equalsIgnoreCase("")) {

            lblSprite.setIcon(cargarSprite(2));

            enviarDialogo("Se nota tu aburrimiento",txtDialogo);

        }

    }//GEN-LAST:event_lblSpriteMouseClicked

    private void jmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmSalirActionPerformed

    private void lblSpriteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSpriteMouseExited


    }//GEN-LAST:event_lblSpriteMouseExited

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
       activarMiniJuegoAbie();
        
 
       
    }//GEN-LAST:event_btnJugarActionPerformed

    private void txtMgIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMgIngresarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (strPalabra.equalsIgnoreCase(txtMgIngresar.getText())) {

                    
                    txtMgIngresar.setText("");

                    if (nivel < 50) {
                        System.out.println("Juego empezado con nivel 10");
                        jugarMiniJuego(10);

                    } else if (nivel > 50) {
                        System.out.println("Juego empezado con nivel 20");
                        jugarMiniJuego(20);
                    }


            } else {
                enviarDialogo("Error", txtMgDialogo02);
            }
        }
 
    }//GEN-LAST:event_txtMgIngresarKeyPressed

    private void btnRespuesta02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespuesta02ActionPerformed
        if (pregunta.getIdPregunta()==1 & pregunta.getIdChica()==1){
            enviarDialogo("Si no lo sabes, vete de aqui!!!",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(3));
            quitarPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta()==2 & pregunta.getIdChica()==1){
            enviarDialogo("Ups! se nota que no lo eres.",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            darPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta()==3 & pregunta.getIdChica()==1){
            enviarDialogo("Odio la vainilla !!",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(3));
            quitarPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta()==4 & pregunta.getIdChica()==1){
            enviarDialogo("Amo dibujar tambien!!",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            darPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta()==5 & pregunta.getIdChica()==1){
            enviarDialogo("Siii!! esa es mi edad",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(6));
            darPassion(3);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta()==6 & pregunta.getIdChica()==1){
            enviarDialogo("No me gusta gastar dinero inutilmente",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            quitarPassion(6);
            quitarDiversion(1);
        }
        limipiarBotones();

    }//GEN-LAST:event_btnRespuesta02ActionPerformed

    private void btnRespuesta01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespuesta01ActionPerformed
        if (pregunta.getIdPregunta()==1 & pregunta.getIdChica()==1){
            enviarDialogo("ohhh!!. encerio?, me gusta",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            darPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta()==2 & pregunta.getIdChica()==1){
            enviarDialogo("Mentira!!, No te creo",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(3));
            quitarPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta()==3 & pregunta.getIdChica()==1){
            enviarDialogo("Wakala!!, odio el chocolate",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(3));
            quitarPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta()==4 & pregunta.getIdChica()==1){
            enviarDialogo("Que nerd, se parece a alguien que conozco.",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            quitarPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta()==5 & pregunta.getIdChica()==1){
            enviarDialogo("Nop esa no es mi edad...",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(2));
            quitarPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta()==6 & pregunta.getIdChica()==1){
            enviarDialogo("Me encanta estudiar...!!!",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            darPassion(6);
            quitarDiversion(1);
        }

        limipiarBotones();

    }//GEN-LAST:event_btnRespuesta01ActionPerformed

    private void btnRespuesta03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespuesta03ActionPerformed
        if (pregunta.getIdPregunta() == 1 & pregunta.getIdChica() == 1) {
            enviarDialogo(".............................",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(7));
            darPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta() == 2 & pregunta.getIdChica() == 1) {
            enviarDialogo("System.out.println(quitarPassion(6)).. ",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(4));
            quitarPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta() == 3 & pregunta.getIdChica() == 1) {
            enviarDialogo("Que rico sabor!!!",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(6));
            darPassion(6);
            quitarDiversion(1);
        }

        else if (pregunta.getIdPregunta() == 4 & pregunta.getIdChica() == 1) {
            enviarDialogo("Entonces vete a jugar y sal de mi clase",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(3));
            quitarPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta() == 5 & pregunta.getIdChica() == 1) {
            enviarDialogo("Failded again...",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(3));
            quitarPassion(6);
            quitarDiversion(1);
        }
        else if (pregunta.getIdPregunta() == 6 & pregunta.getIdChica() == 1) {
            enviarDialogo("Como puede eso gustarme??!!!",txtDialogo);
            isEnableInteraction(1);
            lblSprite.setIcon(cargarSprite(8));
            quitarPassion(6);
            quitarDiversion(1);
        }

        limipiarBotones();
    }//GEN-LAST:event_btnRespuesta03ActionPerformed

    private void jIFullScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIFullScreenActionPerformed
     
     grafica.setFullScreenWindow(this);
    }//GEN-LAST:event_jIFullScreenActionPerformed

    private void jINormalScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jINormalScreenActionPerformed
        this.setExtendedState(NORMAL);
    }//GEN-LAST:event_jINormalScreenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
////            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
////                if ("Nimbus".equals(info.getName())) {
////                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
////                    break;
////                }
////            }
//            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(app.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(app.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(app.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(app.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new app().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barAtraccion;
    private javax.swing.JProgressBar barDiversion;
    private javax.swing.JProgressBar barPasion;
    private javax.swing.JProgressBar barTime;
    private javax.swing.JButton btnAtraccion;
    private javax.swing.JButton btnDarDiversion;
    private javax.swing.JButton btnDiversion;
    private javax.swing.JButton btnInteractuar;
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnPassion;
    private javax.swing.JButton btnRespuesta01;
    private javax.swing.JButton btnRespuesta02;
    private javax.swing.JButton btnRespuesta03;
    private javax.swing.JMenuItem jIFullScreen;
    private javax.swing.JMenuItem jINormalScreen;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jmSalir;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblNivelMiniJuego;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblOcupacion;
    private javax.swing.JLabel lblSprite;
    private javax.swing.JLabel lblSprite02;
    private javax.swing.JPanel pBackground;
    private javax.swing.JFrame pMiniJuego01;
    private javax.swing.JTextField txtDialogo;
    private javax.swing.JTextField txtMgDialogo02;
    private javax.swing.JTextField txtMgIngresar;
    // End of variables declaration//GEN-END:variables

    private void cargarDatosChica(int idChica) {
        try {

            Chica girl = d.getChica(idChica);
            String edadChica = Integer.toString(girl.getEdad());
            lblNombre1.setText("Nombre: " + girl.getNombre());
            lblEdad.setText("Edad: " + edadChica);
            lblOcupacion.setText("Ocupacion: " + girl.getOcupacion());
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this, "Error al cargar datos de la CHICA", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarEstadoChica(int idChica) {
        try {
            Diversion div = d.getDiversion(idChica);
            Passion pa = d.getPasion(idChica);
            Atraccion atrac = d.getAtracion(idChica);
            nivel=atrac.getAtraccion();
            barPasion.setValue(pa.getPassion());
            barDiversion.setValue(div.getDiversion());
            barAtraccion.setValue(atrac.getAtraccion());
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(this, "Error al cargar datos de los MEDIDORES: " + ex + "", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private ImageIcon cargarSprite(int numeroAnimacion) {

        String path = "g/" + numeroAnimacion + ".gif";
        URL url = this.getClass().getResource(path);
        Image spritenew = new ImageIcon(url).getImage().getScaledInstance(200,530, Image.SCALE_DEFAULT);
        ImageIcon sprite = new ImageIcon(spritenew);
   
        return sprite;

        //               lblImagen.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass()
//                        .getResource("/org/jtrans/resources/atun.jpg"))
//                        .getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT)));
    }
    
    
       private ImageIcon cargarSprite2(int numeroAnimacion,JLabel l) {
//Devuelve con la escal por defecto de un label
        String path = "g/" + numeroAnimacion + ".gif";
        URL url = this.getClass().getResource(path);
        Image spritenew = new ImageIcon(url).getImage().getScaledInstance(l.getWidth(),l.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon sprite = new ImageIcon(spritenew);
   
        return sprite;

        //               lblImagen.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass()
//                        .getResource("/org/jtrans/resources/atun.jpg"))
//                        .getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT)));
    }

}
