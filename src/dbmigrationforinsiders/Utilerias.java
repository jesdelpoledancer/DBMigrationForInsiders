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
    /* 
     * EL PRIMER ARREGLO DE PALABRAS ES EL NOMBRE DE LA TABLA, EL LENGTH ES 1
     * EL SEGUNDO ARREGLO DE PALABRAS SON LOS SINÓNIMOS DE LA CLAVE DEL PAÍS, EL LENGTH ES N
     * EL TERCER ARREGLO DE PALABRAS SON LOS SINÓNIMOS DE LA CLAVE DE LA COMPAÑÍA, EL LENGTH ES N
     * EL CUARTO ARREGLO DE PALABRAS SON LOS SINÓNIMOS DE LA CLAVE DEL CLIENTE, EL LENGTH ES N
     * EL QUINTO ARREGLO DE PALABRAS SON LOS SINÓNIMOS DE LA CLAVE DE LA ORDEN DE TRABAJO, EL LENGTH ES N
     * EL SEXTO ARREGLO DE PALABRAS SON LAS LLAVES PRIMARIAS DE LAS TABLAS, EL LENGTH ES 1
     * EL SEPTIMO ARREGLO DE PALABRAS SON LAS PALABRAS VACÍAS (A OMITIR), EL LENGTH ES N
     */
    public static String[][][] DICCIONARIO = {
        {{"BCOCNL"},{"PACLVE"},{"CICLVE"},    {},             {},           {"PACLVE","CICLVE","EMNUM","CTCTAB","BNCLVE","ASTCTA","BCFCHA","BCREFR"},         {}},
        {{"CALCON"},{"PACLVE"},{"CICLVE"},    {"CTCLVE"},     {"OTFOLI"}, {"PACLVE","COCCOS","CAANIO","CACONS","CAPMNO","CAREPR","CAESP","TNTIPO","OTFOLI","CMCLVE","OTSUCM","SRCLVE"},{}},
        {{"AUXASI"},{"PACLVE"},{},    {},             {"OTFOLI"},{"PACLVE","OTFOLI","EMNUM","AXCLVE","AXIDEN","AXCODE"},{}},
        {{"CAPROS"},{"PACLVE"},{"CICLVE"},    {"CTCLVE"},     {},{"CLNEG"},{}},
        {{"CRMSUC"},{},{},{},{},{"CLNEG","SUCLVE"},{}},
        {{"CTENIV"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","MSTCVE","NINIV1","NINIV2","NINIV3","NINIV4","NINIV5","NINIV6","NINIV7","NINIV8","NINIV9","NINIVA"},{}},
        {{"CTESUC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CICLVE","SUCLVE","CTCLVE","CNCONS"},{}},
        {{"CTPERS"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","CNTIPO","CNCONS"},{}},
        {{"CAPROC"},{},{},{},{},{"CLNEG","CNTIPO"},{}},
        {{"DETBXN"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"DEOTRA"},{"PACLVE","CICLVE","DEPCTE","DENUME","EMNUM","BECPTO","BECOEM","PLCONS","ACSTAT"},{}},
        {{"DISCXC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CICLVE","DICCOS","DIORDN","DINOMI","DISERF","DIFOLF","FDAIMP","DISERN","DIFOLN","NCAIMP","CTCLVE","SRCLVE","FTSUCS","DITDOC","DIFECM"},{}},
        {{"FACCXC"},{"FDPACL"},{"FDCICL"},{},{},{"FDPACL","FDCICL","FDSUFI","FDDFOL","FDAIMP","FDCECO","FDORCL","FDDOFE","FDSECL","FDSUCS","FDNUNO","FDCLCL","FDUCXC","FDCEIM"},{}},
        {{"FAPCOM"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"COPYME","CLNEG"},{}},
        {{"FAPDEC"},{},{},{},{},{"COPYME","SRCLVE","DEPROD","CLNEG"},{}},
        {{"FASCON"},{"PACLVE"},{},{},{"OTFOLI"},{"PACLVE","OTFOLI","EMNUM","CMCLVE"},{}},
        {{"FOLIOS"},{"PACLVE"},{},{},{},{"PACLVE","FOARCH","FOCAMP","FOKEYA","FOKEYN"},{}},
        {{"HISASI"},{"PACLVE"},{"CICLVE"},{"CTCLVE","EMNCTE","ASPCTE"},{"ASOTRA"},{"PACLVE","CICLVE","CTCLVE","ASPCTE","RPCLVE","ASOTRA","EMNUM","ASFMOV","ASALTA","EMAPPA","EMAPMA","EMNOMB","COCCOS","ASOTRA","SRCLVE","OTSUCM","EMNCTE","EMCISC"},{}},
        {{"HISEMC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","CTCLVE","OTFOLI","CTCTAB","EMNCTA","BNCLVE","VDFOLI","EMNUM","ENSUCS","BCSTAT"},{}},
        {{"HISIMR"},{"PACLVE"},{"CICLVE"},{"CTCLVE","RPPCTE"},{},{"PACLVE","CICLVE","RPPCTE","RPCLVE","IMFECA"},{}},
        {{"HISLVL"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"OTFOLI"},{"PACLVE","CICLVE","DEPCTE","EMNUM","ASALTA","LVCLVE"},{}},
        {{"HISPTO"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"OTFOLI"},{"PACLVE","CICLVE","DEPCTE","EMNUM","ASALTA","SECLVE","PTCLVE"},{}},
        {{"HISTES"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE"},{"OTFOLI"},{"PACLVE","CICLVE","DEPCTE","EMNUM","ASALTA","ASNIV1","ASNIV2","ASNIV3","ASNIV4","ASNIV5","ASNIV6","ASNIV7","ASNIV8","ASNIV9","ASNIVA"},{}},
        {{"IDSED1"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ISPCTE"},{},{"PACLVE","CICLVE","ISPCTE","RPCLVE","ISTMOV","ISAPPA","ISAPMA","ISNOMB","ISFMOV","MSFTRA","ISNEMP","ISNIMS","MSFTRA"},{}},
        {{"IDSED2"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ISPCTE"},{},{"PACLVE","CICLVE","ISPCTE","RPCLVE","ISTMOV","ISAPPA","ISAPMA","ISNOMB","ISFMOV","CTCLVE","ISNEMP"},{}},
        {{"IDSED3"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ISPCTE"},{},{"PACLVE","CICLVE","ISPCTE","RPCLVE","ISTMOV","ISAPPA","ISAPMA","ISNOMB","ISFMOV","MSFTRA","ISNEMP","FOMOEM","MSHORA"},{}},
        {{"IMP80"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CICLVE","CTCLVE","IMTIPO","IMANIO","IMMES","IMCONS"},{}},
        {{"IMP80A"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CICLVE","CTCLVE","FITIPO","FIANIO","FIMES","FICONS"},{}},
        {{"IMP80B"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CICLVE","CTCLVE","CSTIPO","CSANIO","CSMES","CSCONS"},{}},
        {{"IMPHIS"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"},{"PACLVE","CICLVE","OTFOLI","EMNUM","CAANIO","AINUME","CACONS","AIMPRO"},{}},
        {{"IMSMOV"},{"PACLVE"},{"CICLVE"},{"CTCLVE","MSECTE"},{"MSOTRA"},{"PACLVE","CICLVE","MSPCTE","CTCLVE","RPCLVE","EMNUM","MSFMOV","MSHORA","MSCLVE","MSLINC","MSNINC","MSOTRA","MSECTE","MSFTRA","MSFEXT","MSFCAP","FECACE","IMSHRI","MSCCOS","OTSUCM"},{}},
        {{"MESREP"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"},{"PACLVE","CICLVE","ENSUCS","BNCLVE","EMNUM","EMNCTA","SYFCHA","SYHORA"},{}},
        {{"MESOLI"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"},{"PACLVE","CICLVE","BNCLVE","ENSUCS","VDFOLI"},{}},
        {{"MEAUTR"},{"PACLVE"},{},{},{},{"PACLVE","BRUSER","DESUCS","VDUAUT","ASFVNC"},{}},
        {{"MESTKB"},{"PACLVE"},{"CICLVE"},{},{},{"PACLVE","CICLVE","BNCLVE"},{}},
        {{"MESTKU"},{"PACLVE"},{"CICLVE"},{},{"OTFOLI"},{"PACLVE","CICLVE","ENSUCS","BNCLVE"},{}},
        {{"MODFAC"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CTCLVE","MFCLVE","FAUSER"},{}},
        {{"MODSTR"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","MSTCVE"},{}},
        {{"MTOCIA"},{"PACLVE"},{"CICLVE"},{},{},{"PACLVE","CICLVE"},{}},
        {{"MTOCON"},{"PACLVE"},{},{},{},{"PACLVE","TITIPO"},{}},
        {{"MTOCTE"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","CT_RFC","CTRZON","CTGRPO","PSINFZ"},{}},
        {{"NOMASC"},{"PACLVE"},{},{"ASPCTE"},{"ASOTRA"},{"PACLVE","ASOTRA","EMNUM","ASPCTE"},{}},
        {{"NOMASI"},{"PACLVE"},{"CICLVE"},{"ASPCTE","CTCLVE","EMNCTE"},{"ASOTRA"},{"PACLVE","ASOTRA","EMNUM","CTCLVE","BNCLVE","ASNIV1","ASNIV2","ASNIV3","ASNIV4","ASNIV5","ASNIV6","ASNIV7","ASNIV8","ASNIV9","ASNIVA","PTCLVE","EMNCTE","ASPCTE","CICLVE","ASALTA","ASSTAT"},{}},
        {{"NOMBXE"},{"PACLVE"},{"CICLVE"},{"CTCLVE","ASPCTE"},{},{"PACLVE","EMNUM","BECPTO","BECOEM","CICLVE","CTCLVE","ASPCTE","BECOEM","PLCONS"},{}},
        {{"NOMCAL"},{"PACLVE"},{"CICLVE"},{"CTCLVE","CAPCTE"},{"OTFOLI"},{"PACLVE","CICLVE","COCCOS","CAANIO","CAPMNO","CACONS","CAREPR","CAESP","TNTIPO","OTFOLI","SRCLVE","OTSUCM","CAANIO","CAPMNO","CACONS","CAREPR","CAESP","CAPCTE","CAFPRO","CANUME","CAREPR","CANUME","CAFINI","CAFPAG"},{}},
        {{"NOMCAN"},{"PACLVE"},{"CICLVE"},{"HCCTE","HCPCTE"},{},{"PACLVE","CICLVE","HCPCTE","HCNUME","HCNUM","HCRPTE"},{}},
        {{"NOMCFC"},{"PACLVE"},{},{},{"OTFOLI"},{"PACLVE","OTFOLI","CMCLVE"},{}},
        {{"NOMCIR"},{"PACLVE"},{"CICLVE"},{"CTCLVE","CIPCTE","CIECTE"},{},{"PACLVE","CICLVE","CIPCTE","CIAPRC","CIMPRC","CIEFED","CIORDN","EMNUM","CIECTE","CINIV1","CINIV2","CINIV3","CINIV4","CINIV5","CINIV6","CINIV7","CINIV8","CINIV9","CINIVA",""},{}},
        {{"NOMDET"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE","EMNCTE"},{"DEOTRA"},{"PACLVE","CICLVE","DEPCTE","DENUME","DERPTE","DENUM","DECPTO","DEFCTO","DEANOP","DEMESP","DEGVAR","EMNCTE","DENIV1","DENIV2","DENIV3","DENIV4","DENIV5","DENIV6","DENIV7","DENIV8","DENIV9","DENIVA","CTCLVE","DEDIAP","DECCTE","DEACAL","DEPERI","DEOTRA","DEGVAR","DESERF","DEFOFA","DECCOS","DESERV","DEPSTO","DEFCAN","DEREGP","DESUCS","DESERV"},{}},
        {{"NOMFAS"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"ASOTRA"},{"PACLVE","ASOTRA","EMNUM","CMCLVE"},{}},
        {{"NOMFEM"},{"PACLVE"},{"CICLVE"},{"EMNCTE"},{},{"PACLVE","CICLVE","CTPCLV","EMNUM","CMCLVE","TITIPO"},{}},
        {{"NOMINC"},{"PACLVE"},{"CICLVE"},{"EMNCTE","CTCLVE"},{"ASOTRA"},{"PACLVE","CAANIO","CAPMNO","CACONS","ASOTRA","EMNUM","INNURE","CMCLVE","INFMOV","CICLVE","INCCOS","TNTIPO","INCTEE","CAESP","SRCLVE","OTSUCM","INAPTR","INPRTI","CICLVE","CTCLVE","EMNCTE"},{}},
        {{"NOMMCO"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"},{"PACLVE","SRCLVE","CICLVE","CTCLVE","OTFOLI","CMCLVE","EMNUM","TITIPO"},{}},
        {{"NOMNET"},{"PACLVE"},{"CICLVE"},{"NNPCTE","CTCLVE","EMNCTE"},{"NNOTRA"},{"PACLVE","CICLVE","NNPCTE","NNNUME","NNRPTE","NNNUM","NNCPTO","NNTIPA","NNBCIA","NNCCIA","NNFOLI","NNANOP","EMNCTE","NNNIV1","NNNIV2","NNNIV3","NNNIV4","NNNIV5","NNNIV6","NNNIV7","NNNIV8","NNNIV9","NNNIVA","NNNOMB","NNRPTE","NNCPTO","NNMESP","NNDIAP","NNFPAG","NNOTRA","NNFCAN","NNSTAT","NNSUCS","MSTCVE"},{}},
        {{"NOMRES"},{"PACLVE"},{"CICLVE"},{"REPCTE","CTCLVE"},{"REOTRA"},{"PACLVE","CICLVE","REPCTE","REANIO","REMES","REOTRA","RENUM","RECPTO","REPART"},{}},
        {{"NOTCRE"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","CTCLVE","SRCLVE","NCSUFI","NCDFOL","NCAIMP","NCDOFE","NCSUFI","OTFOLI"},{}},
        {{"ORDEMP"},{"PACLVE"},{},{"OEPCTE"},{"OTFOLI"},{"PACLVE","VCNUM","EMCURP","OTFOLI","OEPCTE"},{}},
        {{"ORDFIN"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","CTCLVE","OTFOLI"},{}},
        {{"ORDTRA"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","SRCLVE","CTCLVE","RPCLVE","BNCLVE","EFCLVE","OTFOLI","OTSUCS","OTSUCM","OTSUCO","OTDESC","OTLONG","OTFCHA","OTSTAT","OTPCRM","TNTIPO","COCCOS","CTPCLV","SYFCHA","PSINFZ"},{}},
        {{"PROCAL"},{"PACLVE"},{"CICLVE"},{"CAPCTE","CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","SRCLVE","OTSUCM","COCCOS","CAANIO","CAPMNO","TNTIPO","OTFOLI",""},{}},
        {{"RHCPAR"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","CTCLVE","OTFOLI"},{}},
        {{"RHMEMP"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"EMCURP","PACLVE","EMNUM","EMAPPA","EMAPMA","EMNOMB","EMCURP","CICLVE","CTCLVE","EMNCTE","EMNKNM","EMCDJF","EMACTZA","EMSTAT","EMPTO3","EMNCTA","EMFING","EMCISS","EMDVSS","EMMAIL","EMRFC"},{}},
        {{"RHMEMC"},{"PACLVE"},{"CICLVE"},{"ASPCTE","EMNCTE"},{},{"PACLVE","CICLVE","ASPCTE","EMCURP","NOIMSS","EMNUM","NOTARJ"},{}},
        {{"RHPLEM"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","MSTCVE","PLCONS","EMNUM"},{}},
        {{"TABBCO"},{"PACLVE"},{},{},{},{"PACLVE","BNCLVE"},{}},
        {{"TABPTO"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","SECLVE","PTCLVE","PTNOMB"},{}},
        {{"TABPTOC"},{"PACLVE"},{},{"CTCLVE"},{},{"PACLVE","CTCLVE","SECLVE","PTCLVE"},{}},
        {{"TABSTR"},{"PACLVE"},{},{},{},{"PACLVE","CTPCLV","SECLVE","SEDESC"},{}},
        {{"TABSUP"},{"PACLVE"},{"CICLVE"},{"CTCLVE"},{},{"PACLVE","CICLVE","CTCLVE","SUTIPO","SUANTI"},{}},
        {{"TABVEN"},{"PACLVE"},{},{},{"ASOTRA"},{"PACLVE","NCCICL","FDCLCL","ASOTRA","ASTIPA"},{}},
        {{"CONFAH"},{"PACLVE"},{"CICLVE"},{"CTCLVE","DEPCTE","EMNCTE"},{"DEOTRA"},{"PACLVE","CICLVE","CTCLVE","DEPCTE","DEOTRA","AFANEJ","AFAPER","DENUM","EMNCTE"},{}},
        {{"PAGFAH"},{"PACLVE"},{"CICLVE"},{"DEPCTE","EMNCTE"},{"DEOTRA"},{"PACLVE","CICLVE","CTCLVE","DEOTRA","DEPCTE","DENUME","DERPTE","DENUM","DECPTO","DEANOP","DEMESP","DEDIAP","EMNCTE","AFANEJ"},{}},
        {{"AUDLAY"},{"AUDPAI"},{"AUDCIA"},{"AUDCTE"},{"AUDORD"},{"AUDPAI","AUDCIA","AUDCTE","AUFOL","AUDNUE","AUDNOM","AUDORD","AUDCON","AUDNUC","AUDBEM","AUDTIP"},{}},
        {{"CFDIST"},{"PACLVE"},{"CICLVE"},{"EMNCTE","CTCLVE"},{"OTFOLI"},{"PACLVE","CICLVE","NNNUME","EMNCTE","CACONS"},{}}
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
    public static ArrayList listaDeInyecciones(int table,ResultSet rs,ArrayList listaDeUpdates) throws SQLException{
        ArrayList listaDeInyecciones = new ArrayList();
        ResultSetMetaData meta = rs.getMetaData();
        while(rs.next()){
            String sql = "INSERT INTO "+Utilerias.DICCIONARIO[table][0][0] +" ";
            String sqlUpdate = "UPDATE "+Utilerias.DICCIONARIO[table][0][0]+" SET ";
            StringBuilder columnNames = new StringBuilder();
            StringBuilder bindVariables = new StringBuilder();
            StringBuilder bindVariablesForUpdate = new StringBuilder();
            StringBuilder whereForUpdate = new StringBuilder("WHERE ");
            boolean llavePrimariaParaIndexadores = false;
            /* LLENARÉ LAS SENTANCIAS RESULTADO POR RESULTADO, ITERANDO POR TODAS LAS COLUMNAS */
            for (int i = 1; i <= meta.getColumnCount(); i++){
                /* SI NO SON LA PRIMERA VEZ QUE ENTRA, COLOCA LOS INDEXADORES */
                if (i > 1) {
                    columnNames.append(", ");
                    bindVariables.append(", ");
                    if(!llavePrimariaParaIndexadores){
                        bindVariablesForUpdate.append(", ");
                    } else {
                        whereForUpdate.append(" AND ");
                    }
                }
                /* REVISAMOS SI SON LLAVES PRIMARIAS */
                boolean pk = false;
                for (int j = 0; j < DICCIONARIO[table][5].length; j++) {
                    if(DICCIONARIO[table][5][j].equals(meta.getColumnName(i))){
                        pk = true;
                        break;
                    }
                }
                llavePrimariaParaIndexadores = pk;
                /* CONSTRUYENDO LAS SENTENCIAS PARCIALES, LAS COLUMNAS PARA LOS INSERTS */
                columnNames.append(meta.getColumnName(i));
                /* SEPARAMOS LOS QUE SON VARCHARS DE LOS QUE SON NUMÉRICOS */
                boolean isNull = rs.getString(meta.getColumnName(i))==null || rs.getString(meta.getColumnName(i)).equals("null") || rs.getString(meta.getColumnName(i)).equals("NULL");
                if(meta.getColumnClassName(i).equals("java.lang.String")){
                    /* CONSTRUYENDO LAS SENTENCIAS PARCIALES, LOS VALORES DE LOS INSERTS */
                    bindVariables.append("'"+rs.getString(meta.getColumnName(i))+"'");
                    /* CONSTRUYENDO LAS SENTENCIAS PARCIALES, SI ES LLAVE PRIMARIA LA COLUMNA SERA EN EL WHERE, SINO EN EL SET */
                    if(pk){
                        if(isNull){
                            whereForUpdate.append(meta.getColumnName(i)+"="+rs.getString(meta.getColumnName(i)));
                        } else {
                            whereForUpdate.append(meta.getColumnName(i)+"='"+rs.getString(meta.getColumnName(i))+"'");
                        }
                    } else {
                        if(isNull){
                            bindVariablesForUpdate.append(meta.getColumnName(i)+"="+rs.getString(meta.getColumnName(i)));
                        } else {
                            bindVariablesForUpdate.append(meta.getColumnName(i)+"='"+rs.getString(meta.getColumnName(i))+"'");
                        }
                    }
                } else {
                    bindVariables.append(rs.getString(meta.getColumnName(i)));
                    if(pk){
                        whereForUpdate.append(meta.getColumnName(i)+"="+rs.getString(meta.getColumnName(i)));
                    } else {
                        bindVariablesForUpdate.append(meta.getColumnName(i)+"="+rs.getString(meta.getColumnName(i)));
                    }
                }
            }
            /* TRUNCO EL ÚLTIMO INDEXADOR */
            if(llavePrimariaParaIndexadores){
                bindVariablesForUpdate.delete(bindVariablesForUpdate.length()-(", ".length()),
                        bindVariablesForUpdate.length());
            } else {
                whereForUpdate.delete(whereForUpdate.length()-("AND ".length()),
                        whereForUpdate.length());
            }
            sql += "("+columnNames+") VALUES (" + bindVariables + ")";
            sqlUpdate += bindVariablesForUpdate + " " + whereForUpdate;
            listaDeInyecciones.add(sql);
            listaDeUpdates.add(sqlUpdate);
        }
        return listaDeInyecciones;
    }
    
    /*
     * (2017-02-03) public static ArrayList listaDeInyecciones(String table,ResultSet rs,ArrayList listaDeUpdates,String whereclause) throws SQLException{
        StringBuilder columnNames = new StringBuilder();
        ArrayList listaDeInyecciones = new ArrayList();
        ResultSetMetaData meta = rs.getMetaData();
        while(rs.next()){
            String sql = "INSERT INTO "+table +" ";
            String sqlUpdate = "UPDATE "+table+" SET ";
            StringBuilder bindVariables = new StringBuilder();
            StringBuilder bindVariablesForUpdate = new StringBuilder();
            for (int i = 1; i <= meta.getColumnCount(); i++){
                if (i > 1) {
                    columnNames.append(", ");
                    bindVariables.append(", ");
                    bindVariablesForUpdate.append(", ");
                }
                columnNames.append(meta.getColumnName(i));
                if(meta.getColumnClassName(i).equals("java.lang.String")){
                    bindVariables.append("'"+rs.getString(meta.getColumnName(i))+"'");
                    bindVariablesForUpdate.append(meta.getColumnName(i)+"='"+rs.getString(meta.getColumnName(i))+"'");
                } else {
                    bindVariables.append(rs.getString(meta.getColumnName(i)));
                    bindVariablesForUpdate.append(meta.getColumnName(i)+"="+rs.getString(meta.getColumnName(i)));
                }
            }
            sql += "("+columnNames+") VALUES (" + bindVariables + ")";
            sqlUpdate += bindVariablesForUpdate + " " + whereclause;
            listaDeInyecciones.add(sql);
            listaDeUpdates.add(sqlUpdate);
        }
        return listaDeInyecciones;
    }
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
    public static int injectToTable(String tabla, ArrayList inyecciones,ArrayList updatesBackup){
        int inyectados = 0;
        ERRORES = "";
        for (int i = 0; i < inyecciones.size(); i++) {
            if(makeInjection(""+inyecciones.get(i),""+updatesBackup.get(i))){
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
    public static boolean makeInjection(String inyeccion,String update){
        Connection conexion = ConectionPool.getInstance().getConfirm();
            try{
                Statement stmt = conexion.createStatement();
                /* DANGER ZONE - TOKEN MAGICO */
                stmt.executeUpdate(inyeccion);
                //System.out.println(inyeccion);
            } catch(Exception e){
                String stringedexception = e.toString().replace("\n","\t\t")+"\n";
                ERRORES += inyeccion+"\n";
                ERRORES += stringedexception;
                if(stringedexception.contains("Duplicate key value specified")){
                    ERRORES += "          ---Se intentará hacer update en lugar de inyección:\n";
                    ERRORES += update+"\n";
                    try{
                        Statement stmt = conexion.createStatement();
                        /* DANGER ZONE - TOKEN MAGICO */
                        stmt.executeUpdate(update);
                        //System.out.println(update);
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
