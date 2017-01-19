package dbmigrationforinsiders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jasalazar
 */
public class Utilerias {
    public static String ERRORES = "";
    /* MÉTODO QUE ESCRIBE A ARCHIVO EL LOG */
    public static boolean writeLogFile(File file, String log){
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(log);
            bw.write("    -Escritura de log realizada con éxito.\n");
            bw.close();
            fw.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /* MÉTODO QUE ABRE NUEVAS CONEXIONES AL ENTORNO Y CON LAS CREDENCIALES. */
    public static boolean openConnection(String user, String password, String entorno, String schema, boolean productivo) {
        String SERVER = getServerID(productivo,entorno);
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        try {
            DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());
            Connection conn = DriverManager.getConnection(
                    "jdbc:as400://"+SERVER+"/"+schema,
                    connectionProps);
            if(productivo){
                ConectionPool.getInstance().setProductivo(conn);
            } else {
                ConectionPool.getInstance().setConfirm(conn);
            }
        }catch(SQLException e){
            ERRORES = "    --Ocurrió una excepción al intentar conectarse.--\n"+
                    "    --Por favor revisa tus credenciales e intenta de nuevo.--\n"+
                    "    --Si el problema persiste y los entornos productivo y confirm no tienen problemas,--\n"+
                    "    --por favor contacta a tu administrador de aplicaciones.--\n";
            return false;
        }
        return true;
    }
    
    /* MÉTODO QUE REGRESA LA CONSULTA DE LAS ÓRDENES DE PRODUCTIVO EN UN ARRAYLIST */
    public static boolean getOrdersID(String cliente,ArrayList ordenesDeTrabajo){
        try {
            
            //ResultSet rs = getResult(ConectionPool.getInstance().getProductivo(),"SELECT OTFOLI FROM ORDTRA WHERE CTCLVE = "+cliente);
            ResultSet rs = getResult(ConectionPool.getInstance().getConfirm(),"SELECT OTFOLI FROM ORDTRA WHERE CTCLVE = "+cliente);
            while (rs.next()) {
                String CLAVE = rs.getString("OTFOLI");
                ordenesDeTrabajo.add(""+CLAVE);
            }
            return true;
        }catch(Exception e){
            ERRORES = "      -Hubo un error al intentar hacer la consulta de las ordenes del cliente ["+cliente+"], el log es:\n        -"+e.getMessage();
            return false;
        }
    }
    
    /* MÉTODO QUE REGRESA EL NOMBRE DEL SERVIDOR AL CUAL NOS CONECTAREMOS LOCALHOST POR DEFAULT */
    public static String getServerID(boolean productivo,String entorno){
        if(productivo && entorno.equals("MECA")){
            return "promeca.manpower.com.mx";
        } else if(productivo && entorno.equals("MESA")){
            return "promesa.manpower.com.mx";
        } else if(productivo && entorno.equals("PAYRO")){
            return "propayro.manpower.com.mx";
        } else if(!productivo && entorno.equals("MECA")){
            return "tstmeca.manpower.com.mx";
        } else if(!productivo && entorno.equals("MESA")){
            return "tstmesa.manpower.com.mx";
        } else if(!productivo && entorno.equals("PAYRO")){
            return "tstpayro.manpower.com.mx";
        }
        return "127.0.0.1";
    }

    /* MÉTODO QUE RECIBE UNA CONEXIÓN Y UNA CONSULTA, Y REGRESA EL RESULT SET */
    public static ResultSet getResult(Connection conexion, String consulta) throws SQLException{
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            return rs;
    }
    
    /* SINGLETON QUE ADMINISTRA LAS CONEXIONES A LAS BASES DE DATOS */
    public static class ConectionPool {
        private static ConectionPool instance = null;
        private static Connection PRODUCTIVO;
        private static Connection CONFIRM;
        private static boolean productivoFlag;
        private static boolean confirmFlag;
        protected ConectionPool() {
            PRODUCTIVO = null;
            CONFIRM = null;
            productivoFlag = false;
            confirmFlag = false;
        }
        public static ConectionPool getInstance() {
            if(instance == null) {
                instance = new ConectionPool();
            }
            return instance;
        }
        public static Connection getProductivo(){
            return PRODUCTIVO;
        }
        public static Connection getConfirm(){
            return CONFIRM;
        }
        public static void setProductivo(Connection pPRODUCTIVO){
            PRODUCTIVO = pPRODUCTIVO;
            productivoFlag = true;
        }
        public static void setConfirm(Connection pCONFIRM){
            CONFIRM = pCONFIRM;
            confirmFlag = true;
        }
        public static void desconectarProductivo(){
            try {
                PRODUCTIVO.close();
            } catch (Exception ex) {}
            PRODUCTIVO = null;
            productivoFlag = false;
        }
        public static void desconectarConfirm(){
            try {
                CONFIRM.close();
            } catch (Exception ex) {}
            CONFIRM = null;
            confirmFlag = false;
        }
        public static boolean getProductivoFlag(){
            return productivoFlag;
        }
        public static boolean getConfirmFlag(){
            return confirmFlag;
        }
    }
    
}
