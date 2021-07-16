package auditoriaexpsiemens;

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
public class AuditoriaEdocuments {
    
    private ArrayList<DatosCove> operaciones = null;
    Operacion operacion;
    
    public AuditoriaEdocuments(Operacion operacion){
        this.operacion = operacion;
        
        operaciones = new ArrayList();
        findOperaciones();
        
        /*
        if (operaciones != null) {
            int cuenta = 1;
            int i = 0;
            for (DatosCove dc: operaciones) {
            
               //File fCove = new File("C:\\Users\\Rene\\Documents\\files\\recovem\\RecordKeeping\\Septiembre\\MOSHDX1724033127001807\\" + dc.getEdocum() + " Registro.pdf");
                
               //if (!fCove.exists()) {
               //    System.out.println(cuenta + " '" + dc.getReferencia() + "'," + dc.getEdocum());
               //     cuenta++;
               //}
                if (operacion != null) {
                    ArrayList<String> lstEd = operacion.getListEdocument();
                    Pattern sEd = Pattern.compile(dc.getEdocum());
                    
                    for (String pi: lstEd) {
                        
                        Matcher m = sEd.matcher(pi);
                        if (m.find()) {
                            System.out.println("------------------------------------------------");
                        } else {
                            System.out.println(pi + " " + dc.getEdocum());
                        }
                    }
                }
            } // end for
        } */
        
    }
    
     private void findOperaciones(){
         
        Connection con = null;
        PreparedStatement pstmt = null;
       
        ResultSet rs = null;
     
        String str_query = "select distinct ped.referencia, ped.nomcli, dv.edocum from pedimentos ped, t_firbank fb, doctosvucem  dv" +
                " where ped.referencia = fb.referencia" +
                " and ped.pedimento = fb.pedimento" +
                " and ped.referencia = dv.trafico" +
                " and ped.con_rec = dv.con_rec" +
                //" and ped.status = 'ACT'" +
                " and ped.cliente in ('98','99')" +
                // 26 junio para auditoria de Ortho Clinical
                //" and ped.cliente in ('59')" +
                
                // para auditar por rango de fechas
                //" and ped.fecha_pago >= '01/11/2016' and ped.fecha_pago <= '30/11/2016'" +
                
                // para auditar por pedimento especifico
                " and ped.pedimento = ? and aduana_des = ? and patente = ?" +
              
                // SE CAMBIA in ('P', 'G') por is not 'F' para considerar todo edocument menos cove
                " and dv.cvedocto <> 'F' and dv.edocum is not null ORDER BY ped.pedimento"; 
        
              
        
        try {
            con = JDBCUtils.getConnection();
            
            pstmt = con.prepareStatement(str_query);
            pstmt.setString(1, operacion.getPedimento().trim());
            pstmt.setString(2, operacion.getAduana_des().trim());
            pstmt.setString(3, operacion.getPatente().trim());
            
            rs = pstmt.executeQuery();
            
            DatosCove datosCove = null;
            int indice = 0;
            
            while (rs.next()) {
                 datosCove = new DatosCove();
                 datosCove.setEdocum(rs.getString("edocum"));
                 datosCove.setReferencia(rs.getString("referencia"));
                 
                 getOperaciones().add(datosCove);
                 
                 indice++;
            } 
            //System.out.print("\t" + indice);
            
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
        new AuditoriaEdocuments(null);
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
