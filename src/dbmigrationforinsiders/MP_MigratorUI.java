package dbmigrationforinsiders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jasalazar
 */
public class MP_MigratorUI extends javax.swing.JFrame {
    public String usuarioProd;
    public String usuarioConf;
    public String ENTORNO = "MECA";
    public String SIN_RESULTADOS = "";
    private Ayuda ayuda = null;

    public MP_MigratorUI() {
        initComponents();
        printImage();
        appendLog("\n    -Bienvenido al migrador, esperando comando\n");
        usuarioProd = "";
        usuarioConf = "";
    }
    
    public void printImage(){
        try {
            BufferedImage wPic = ImageIO.read(new File("lib/com/mx/img/mpg-logo2.png"));
            this.logompg_label.setIcon(new ImageIcon(wPic));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void appendLog(String add){
        this.jTextArea1.append(add);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        logompg_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 2, true));

        logompg_label.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        logompg_label.setText("  DB Migrator");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel1.setText("By");

        jLabel2.setFont(new java.awt.Font("Verdana", 2, 10)); // NOI18N
        jLabel2.setText("itzcoatl90");

        jButton4.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jButton4.setText("Ayuda");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logompg_label, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logompg_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 255), 2, true));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("PRODUCTIVO");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel5.setText("Usuario");

        jTextField1.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel6.setText("Contraseña");

        jPasswordField1.setFont(new java.awt.Font("LilyUPC", 0, 8)); // NOI18N
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jButton1.setText("Probar Conexión");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jPasswordField1)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGap(8, 8, 8)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 2, true));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("CONFIRM");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel7.setText("Usuario");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel8.setText("Contraseña");

        jTextField2.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jPasswordField2.setFont(new java.awt.Font("LilyUPC", 0, 8)); // NOI18N

        jButton2.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jButton2.setText("Probar Conexión");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jPasswordField2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 0, 0), 2, true));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("MECA");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseReleased(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jRadioButton2.setText("MESA");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseReleased(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jRadioButton3.setText("PAYRO");
        jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButton3MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 2, true));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 2, true));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("CLIENTE");

        jTextField3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("ORDENES");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jRadioButton4.setSelected(true);
        jRadioButton4.setText("Todas");
        jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButton4MouseReleased(evt);
            }
        });

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jRadioButton5.setText("Especificas");
        jRadioButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButton5MouseReleased(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Aquí tus órdenes,\nseparadas por\nespacio, coma,\nenter, pipe o\npunto y coma.");
        jTextArea2.setEnabled(false);
        jTextArea2.setFocusable(false);
        jTextArea2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea2FocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea2);

        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setText("Ejecutar Migración");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton5.setText("Exportar log");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton5MouseReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setText("PAÍS");

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setText("COMP");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jRadioButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton5))
                                    .addComponent(jLabel10)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton5)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if(Utilerias.ConectionPool.getInstance().getProductivoFlag() && !jTextField1.getText().equals(this.usuarioProd)){
            this.jButton1.setText("Conectar nuevo usuario");
        } else if(Utilerias.ConectionPool.getInstance().getProductivoFlag()) {
            if(!this.jButton1.getText().equals("Conectado a productivo")){
                this.jButton1.setText("Conectado a productivo");
            }
        } else if(!Utilerias.ConectionPool.getInstance().getProductivoFlag() || jTextField1.getText().equals(this.usuarioProd)) {
            if(!this.jButton1.getText().equals("Probar Conexión")){
                this.jButton1.setText("Probar Conexión");
            }
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        //NOTHING TO DO HERE
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        if(Utilerias.ConectionPool.getInstance().getConfirmFlag() && !jTextField2.getText().equals(this.usuarioConf)){
            this.jButton2.setText("Conectar nuevo usuario");
        } else if(Utilerias.ConectionPool.getInstance().getConfirmFlag()) {
            if(!this.jButton2.getText().equals("Conectado a confirm")){
                this.jButton2.setText("Conectado a confirm");
            }
        } else if(!Utilerias.ConectionPool.getInstance().getConfirmFlag() || jTextField2.getText().equals(this.usuarioConf)) {
            if(!this.jButton2.getText().equals("Probar Conexión")){
                this.jButton2.setText("Probar Conexión");
            }
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        jButton1.setText("Intentando conectar");
        openConnectionProd();
    }//GEN-LAST:event_jButton1MouseReleased
    
    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        jButton2.setText("Intentando conectar");
        openConnectionConf();
    }//GEN-LAST:event_jButton2MouseReleased

    /* MÉTODO QUE INVOCA EN UN NUEVO THREAD EL OPENCONNECT PRODUCTIVO */
    public void openConnectionProd(){
        Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            appendLog("    --Intentando conectar con productivo\n");
            if(Utilerias.openConnection(jTextField1.getText(),String.valueOf(jPasswordField1.getPassword()),
                jRadioButton1.isSelected() ? jRadioButton1.getText() : jRadioButton2.isSelected() ? jRadioButton2.getText() : jRadioButton3.getText(),
                "powerdata",true)){
                appendLog("    --Establecida conexión con productivo\n");
                jButton1.setText("Conectado a productivo");
                usuarioProd = jTextField1.getText();
            } else {
                appendLog(Utilerias.ERRORES + "\n");
                Utilerias.ERRORES = "";
                jButton1.setText("Fallo en la conexión");
            }
        }     
        });
        t.start();
    }
    
    /* MÉTODO QUE INVOCA EN UN NUEVO THREAD EL OPENCONNECT CONFIRM*/
    public void openConnectionConf(){
        Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            appendLog("    --Intentando conectar con confirm\n");
            if(Utilerias.openConnection(jTextField2.getText(),String.valueOf(jPasswordField2.getPassword()),
                jRadioButton1.isSelected() ? jRadioButton1.getText() : jRadioButton2.isSelected() ? jRadioButton2.getText() : jRadioButton3.getText(),
                "powerdata",false)){
                appendLog("    --Establecida conexión con confirm\n");
                jButton2.setText("Conectado a confirm");
                usuarioConf = jTextField2.getText();
            } else {
                appendLog(Utilerias.ERRORES + "\n");
                Utilerias.ERRORES = "";
                jButton2.setText("Fallo en la conexión");
            }
        }     
        });
        t.start();
    }
    
    private void jRadioButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseReleased
        if(!jRadioButton1.getText().equals(ENTORNO) && (Utilerias.ConectionPool.getInstance().getConfirmFlag() || Utilerias.ConectionPool.getInstance().getProductivoFlag())){
            if(confirmEnvironmentChange()){
                ENTORNO = jRadioButton1.getText();
            } else {
                entornoAnterior();
            }
        }
    }//GEN-LAST:event_jRadioButton1MouseReleased

    private void jRadioButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseReleased
        if(!jRadioButton2.getText().equals(ENTORNO) && (Utilerias.ConectionPool.getInstance().getConfirmFlag() || Utilerias.ConectionPool.getInstance().getProductivoFlag())){
            if(confirmEnvironmentChange()){
                ENTORNO = jRadioButton2.getText();
            } else {
                entornoAnterior();
            }
        }
    }//GEN-LAST:event_jRadioButton2MouseReleased

    private void jRadioButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton3MouseReleased
        if(!jRadioButton3.getText().equals(ENTORNO) && (Utilerias.ConectionPool.getInstance().getConfirmFlag() || Utilerias.ConectionPool.getInstance().getProductivoFlag())){
            if(confirmEnvironmentChange()){
                ENTORNO = jRadioButton3.getText();
            } else {
                entornoAnterior();
            }
        }
    }//GEN-LAST:event_jRadioButton3MouseReleased

    private void jRadioButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton4MouseReleased
        jTextArea2.setEditable(false);
        jTextArea2.setEnabled(false);
        jTextArea2.setFocusable(false);
    }//GEN-LAST:event_jRadioButton4MouseReleased

    private void jRadioButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton5MouseReleased
        jTextArea2.setEditable(true);
        jTextArea2.setEnabled(true);
        jTextArea2.setFocusable(true);
    }//GEN-LAST:event_jRadioButton5MouseReleased

    private void jButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseReleased
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if(Utilerias.writeLogFile(file, jTextArea1.getText())){
                appendLog("    -Escritura de log realizada con éxito.\n");
            } else {
                appendLog("    -Falló el intento de exportar el log, revisar permisos de aplicación.\n");
            }
        }
    }//GEN-LAST:event_jButton5MouseReleased

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
        appendLog("    -Iniciando con la migración\n");
        // ME CONECTO A MIS ENTORNOS SI NO ESTOY CONECTADO AÚN
        if(!Utilerias.ConectionPool.getInstance().getProductivoFlag()){
            openConnectionProd();
        }
        if(!Utilerias.ConectionPool.getInstance().getConfirmFlag()){
            openConnectionConf();
        }
        // DOY ARRANQUE CON LA MIGRACIÓN
        validateMigration();
    }//GEN-LAST:event_jButton3MouseReleased

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        if(ayuda == null){
            ayuda = new Ayuda();
            ayuda.setVisible(true);
        } else {
            ayuda.setVisible(true);
        }
    }//GEN-LAST:event_jButton4MouseReleased

    private void jTextArea2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea2FocusGained
        if(jTextArea2.getText().indexOf("Aquí tus órdenes") == 0){
            jTextArea2.setText("");
        }
    }//GEN-LAST:event_jTextArea2FocusGained

    private void jTextArea2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea2FocusLost
        String content = jTextArea2.getText().replaceAll("\\s+","");
        if(content.equals("")){
            jTextArea2.setText("Aquí tus órdenes,\n" +
            "separadas por\n" +
            "espacio, coma,\n" +
            "enter, pipe o\n" +
            "punto y coma.");
        }
    }//GEN-LAST:event_jTextArea2FocusLost
    
    public void entornoAnterior(){
        if(ENTORNO.equals("MECA")){
            jRadioButton1.doClick();
        } else if(ENTORNO.equals("MESA")){
            jRadioButton2.doClick();
        } else {
            jRadioButton3.doClick();
        }
    }
    
    public boolean confirmEnvironmentChange() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Cambiar el entorno una vez conectado\ndesconectará la conexión previamente establecida.     \nDesea continuar de todos modos?", "Confirmar",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            jButton1.setText("Probar Conexión");
            jButton2.setText("Probar Conexión");
            appendLog("    --Desconectando de entornos.\n");
            Utilerias.ConectionPool.getInstance().desconectarProductivo();
            Utilerias.ConectionPool.getInstance().desconectarConfirm();
            return true;
        }
        return false;
    }
    
    
    public void validateMigration(){
        Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String pais = jTextField5.getText();
            String compania = jTextField4.getText();
            String cliente = jTextField3.getText();
            /* ESPERO A QUE ESTÉN HECHAS LAS 2 CONEXIONES O A QUE PASEN 10 SEGUNDOS SIN QUE UN SERVIDOR NOS RESPONDA */
            while(!(Utilerias.ConectionPool.getInstance().getProductivoFlag() && Utilerias.ConectionPool.getInstance().getConfirmFlag())
                    && (System.currentTimeMillis()-start)<10000){}
            /* REVISO SI TENEMOS AMBAS CONEXIONES */
            if(Utilerias.ConectionPool.getInstance().getProductivoFlag() && Utilerias.ConectionPool.getInstance().getConfirmFlag()){
                /* VALIDACIÓN DEL CLIENTE NUMERIC(7) EN BASE DE DATOS, PAIS CHAR(2) Y COMPAÑIA NUMERIC(3) */
                boolean TODO_BIEN = true;
                String validaciones = "";
                if(!isNumeric(cliente) || cliente.length()>9){
                    appendLog("    -La clave del cliente no tiene el formato de consulta.\n");
                    validaciones += "La clave ["+cliente+"] del cliente no tiene el formato del campo CTCLVE en la BD.\n";
                    TODO_BIEN = false;
                }
                if(pais.length()>2 || pais.length()==0){
                    appendLog("    -La clave del país no tiene el formato de consulta.\n");
                    validaciones += "La clave ["+pais+"] del país no tiene el formato del campo CTCLVE en la BD.\n";
                    TODO_BIEN = false;
                }
                if(!isNumeric(compania) || compania.length()>3){
                    appendLog("    -La clave de la compañía no tiene el formato de consulta.\n");
                    validaciones += "La clave ["+compania+"] de la compañía no tiene el formato del campo CTCLVE en la BD.\n";
                    TODO_BIEN = false;
                }
                if(TODO_BIEN){
                    /* RECOPILO LAS ÓRDENES QUE VOY A COPIAR, YA SEA DESDE EL AREA DE TEXTO O DB */
                    ArrayList ordenes = new ArrayList();
                    if(jRadioButton4.isSelected()){
                        llenarDesdeBD(pais,compania,cliente,ordenes);
                    } else {
                        llenarDesdeInput(cliente,ordenes);
                    }
                    /* CONFIRMO CON EL USUARIO */
                    if(ordenes.size() > 0){
                        if(userValidationForMigration(cliente,ordenes)){
                            /* MIGRO POR ORDEN */
                            long start_time = System.currentTimeMillis();
                            for (int i = 0; i < ordenes.size(); i++) {
                                makeMigration(pais,compania,cliente,""+ordenes.get(i));
                            }
                            long stop_time = System.currentTimeMillis();
                            appendLog("          ----El proceso tardó ["+((double)(stop_time-start_time)/1000.0)+"] segundos en terminar\n");
                            JOptionPane.showMessageDialog(null, "El proceso tardó ["+((double)(stop_time-start_time)/1000.0)+"] segundos en terminar.          ");
                        }
                    } else {
                        appendLog("      -No se encontraron órdenes apropiadas, se detendrá la migración en curso.\n");
                        JOptionPane.showMessageDialog(null, "-No se encontraron órdenes apropiadas, se detendrá la migración en curso.       ");
                    }
                } else {
                    validaciones += "Por favor revisa que hayas introducido correctamente sin espacios en blanco para poder continuar.";
                    JOptionPane.showMessageDialog(null, validaciones+"       ");
                }
            } else {
                appendLog("    -Se ha cancelado la migración, entre bases de datos, el servidor no contestó.\n");
                JOptionPane.showMessageDialog(null, "Se intentó realizar la conexión, pero el servidor está tardando demasiado en responder.\n"+
                        "Por favor revisa que tengas conexión entre la aplicación y los servidores y vuelve a intentarlo.       ");
            }
        }    
        });
        t.start();
    }
    
    public boolean userValidationForMigration(String cliente, ArrayList ordenes){
        String str_ordenes = "";
        for (int i = 0; i < ordenes.size(); i++) {
            str_ordenes += "          -"+ordenes.get(i);
            if(ordenes.size() <= 10){
                str_ordenes += "\n";
            } else {
                if(i!=0 && i%10==0){
                    str_ordenes += "\n";
                } else {
                    str_ordenes += "; ";
                }
            }
        }
        int response = JOptionPane.showConfirmDialog(null, " Se comenzará con la migración de ["+ordenes.size()+"] órdenes del cliente ["+cliente+"],\n confirme que desea migrar las siguientes órdenes: \n"+str_ordenes, "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            appendLog("      -Comenzando la migración con ["+ordenes.size()+"] órdenes del cliente ["+cliente+"]. Migrando las órdenes:\n");
            appendLog(str_ordenes);
            return true;
        } else {
            appendLog("      -Operación de migración cancelada.\n");
        }
        return false;
    }
    
    public void makeMigration(String pais,String compania,String cliente, String orden){
        /* ENLISTO TODAS LAS CONSULTAS QUE NECESITO POR ORDEN */
        appendLog("          ----INICIANDO MIGRACIÓN DE ÓRDEN ["+orden+"]\n");
        ArrayList listaDeConsultas = Utilerias.listaDeConsultas(pais,compania,cliente, orden);
        ArrayList listaDeInyecciones = null;
        ArrayList listaDeUpdates = null;
        int tablas_sin_resultado=0; // Variable que guarda cuantas tablas no tuvieron tuplas que inyectar
        /* ITERO TODAS LAS CONSULTAS NECESITADAS */
        for (int i = 0; i < listaDeConsultas.size(); i++) {
            String tabla = Utilerias.DICCIONARIO[i][0][0];
            try {
                /* SACO UNA LISTA DE INYECCIONES DE TODAS LAS TUPLAS POR CADA CONSULTA */
                if(!(""+listaDeConsultas.get(i)).equals("NA")){
                    listaDeUpdates = new ArrayList();
                    listaDeInyecciones = Utilerias.listaDeInyecciones(tabla,
                        Utilerias.getResult(Utilerias.ConectionPool.getInstance().getProductivo(), ""+listaDeConsultas.get(i)),
                            listaDeUpdates,separaWhere(""+listaDeConsultas.get(i)));
                } else {
                    listaDeInyecciones=null;
                    listaDeUpdates=null;
                }
            } catch(Exception e) {
                appendLog("        ---Hubo una excepción al intentar hacer migración de la tabla ["+tabla+"]\n"
                        + "        ---El mensaje de la excepción es ["+e.getMessage()+"]\n"+e.toString().replace("\n","\t\t")+"\n\n");
                e.printStackTrace();
            }
            if(listaDeInyecciones == null || listaDeInyecciones.isEmpty()){
                SIN_RESULTADOS+=tabla+";";
                if(tablas_sin_resultado!=0 && tablas_sin_resultado%10==0){
                    SIN_RESULTADOS+="\n           ";
                }
                tablas_sin_resultado++;
            } else {
                /* HAGO LA INYECCION DE TODAS LAS TUPLAS POR CADA CONSULTA Y REPORTO LOS RESULTADOS */
                int inyectados = Utilerias.injectToTable(tabla,
                        listaDeInyecciones,listaDeUpdates,listaDeInyecciones.size()!=1?false:true);
                for (int j = 0; j < listaDeInyecciones.size(); j++) {
                    appendLog(""+listaDeInyecciones.get(j)+"\n");
                }
                if(listaDeInyecciones.size()!=inyectados){
                    appendLog("        ---Se declinaron "+(listaDeInyecciones.size()-inyectados)+" inyecciones. Detalle de excepciones:\n");
                    appendLog(Utilerias.ERRORES);
                    Utilerias.ERRORES = "";
                } else {
                    appendLog("        ---Se ha hecho la inyeccion a la tabla ["+tabla+"] de ["+inyectados+"] tuplas\n");
                }
            }
        }
        if(!SIN_RESULTADOS.equals("")){
            appendLog("\n        ---Sin resultados para las tablas ["+SIN_RESULTADOS+"]\n");
            SIN_RESULTADOS="";
        }
        appendLog("          ----TERMINADA MIGRACIÓN DE ÓRDEN ["+orden+"]\n");
    }
    
    public String separaWhere(String consulta){
        return consulta.substring(consulta.indexOf("WHERE"));
    }
    
    public void llenarDesdeBD(String pais, String compania, String cliente, ArrayList listaDeOrdenes){
        if(Utilerias.getOrdersID(pais,compania,cliente, listaDeOrdenes)){
            if(listaDeOrdenes.size()>0){
                appendLog("      -Se han recopilado ["+listaDeOrdenes.size()+"] ordenes desde la base de datos con el cliente ["+cliente+"]\n");
            }
        } else {
            appendLog(Utilerias.ERRORES + "\n");
            JOptionPane.showMessageDialog(null, Utilerias.ERRORES + "\n");
            Utilerias.ERRORES = "";
        }
    }
    
    public void llenarDesdeInput(String cliente,ArrayList listaDeOrdenes){
        String ordenesRAW = jTextArea2.getText();
        StringTokenizer token = new StringTokenizer(ordenesRAW,"\n ,|;");
        boolean sinFalsos = true;
        String ORDENES_ERRONES_STOCK = "";
        while(token.hasMoreTokens()){
            String orden = token.nextToken();
            if(isNumeric(orden) && orden.length()<=9){
                listaDeOrdenes.add(orden);
            } else {
                ORDENES_ERRONES_STOCK+=orden+"; ";
                sinFalsos = false;
            }
        }
        if(!sinFalsos){
            appendLog("      -Las ordenes que siguen a continuación fueron ignoradas por su formato:\n"+
                    "        -"+ORDENES_ERRONES_STOCK);
        }
        if(listaDeOrdenes.size()>0){
            appendLog("      -Se han recopilado ["+listaDeOrdenes.size()+"] ordenes desde la base de datos con el cliente ["+cliente+"]\n");
        }
    }
    
    public void imprimirArrayList(ArrayList lista){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("<<"+lista.get(i)+">>");
        }
    }
    
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);  
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
            return false;  
        }
        return true;  
    }
    
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
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MP_MigratorUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel logompg_label;
    // End of variables declaration//GEN-END:variables
}
