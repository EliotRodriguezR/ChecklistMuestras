/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoriaexpsiemens;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rene
 */
public class AuditoriaExpSiemens {

    private boolean esCompleta;
    private String directorio;
    private CheckList checkList;
    public String  tipo_operacion;
    public String tieneContingencia; 
    
    public AuditoriaExpSiemens(String directorio){
        this.directorio = directorio;
    }
    
    public void doAuditar(){
        //System.out.println(directorio);
        File fileDir = new File(directorio);
        //File fileDir = new File("C:\\Users\\Rene\\Documents\\files\\recovem\\RecordKeeping\\Agosto\\" + directorio);
        // en 100.11
        //File fileDir = new File("/home/DIGITALIZACION/SIEMENS/1-AEROPUERTO MAYO - AGOSTO/AGOSTO-2/" + directorio);
        //File fileDir = new File("/home/DIGITALIZACION/SIEMENS/SEPTIEMBRE2017-2/" + directorio);
        
        if(!fileDir.isDirectory()){
            return;
        }
        System.out.print(fileDir.getName() + "\t");
        File [] lstFiles = fileDir.listFiles();
        
        checkList = new CheckList();
        ValidaDocumento validaDocumento = new ValidaDocumento();
        validaDocumento.setCheckList(checkList);
        Operacion operacion = new Operacion(fileDir.getName());
        validaDocumento.setOperacion(operacion);
        
        int c = 1;
        for (File fi: lstFiles) {
            //System.out.println(c + "\t" + fi.getName());
            validaDocumento.setNombreArchivo(fi.getName());
            validaDocumento.checkTipoDocumento();
            c++;
        }
        
        // checkList valida edocuments
        //System.out.println("Total edocuments " + operacion.getListEdocument().size());
        AuditoriaEdocuments auditEdocuments = new AuditoriaEdocuments(operacion);
        // checkList valida coves
        //System.out.println("Total coves " + operacion.getListCoves().size());
        AuditoriaCoves auditoriaCoves = new AuditoriaCoves(operacion);
        
        // validar si operacion es exportacion
        // entonces no lleva manifestaciones
        tipo_operacion = findTipoOperacion(operacion);
        if (tipo_operacion != null && tipo_operacion.equals("2")) {
            checkList.setTieneManifestacionValor(true);
            checkList.setTieneHojaCalculo(true);
        }
        
        tieneContingencia = findCasosGOC(operacion);
        if (tieneContingencia.equals("OC")) {
            checkList.setTieneEdocuments(true);
            checkList.setTieneCOVESXML(true);
            checkList.setTieneCOVESPDF(true);
        }
        
        if (checkList.carpetaCompleta()) {
            this.esCompleta = true;
         // System.out.print("Carpeta completa :) " + "\t"); 
            
            imprimeDetalle();
            
               
            System.out.print(operacion.getListEdocument().size() + "\t" + auditEdocuments.getOperaciones().size() + "\t");
            System.out.println(operacion.getListCoves().size() + "\t" + operacion.getListCovesPDF().size() + "\t" + auditoriaCoves.getOperaciones().size() + "\t\t\t\t\t"+"Carpeta completa c:");
              
        } else {
             this.esCompleta = false;
                
           
            imprimeDetalle();
            
           
            System.out.print(operacion.getListEdocument().size() + "\t" + auditEdocuments.getOperaciones().size() + "\t");
            System.out.println(operacion.getListCoves().size() + "\t" + operacion.getListCovesPDF().size() + "\t" + auditoriaCoves.getOperaciones().size() + "\t\t\t\t\t"+"Carpeta incompleta :C");
             //System.out.print("Carpeta incompleta :( " + "\t");
            
        }
    }
    
