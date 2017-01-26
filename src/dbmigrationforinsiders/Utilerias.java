package dbmigrationforinsiders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    /* DICCIONARIO DE TABLAS Y PALABRAS CLAVE */
    public static String[][][] DICCIONARIO = {
        {{"BCOCNL"},{"PACLVE"},{"CICLVE"},    {},             {}},
        {{"CALCON"},{"PACLVE"},{"CICLVE"},    {"CTCLVE"},     {"OTFOLI"}},
        {{"AUXASI"},{"PACLVE"},{},    {},             {"OTFOLI"}},
        {{"CAPROS"},{"PACLVE"},{"CICLVE"},    {"CTCLVE"},     {}},
        {{"CRMSUC"},{},{},{},{}},
        {{"CTENIV"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"CTESUC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"CTPERS"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"CAPROC"},{},{},{},{}},
        {{"DETBXN"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"DEOTRA"}},
        {{"DISCXC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"FACCXC"},{"FDPACL"},{"FDCICL"},{},{}},
        {{"FAPCOM"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"FAPDEC"},{},{},{},{}},
        {{"FASCON"},{"PACLVE"},{},{},{"OTFOLI"}},
        {{"FOLIOS"},{"PACLVE"},{},{},{}},
        {{"HISASI"},{"PACLVE"},{"CICLVE"},{"CTCLVE","EMNCTE","ASPCTE"},{"ASOTRA"}},
        {{"HISEMC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"}},
        {{"HISIMR"},{"PACLVE"},{"CICLVE"},{"CTCLVE","RPPCTE"},{}},
        {{"HISLVL"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"OTFOLI"}},
        {{"HISPTO"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"OTFOLI"}},
        {{"HISTES"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"OTFOLI"}},
        {{"IDSED1"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ISPCTE"},{}},
        {{"IDSED2"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ISPCTE"},{}},
        {{"IDSED3"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ISPCTE"},{}},
        {{"IMP80"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"IMP80A"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"IMP80B"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"IMPHIS"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"}},
        {{"IMSMOV"},{"PACLVE"},{"CICLVE"},{"CTCLVE","MSECTE"},{"MSOTRA"}},
        {{"MESREP"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"}},
        {{"MESOLI"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"}},
        {{"MEAUTR"},{"PACLVE"},{},{},{}},
        {{"MESTKB"},{"PACLVE"},{"CICLVE"},{},{}},
        {{"MESTKU"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"}},
        {{"MODFAC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"MODSTR"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"MTOCIA"},{"PACLVE"},{"CICLVE"},{},{}},
        {{"MTOCON"},{"PACLVE"},{},{},{}},
        {{"MTOCTE"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"NOMASC"},{"PACLVE"},{},{"ASPCTE"},{"ASOTRA"}},
        {{"NOMASI"},{"PACLVE"},{"CICLVE"},{"ASPCTE","CTCLVE","EMNCTE"},{"ASOTRA"}},
        {{"NOMBXE"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ASPCTE"},{}},
        {{"NOMCAL"},{"PACLVE"},{"CICLVE"},{"CTCLVE","CAPCTE"},{"OTFOLI"}},
        {{"NOMCAN"},{"PACLVE"},{"CICLVE"},{"HCCTE","HCPCTE"},{}},
        {{"NOMCFC"},{"PACLVE"},{},{},{"OTFOLI"}},
        {{"NOMCIR"},{"PACLVE"},{"CICLVE"},{"CTCLVE","CIPCTE","CIECTE"},{}},
        {{"NOMDET"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE","EMNCTE"},{"DEOTRA"}},
        {{"NOMFAS"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"ASOTRA"}},
        {{"NOMFEM"},{"PACLVE"},{"CICLVE"},{"EMNCTE"},{}},
        {{"NOMINC"},{"PACLVE"},{"CICLVE"},{"EMNCTE","CTCLVE"},{"ASOTRA"}},
        {{"NOMMCO"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"}},
        {{"NOMNET"},{"PACLVE"},{"CICLVE"},{"NNPCTE","CTCLVE","EMNCTE"},{"NNOTRA"}},
        {{"NOMRES"},{"PACLVE"},{"CICLVE"},{"REPCTE","CTCLVE"},{"REOTRA"}},
        {{"NOTCRE"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"}},
        {{"ORDEMP"},{"PACLVE"},{},{"OEPCTE"},{"OTFOLI"}},
        {{"ORDFIN"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"}},
        {{"ORDTRA"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"}},
        {{"PROCAL"},{"PACLVE"},{"CICLVE"},{"CAPCTE","CTCLVE"},{"OTFOLI"}},
        {{"RHCPAR"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"}},
        {{"RHMEMP"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"RHMEMC"},{"PACLVE"},{"CICLVE"},{"ASPCTE","EMNCTE"},{}},
        {{"RHPLEM"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"TABBCO"},{"PACLVE"},{},{},{}},
        {{"TABPTO"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"TABPTOC"},{"PACLVE"},{},{"CTCLVE"},{}},
        {{"TABSTR"},{"PACLVE"},{},{},{}},
        {{"TABSUP"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{}},
        {{"TABVEN"},{"PACLVE"},{},{},{"ASOTRA"}},
        {{"CONFAH"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE","EMNCTE"},{"DEOTRA"}},
        {{"PAGFAH"},{"PACLVE"},{"CICLVE"},{"DEPCTE","EMNCTE"},{"DEOTRA"}},
        {{"AUDLAY"},{"AUDPAI"},{"AUDCIA"},{"AUDCTE"},{"AUDORD"}},
        {{"CFDIST"},{"PACLVE"},{"CICLVE"},{"EMNCTE","CTCLVE"},{"OTFOLI"}}
    };
    
    /* MÉTODO QUE REGRESA UN ARREGLO CON TODOS LOS SELECTS QUE SE NECESITAN PARA HACER UNA MIGRACIÓN DE CLIENTE - ORDEN */
    public static ArrayList listaDeConsultas(String pais, String compania, String cliente, String orden){
        ArrayList consultas = new ArrayList();
        for (int i = 0; i < DICCIONARIO.length; i++) {
            /* FORMO LOS 'OR' */
            String paisClause = orStatement(DICCIONARIO[i],1,pais,true);
            String companiaClause = orStatement(DICCIONARIO[i],2,compania,false);
            String clienteClase = orStatement(DICCIONARIO[i],3,cliente,false);
            String ordenClause = orStatement(DICCIONARIO[i],4,orden,false);
            
            /* SI NO HAY CLIENTE NI ORDEN, ME SALTO LA CONSULTA */
            if(clienteClase.equals("") && ordenClause.equals("")){
                consultas.add("NA");
                continue;
            }
            
            boolean firstAdded = true;
            String sqlStatement = "";
            
            /* FORMO EL WHERE */
            if(!paisClause.equals("") && firstAdded){ firstAdded=false; sqlStatement+="("+paisClause+")"; }
                else if(!paisClause.equals("")){ sqlStatement+=" AND ("+paisClause+")"; }
            
            if(!companiaClause.equals("") && firstAdded){ firstAdded=false; sqlStatement+="("+companiaClause+")"; }
                else if(!companiaClause.equals("")){ sqlStatement+=" AND ("+companiaClause+")"; }
            
            if(!clienteClase.equals("") && firstAdded){ firstAdded=false; sqlStatement+="("+clienteClase+")"; }
                else if(!clienteClase.equals("")){ sqlStatement+=" AND ("+clienteClase+")"; }
            
            if(!ordenClause.equals("") && firstAdded){ firstAdded=false; sqlStatement+="("+ordenClause+")"; }
                else if(!ordenClause.equals("")){ sqlStatement+=" AND ("+ordenClause+")"; }
            
            /* FORMO LA CONSULTA Y LA GUARDO */
            sqlStatement = "SELECT * FROM "+DICCIONARIO[i][0][0]+" WHERE "+sqlStatement;
            consultas.add(sqlStatement);
        }
        return consultas;
    }
    
    /* MÉTODO PARA CONSTRUIR CLAUSULAS CON OR */
    public static String orStatement(String [][]tabla,int columna,String token,boolean varchar){
        if(token.equals("")){return "";}
        boolean first = true;
        String orClause = "";
        String delim = varchar? "'" : "";
        for (int j = 0; j < tabla[columna].length; j++) {
            if(first){
                first = false;
                orClause += tabla[columna][j] + "=" + delim + token + delim;
            } else {
                orClause += " OR " + tabla[columna][j] + "=" + delim + token + delim;
            }
        }
        return orClause;
    }
    
    /* MÉTODO QUE, APARTIR DE UN RESULTSET, PONE LAS INYECCIONES EN UN SOLO INSERT CONCATENADO, REGRESA LA CANTIDAD DE TUPLA */
    public static ArrayList listaDeInyecciones(String table,ResultSet rs,ArrayList listaDeUpdates,String whereclause) throws SQLException{
        ArrayList listaDeInyecciones = new ArrayList();
        ResultSetMetaData meta = rs.getMetaData();
        while(rs.next()){
            String sql = "INSERT INTO "+table +" VALUES ";
            String sqlUpdate = "UPDATE "+table+" SET ";
            StringBuilder bindVariables = new StringBuilder();
            StringBuilder bindVariablesForUpdate = new StringBuilder();
            for (int i = 1; i <= meta.getColumnCount(); i++){
                if (i > 1) {
                    bindVariables.append(", ");
                    bindVariablesForUpdate.append(", ");
                }
                if(meta.getColumnClassName(i).equals("java.lang.String")){
                    bindVariables.append("'"+rs.getString(meta.getColumnName(i))+"'");
                    bindVariablesForUpdate.append(meta.getColumnName(i)+"='"+rs.getString(meta.getColumnName(i))+"'");
                } else {
                    bindVariables.append(rs.getString(meta.getColumnName(i)));
                    bindVariablesForUpdate.append(meta.getColumnName(i)+"="+rs.getString(meta.getColumnName(i)));
                }
            }
            sql += "(" + bindVariables + ")";
            sqlUpdate += bindVariablesForUpdate + " " + whereclause;
            listaDeInyecciones.add(sql);
            listaDeUpdates.add(sqlUpdate);
        }
        return listaDeInyecciones;
    }
    
    /*
    public static int listaDeInyecciones(String table,ResultSet rs,ArrayList listaDeInyecciones) throws SQLException{
        //StringBuilder columnNames = new StringBuilder();
        StringBuilder bindVariables = new StringBuilder();
        ResultSetMetaData meta = rs.getMetaData();
        String sql = "INSERT INTO "+table +" VALUES ";
        boolean firstEnter = true;
        int tuplas = 0;
        while(rs.next()){    
            for (int i = 1; i <= meta.getColumnCount(); i++){
                if (i > 1) {
                    //columnNames.append(", ");
                    bindVariables.append(", ");
                }
                //columnNames.append(meta.getColumnName(i));
                if(meta.getColumnClassName(i).equals("java.lang.String")){
                    bindVariables.append("'"+rs.getString(meta.getColumnName(i))+"'");
                } else {
                    bindVariables.append(rs.getString(meta.getColumnName(i)));
                }
            }
            if(firstEnter){
                firstEnter=false;
            } else {
                sql += ",";
            }
            sql += "(" + bindVariables + ")";
            tuplas++;
        }
        if(!firstEnter){
            listaDeInyecciones.add(sql);
        }
        return tuplas;
    } 
     */
    
    /* MÉTODO QUE HACE LA INYECCIÓN DIRECTAMENTE POR TABLA */
    public static int injectToTable(String tabla, ArrayList inyecciones,ArrayList updatesBackup,boolean hacerupdate){
        int inyectados = 0;
        ERRORES = "";
        for (int i = 0; i < inyecciones.size(); i++) {
            if(makeInjection(""+inyecciones.get(i),""+updatesBackup.get(i),hacerupdate)){
                inyectados++;
            }
        }
        return inyectados;
    }
    
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
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /* MÉTODO QUE REGRESA LA CONSULTA DE LAS ÓRDENES DE PRODUCTIVO EN UN ARRAYLIST */
    public static boolean getOrdersID(String pais,String compania, String cliente,ArrayList ordenesDeTrabajo){
        try {
            ResultSet rs = getResult(ConectionPool.getInstance().getProductivo(),"SELECT OTFOLI FROM ORDTRA WHERE PACLVE = '"+pais+"' AND CICLVE="+compania+" AND CTCLVE = "+cliente);
            //ResultSet rs = getResult(ConectionPool.getInstance().getConfirm(),"SELECT OTFOLI FROM ORDTRA WHERE PACLVE = '"+pais+"' AND CICLVE="+compania+" AND CTCLVE = "+cliente);
            while (rs.next()) {
                String CLAVE = rs.getString("OTFOLI");
                ordenesDeTrabajo.add(""+CLAVE);
            }
            return true;
        }catch(Exception e){
            ERRORES = "      -Hubo un error al intentar hacer la consulta de las ordenes del cliente ["+cliente+"], el log es:\n        -"+e.getMessage();
            e.printStackTrace();
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
            stmt.setMaxRows(20);
            ResultSet rs = stmt.executeQuery(consulta);
            return rs;
    }
    
    /* MÉTODO QUE HACE UNA INYECCION */
    /* NOTA, TENER CUIDADO CON LA INYECCION, POR QUE ESTA ESCRIBE A BASE DE DATOS */
    /* SIEMPRE EN CONFIRM */
    public static boolean makeInjection(String inyeccion,String update,boolean hacerUpdate){
        Connection conexion = ConectionPool.getInstance().getConfirm();
            try{
                Statement stmt = conexion.createStatement();
                /* DANGER ZONE - TOKEN MAGICO */
                stmt.executeUpdate(inyeccion);
                System.out.println(inyeccion);
            } catch(Exception e){
                String stringedexception = e.toString().replace("\n","\t\t")+"\n";
                ERRORES += inyeccion+"\n";
                ERRORES += stringedexception;
                if(stringedexception.contains("Duplicate key value specified") && hacerUpdate){
                    ERRORES += "          ---Se intentará hacer update en lugar de inyección:\n";
                    ERRORES += update+"\n";
                    try{
                        Statement stmt = conexion.createStatement();
                        /* DANGER ZONE - TOKEN MAGICO */
                        stmt.executeUpdate(update);
                        System.out.println(update);
                        ERRORES += "          ---Update realizado con éxito\n\n";
                    }catch(Exception ex){
                        ERRORES += e.toString().replace("\n","\t\t")+"\n\n";
                        e.printStackTrace();
                    }
                } else {
                    ERRORES += "\n";
                    e.printStackTrace();
                }
                return false;
            }
            return true;
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            PRODUCTIVO = null;
            productivoFlag = false;
        }
        public static void desconectarConfirm(){
            try {
                CONFIRM.close();
            } catch (Exception ex) {
                ex.printStackTrace();
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
