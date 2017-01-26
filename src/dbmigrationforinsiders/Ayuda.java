/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmigrationforinsiders;

import javax.swing.ImageIcon;

/**
 *
 * @author jasalazar
 */
public class Ayuda extends javax.swing.JFrame {

    /**
     * Creates new form Ayuda
     */
    public Ayuda() {
        initComponents();
        jLabel1.setIcon(new ImageIcon("lib/com/mx/img/ayuda02.png"));
        jLabel2.setIcon(new ImageIcon("lib/com/mx/img/catpais.png"));
        jLabel3.setIcon(new ImageIcon("lib/com/mx/img/catcomp.png"));
        jTextArea1.setText(funcionamiento());
        jTextArea2.setText(info_general());
    }
    
    private String funcionamiento(){
        return "   El programa de \"Migración de datos\" tiene el siguinte funcionamiento:\n" +
"    1.- El programa dejará elegir entre los 3 diferentes entorno (MECA, MESA y PAYRO).\n" +
"    2.- El programa pedirá credenciales para productivo y para pruebas, no se quedará ninguna por cuestiones de seguridad.\n" +
"    3.- El programa hará migraciones utilizando cuatro diferentes inputs:\n" +
"        -País\n" +
"        -Compañía\n" +
"        -Cliente\n" +
"        -Orden(es) de trabajo\n" +
"     4.1.- La clave del país se mapea a PACLVE (excepto en FACCXC que es FDPACL y AUDLAY que es AUDPAI), el input se valida para que sea caracteres desde 1 y hasta 2.\n" +
"     4.2.- La clave de la compañía se mapea a CICLVE (excepto en FACCXC que es FDCICL y AUDLAY que es AUDCIA), el input se valida para que sea numeros desde 1 y hasta 3.\n" +
"      4.3.1.- La clave del Cliente se mapea a alguno de los que siguen: CTCLVE,DEPCTE,EMNCTE,ASPCTE,ISPCTE,MSECTE,CAPCTE,HCCTE,HCPCTE,CIPCTE,CIECTE,NNPCTE,REPCTE,OEPCTE,AUDCTE,RPPCTE según la tabla.\n" +
"      4.3.2.- El input se valida para que sea números desde 1 y hasta 9 (Esto es por el mayor tamaño de las columnas candidatas).\n" +
"      4.4.1.- La clave de la Orden se mapea a alguno de los que siguen: OTFOLI,DEOTRA,ASOTRA,MSOTRA,NNOTRA,REOTRA,AUDORD según la tabla.\n" +
"      4.4.2.- El input se valida para que sea números desde 1 y hasta 9.\n" +
"       4.4.3.1.- Si en el input se incluyen las órdenes, serán las que se validen y se utilicen.\n" +
"       4.4.3.2.- Si en el input no se incluyen las órdenes, se hará una lista de las OTFOLI de la tabla ORDTRA con la PACLVE, CICLVE y CTCLVE correspondiente, el proceso se hará con todas.\n" +
"      4.4.4- Todo resultado se inyectará a CONFIRM (pruebas) del entorno en cuestión, a la misma tabla de la cual es extraído el resultado. Las tablas de las consultas serán las 70 anexadas.\n" +
"       4.4.5.1.- Si hay un solo resultado de una tabla en productivo y no está en la tabla de confirm, se inyectará.\n" +
"       4.4.5.2.- Si hay un solo resultado de una tabla en productivo y ya está en la tabla de confirm, se realizará un update del registro.\n" +
"       4.4.5.3.- Si hay varios resultados de una tabla en productivo, se inyectarán los que no estén, y los que estén se reportarán (por seguridad, no se actualizarán).\n" +
"    5.- El programa retroalimentará sobre:\n" +
"        -Los errores que hayan al momento de conectarse a las dos bases de datos (productivo y pruebas).\n" +
"        -Los errores que hayan al momento de hacer las consultas a las bases de datos de productivo.\n" +
"        -Los errores que hayan al momento de hacer las inyecciones o actualizaciones a las bases de datos de pruebas.\n" +
"        -La cantidad de inyecciones exitosas y fallidas por tabla.\n" +
"        -En el caso de las inyecciones fallidas, retroalimentará sobre la excepción producida y sobre el registro (con el identificador correspondiente).\n" +
"    6.- Todo esto se logrará a través de una interfaz gráfica.\n" +
"\n" +
"    NOTA ADICIONAL:\n" +
"     *En todos los puntos 4.4.5, se toma como \"no están\" si se permite hacer una inyección en base de datos sin llave primaria duplicada.\n" +
"     *El programa se desarrollará en Java compilado en 6… (producto final es un jar) para correrse se necesitará tener instalado en el equipo el jre 1.6.x o superior.\n" +
"     *Solo se realizarán las inyecciones de los resultados que cumplan con todos los filtros previos (del punto 4.1 al 4.4.3.2)\n" +
"     *El programa, por seguridad, NO GUARDARÁ ningún tipo de credenciales.";
    }