    private String findTipoOperacion(Operacion operacion){
         
        Connection con = null;
        PreparedStatement pstmt = null;
        String tipoOperacion = null;
        ResultSet rs = null;
     
        String str_query = "SELECT ped.tipo_movimiento FROM pedimentos ped" +
                " WHERE ped.pedimento = ? and aduana_des = ? and patente = ?";
              
        try {
            con = JDBCUtils.getConnection();
            
            pstmt = con.prepareStatement(str_query);
            pstmt.setString(1, operacion.getPedimento().trim());
            pstmt.setString(2, operacion.getAduana_des().trim());
            pstmt.setString(3, operacion.getPatente().trim());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                 tipoOperacion = rs.getString("tipo_movimiento");
            } 
            //System.out.print("\t" + indice);
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return null;
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
       return tipoOperacion;
    } // end consulta operaciones
    
    private String findCasosGOC(Operacion operacion){
         
        Connection con = null;
        PreparedStatement pstmt = null;
        String tieneContingencia = null;
        ResultSet rs = null;
     
        String str_query = "SELECT cg.clave "
                + "FROM pedimentos ped, casos_g cg " +
                  " WHERE ped.referencia = cg.referencia AND ped.con_rec = cg.con_rec"
                + " AND ped.pedimento = ? and aduana_des = ? and patente = ?"
                + " AND trim(cg.clave) = 'OC'";
              
        try {
            con = JDBCUtils.getConnection();
            
            pstmt = con.prepareStatement(str_query);
            pstmt.setString(1, operacion.getPedimento().trim());
            pstmt.setString(2, operacion.getAduana_des().trim());
            pstmt.setString(3, operacion.getPatente().trim());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                 tieneContingencia = rs.getString("clave");
                 //System.out.print("\t" + tieneContingencia);
            } 
            
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return "";
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
        
       return (tieneContingencia == null ? "" : tieneContingencia.trim());
    } // end consulta operaciones
    
    public  void imprimeDetalle(){
//        System.out.println("CARPETA\t\tTO\tID\tPC\tPS\tERR\tM\tE\tA\tFC\tAWB\tBL\tMV\tHC\tPL\tEDOC PDF\tCOVE PDF\tCOVE XML\tNo\tBD\tBD");
       //hasta aqui todo bien ya esta actualizacion si jala
        //System.out.print();
        System.out.println("\t"+tipo_operacion.trim()+"\t\t"+tieneContingencia.trim());
        System.out.print("\t\t\t\t"+(checkList.isTienePedimentoCompleto() ? "S" : "N") + "\t");
        System.out.print((checkList.isTienePedimentoSimplificado() ? "S" : "N") + "\t");
        System.out.print((checkList.isTieneArchivoErr()? "S" : "N") + "\t");
        System.out.print((checkList.isTieneArchivoM()? "S" : "N") + "\t");
        
        System.out.print((checkList.isTieneArchivoE()? "S" : "N") + "\t");
        System.out.print((checkList.isTieneArchivoA() ? "S" : "N") + "\t");
         
        System.out.print((checkList.isTieneFacturasComerciales() ? "S" : "N") + "\t");
        System.out.print((checkList.isTieneGuiaAWB() ? "S" : "N") + "\t");
        System.out.print((checkList.isTieneGuiaBL() ? "S" : "N") + "\t");
        System.out.print((checkList.isTieneManifestacionValor() ? "S" : "N") + "\t");
        System.out.print((checkList.isTieneHojaCalculo() ? "S" : "N") + "\t");
        
        System.out.print((checkList.isTieneEdocuments()? "S" : "N") + "\t");
        
        System.out.print((checkList.isTieneCOVESPDF()? "S" : "N") + "\t");
        System.out.print((checkList.isTieneCOVESXML()? "S" : "N") + "\t");
        System.out.print((checkList.isTienePackingList()? "S" : "N") + "\t");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AuditoriaExpSiemens("MOSHDX1724033127001714");
    }

    /**
     * @return the directorio
     */
    public String getDirectorio() {
        return directorio;
    }

    /**
     * @param directorio the directorio to set
     */
    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    /**
     * @return the esCompleta
     */
    public boolean isEsCompleta() {
        return esCompleta;
    }

    /**
     * @param esCompleta the esCompleta to set
     */
    public void setEsCompleta(boolean esCompleta) {
        this.esCompleta = esCompleta;
    }
}
