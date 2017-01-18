package dbmigrationforinsiders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jasalazar
 */
public class Utilerias {
    public static String ERRORES = "";
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
            ERRORES = "Ocurrió una excepción al intentar conectarse.\n"+
                    "Por favor revisa tus credenciales e intenta de nuevo.\n"+
                    "Si el problema persiste y los entornos productivo y confirm no tienen problemas,\n"+
                    "por favor contacta a tu administrador de aplicaciones.";
            return false;
        }
        return true;
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
            } catch (SQLException ex) {
                Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
            }
            PRODUCTIVO = null;
            productivoFlag = false;
        }
        public static void desconectarConfirm(){
            try {
                CONFIRM.close();
            } catch (SQLException ex) {
                Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
            }
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