    public String info_general(){
        return    "    Version 1.1\n"
                + "    El programador y desarrollador es Itzcoatl Salazar Monroy (itzcoatl90)\n"
                + "    El Propietario de este código y programa, sin embargo, es MANPOWER CORPORATIVO S.A DE C.V.\n"
                + "     y es para uso exclusivo de personal de Sistemas.\n\n"
                + "    Programado y desarrollado en Java.\n"
                + "    Revisión del 27 / Enero / 2017\n\n"
                + "    Los entornos apuntados en esta revisión son:\n"
                + "      -promeca.manpower.com.mx\n"
                + "      -promesa.manpower.com.mx\n"
                + "      -propayro.manpower.com.mx\n"
                + "      -tstmeca.manpower.com.mx\n"
                + "      -tstmesa.manpower.com.mx\n"
                + "      -tstpayro.manpower.com.mx\n"
                + "      - DEFAULT 127.0.0.1\n\n"
                + "    Las tablas que el programa toma en cuenta son:\n"
                + "      -AUXASI," + "BCOCNL," + "CALCON," + "CAPROC," + "CAPROS," + "CRMSUC," + "CTENIV," + "CTESUC," + "CTPERS," + "DETBXN\n" +
                  "      -DISCXC," + "FACCXC," + "FACSUC," + "FAPCOM," + "FAPDEC," + "FASCON," + "FOLIOS," + "HISASI," + "HISEMC," + "HISIMR\n" +
                  "      -HISLVL," + "HISPTO," + "HISTES," + "IDSED1," + "IDSED2," + "IDSED3," + "IMP80," + "IMP80A," + "IMP80B," + "IMPHIS\n" +
                  "      -IMSMOV," + "IMSREG," + "MEAUIM," + "MEAUTR," + "MESOLI," + "MESREP," + "MESTKB," + "MESTKU," + "MODFAC," + "MODSTR\n" +
                  "      -MTOCIA," + "MTOCON," + "MTOCTE," + "NOMASC," + "NOMASI," + "NOMBXE," + "NOMCAL," + "NOMCAN," + "NOMCFC," + "NOMCIR\n" +
                  "      -NOMDET," + "NOMFAS," + "NOMFEM," + "NOMINC," + "NOMMCO," + "NOMNET," + "NOMRES," + "NOTCRE," + "ORDEMP," + "ORDFIN\n" +
                  "      -ORDTRA," + "PROCAL," + "RHCPAR," + "RHMEMP," + "RHMEMPC," + "RHPLEM," + "TABBCO," + "TABPTO," + "TABPTOC," + "TABSTR\n" +
                  "      -TABSUP," + "TABVEN," + "CONFAH," + "PAGFAH," + "AUDLAY," + "CFDIST\n\n"
                + "    El driver utilizado es: jdbc:as400\n"
                + "    Jar integrados:\n"
                + "     -jt400.jar\n"
                + "     -JTatto-1.6.11.jar\n"
                + "     -javax.swing\n\n"
                + "    El programa es sin fines de lucro y como herramienta de trabajo, sin derechos reservados.";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pantalla de ayuda", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Catálogo de país", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Catálogo de compañía", jPanel3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Funcionamiento", jPanel4);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setOpaque(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Acerca de", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ayuda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ayuda().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
