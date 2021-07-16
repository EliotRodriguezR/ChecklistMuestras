package auditoriaexpsiemens;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rene
 */
public class AuditoriaCoves {
    
    private ArrayList<DatosCove> operaciones = null;
    Operacion operacion;
    
    public AuditoriaCoves(Operacion operacion){
        this.operacion = operacion;
        
        operaciones = new ArrayList();
        findOperaciones();
        
        /*
        if (operaciones != null) {
            int cuenta = 1;
            int i = 0;
            for (DatosCove dc: operaciones) {
            
                File fCove = new File("C:\\Users\\Rene\\Documents\\files\\recovem\\x\\" + dc.getEdocum() + ".pdf");
                
                
                if (!fCove.exists()) {
                    //System.out.println(cuenta + " '" + dc.getReferencia() + "'," + dc.getEdocum());
                    System.out.print("'" + dc.getReferencia() + "',");
                    if (i == 5) {
                        System.out.println();
                        i = 0;
                    }
                    i++;
                    cuenta++;
                }
                
            }
        }
        */
    }
    
     private void findOperaciones(){
         
        Connection con = null;
        PreparedStatement pstmt = null;
       
        ResultSet rs = null;
     
        String str_query = "select ped.referencia, ped.nomcli, dv.edocum from pedimentos ped, t_firbank fb, doctosvucem  dv" +
                " where ped.referencia = fb.referencia" +
                " and ped.pedimento = fb.pedimento" +
                " and ped.referencia = dv.trafico" +
                " and ped.con_rec = dv.con_rec " +
                //" and ped.status = 'ACT'" +
                " and ped.cliente in ('98','99')" +
                 // 20 nov 59 Astra, 26 junio para auditoria de Ortho Clinical 1374
                //" and ped.cliente in ('59')" +
                
                // por operacion
                //" and ped.referencia = '17-012404'" +
                //" and ped.fecha_pago >= '01/11/2016' and ped.fecha_pago <= '30/11/2016'" +
                " and ped.pedimento = ? and aduana_des = ? and patente = ?" +
                
                " and dv.cvedocto = 'F' and dv.edocum like 'COVE%' and dv.edocum is not null ORDER BY ped.pedimento";
                
        try {
            con = JDBCUtils.getConnection();
            
            pstmt = con.prepareStatement(str_query);
            pstmt.setString(1, operacion.getPedimento().trim());
            pstmt.setString(2, operacion.getAduana_des().trim());
            pstmt.setString(3, operacion.getPatente().trim());
            
            rs = pstmt.executeQuery();
            
            DatosCove datosCove = null;
            int indice = 1;
            
            while (rs.next()) {
                 datosCove = new DatosCove();
                 datosCove.setEdocum(rs.getString("edocum"));
                 datosCove.setReferencia(rs.getString("referencia"));
                 
                 getOperaciones().add(datosCove);
                 
                 indice++;
            } 
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
         
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqle) {
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException sqle) {
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException sqle) {
            }
        }
     
    } // end consulta operaciones
    
    public static void main(String [] args){
        new AuditoriaCoves(null);
    }

    /**
     * @return the operaciones
     */
    public ArrayList<DatosCove> getOperaciones() {
        return operaciones;
    }

    /**
     * @param operaciones the operaciones to set
     */
    public void setOperaciones(ArrayList<DatosCove> operaciones) {
        this.operaciones = operaciones;
    }
    
}
