package dbmigrationforinsiders;

import java.sql.ResultSet;
import javax.swing.UIManager;

/**
 *
 * @author jasalazar
 */
public class DBMigrationForInsiders {

    public static void main(String[] args) {
        test2();
    }
    
    public static void test2(){
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
    } catch(Exception e){
        e.printStackTrace();
    }
        MP_MigratorUI mps = new MP_MigratorUI();
        mps.setVisible(true);
    }
    
    /*public static void test1(){
        String usuario = "jasalazar";
        String password = "veronica_1";
        String entorno = "tstmeca.manpower.com.mx";
        String schema = "powerdata";
        String consulta = "select * from nomasi where CTCLVE = 2 and ASOTRA = 17";

        try{
                ResultSet rs = Utilerias.getResult(
                                Utilerias.getConnection(usuario,password,entorno,schema),
                                consulta);
                System.out.println("CTCTAB\n");
                while (rs.next()) {
                        String CLAVE = rs.getString("CTCTAB");
                        System.out.println(CLAVE);
                }
        }catch(Exception e){
                e.printStackTrace();
        }
    }*/
}
