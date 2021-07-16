/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoriaexpsiemens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rene
 */
public class ValidaDocumento {
    
    private String nombreArchivo;
    private CheckList checkList;
    private Operacion operacion;
    
    public ValidaDocumento(){
    }
    
    public void checkTipoDocumento(){
    
        Pattern pattPedCompleto = Pattern.compile ("Pedimento\\scompleto\\.pdf");  //  ("P\\d{4}-\\d{6}.pdf");   
        Matcher mPedCompleto = pattPedCompleto.matcher(nombreArchivo);
        
        if (mPedCompleto.find()) {
            checkList.setTienePedimentoCompleto(true);
        }
        
        Pattern pattPedSimpl = Pattern.compile  ("Pedimento\\ssimplificado\\.pdf");  //     ("V\\d{4}-\\d{6}.pdf");
        Matcher mPedSimpl = pattPedSimpl.matcher(nombreArchivo);
        
        if (mPedSimpl.find()) {
            checkList.setTienePedimentoSimplificado(true);
        }
        
        // en caso de pedimento de rectificacion para otros clientes
        // donde no se estan renombrando pedimentos
        /*Pattern pattPedCompletoR = Pattern.compile("R\\d{4}-\\d{6,8}.pdf");  // ("Pedimento\\scompleto\\.pdf")
        Matcher mPedCompletoR = pattPedCompletoR.matcher(nombreArchivo);
        
        if (mPedCompletoR.find()) {
            checkList.setTienePedimentoCompleto(true);
        }
        
        Pattern pattPedSimplR = Pattern.compile("W\\d{4}-\\d{6,8}.pdf");  //  ("Pedimento\\ssimplificado\\.pdf");
        Matcher mPedSimplR = pattPedSimplR.matcher(nombreArchivo);
        
        if (mPedSimplR.find()) {
            checkList.setTienePedimentoSimplificado(true);
        }
        */
        // end pedidmento de rectificacion
        
        // validacion y pago
        Pattern pattArchivoM = Pattern.compile("(m\\d{7}\\.\\d{3})");
        Matcher mArchivoM = pattArchivoM.matcher(nombreArchivo);
        
        if (mArchivoM.find()) {
            checkList.setTieneArchivoM(true);
        }
        
        Pattern pattArchivoErr = Pattern.compile("(m\\d{7}\\.err)|(m\\d{7}\\.\\d{3}-err)");
        Matcher mArchivoErr = pattArchivoErr.matcher(nombreArchivo);
        
        if (mArchivoErr.find()) {
            checkList.setTieneArchivoErr(true);
        }
        
        Pattern pattArchivoE = Pattern.compile("(e\\d{7}\\.\\d{3})");
        Matcher mArchivoE = pattArchivoE.matcher(nombreArchivo);
        
        if (mArchivoE.find()) {
            checkList.setTieneArchivoE(true);
        }
        
        Pattern pattArchivoA = Pattern.compile("(a\\d{7}\\.\\d{3})");
        Matcher mArchivoA = pattArchivoA.matcher(nombreArchivo);
        
        if (mArchivoA.find()) {
            checkList.setTieneArchivoA(true);
        }
        
        // packing list y facturas
        Pattern pattPackingLst = Pattern.compile("Packing\\slist.*\\.pdf");
        Matcher mPackingList = pattPackingLst.matcher(nombreArchivo);
        
        if (mPackingList.find()) {
            checkList.setTienePackingList(true);
        }
        
        Pattern pattFacturas = Pattern.compile("Factura\\scomercial.*\\.pdf");
        Matcher mFacturas = pattFacturas.matcher(nombreArchivo);
        
        if (mFacturas.find()) {
            checkList.setTieneFacturasComerciales(true);
        }
        
        // manifestaciones valor y hoja calculo
        Pattern pattMV = Pattern.compile("MV.pdf");
        Matcher mMV = pattMV.matcher(nombreArchivo);
        
        if (mMV.find()) {
            checkList.setTieneManifestacionValor(true);
        }
        
        Pattern pattHC = Pattern.compile("HC.pdf");
        Matcher mHC = pattHC.matcher(nombreArchivo);
        
        if (mHC.find()) {
            checkList.setTieneHojaCalculo(true);
        }
        
        // Guias
        Pattern pattAWB = Pattern.compile("AWB.pdf");
        Matcher mAWB = pattAWB.matcher(nombreArchivo);
        
        if (mAWB.find()) {
            checkList.setTieneGuiaAWB(true);
        }
        
        Pattern pattBL = Pattern.compile("BL.pdf");
        Matcher mBL = pattBL.matcher(nombreArchivo);
        
        if (mBL.find()) {
            checkList.setTieneGuiaBL(true);
        }
        
        // COVE xml
        Pattern pattCoveXML = Pattern.compile("COVE([A-Z0-9]{9}).xml");
        Matcher mCoveXml = pattCoveXML.matcher(nombreArchivo);
        
        if (mCoveXml.find()) {
            checkList.setTieneCOVESXML(true);
            operacion.agregaCove(nombreArchivo);
        }
        
        Pattern pattCovePDF = Pattern.compile("COVE([A-Z0-9]{9}).pdf");
        Matcher mCovePDF = pattCovePDF.matcher(nombreArchivo);
        
        if (mCovePDF.find()) {
            checkList.setTieneCOVESPDF(true);
            operacion.agregaCovePDF(nombreArchivo);
        }
        
        // edocuments
        Pattern pattEdocuments = Pattern.compile("\\b(\\d[A-Z0-9]{12}).*.pdf");
        Matcher mEdocuments = pattEdocuments.matcher(nombreArchivo);
        
        if (mEdocuments.find()) {
            checkList.setTieneEdocuments(true);
            operacion.agregaEdocument(nombreArchivo);
        }
        
    } // end validation

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the checkList
     */
    public CheckList getCheckList() {
        return checkList;
    }

    /**
     * @param checkList the checkList to set
     */
    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    /**
     * @return the operacion
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

   
}
