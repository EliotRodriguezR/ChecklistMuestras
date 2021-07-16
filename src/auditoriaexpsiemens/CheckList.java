/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoriaexpsiemens;

/**
 *
 * @author Rene
 */
public class CheckList {
    
    private boolean tienePedimentoCompleto;
    private boolean tienePedimentoSimplificado;
    private boolean tieneArchivoM;
    private boolean tieneArchivoErr;
    private boolean tieneArchivoE;
    private boolean tieneArchivoA;
    
    private boolean tieneGuiaAWB; //AWB
    private boolean tieneGuiaBL; //BL
    private boolean tienePackingList;
    private boolean tieneFacturasComerciales; 
    private boolean tieneEdocuments;
    private boolean tieneCOVESXML; // xml
    private boolean tieneCOVESPDF; // pdf
    
    private boolean tieneManifestacionValor; // MV
    private boolean tieneHojaCalculo; // HC
    private boolean tieneCertificadoOrigen; // CO

    public boolean carpetaCompleta(){
    
        return (tienePedimentoCompleto && tienePedimentoSimplificado
                && tieneArchivoM && tieneArchivoErr
                && tieneArchivoE && tieneArchivoA
                && tieneFacturasComerciales && tieneManifestacionValor
                && tieneHojaCalculo && (tieneGuiaAWB || tieneGuiaBL)
                && tieneEdocuments && tieneCOVESXML && tieneCOVESPDF);
    
    }
    
    
    /**
     * @return the tienePedimentoCompleto
     */
    public boolean isTienePedimentoCompleto() {
        return tienePedimentoCompleto;
    }

    /**
     * @param tienePedimentoCompleto the tienePedimentoCompleto to set
     */
    public void setTienePedimentoCompleto(boolean tienePedimentoCompleto) {
        this.tienePedimentoCompleto = tienePedimentoCompleto;
    }

    /**
     * @return the tienePedimentoSimplificado
     */
    public boolean isTienePedimentoSimplificado() {
        return tienePedimentoSimplificado;
    }

    /**
     * @param tienePedimentoSimplificado the tienePedimentoSimplificado to set
     */
    public void setTienePedimentoSimplificado(boolean tienePedimentoSimplificado) {
        this.tienePedimentoSimplificado = tienePedimentoSimplificado;
    }

    /**
     * @return the tieneArchivoM
     */
    public boolean isTieneArchivoM() {
        return tieneArchivoM;
    }

    /**
     * @param tieneArchivoM the tieneArchivoM to set
     */
    public void setTieneArchivoM(boolean tieneArchivoM) {
        this.tieneArchivoM = tieneArchivoM;
    }

    /**
     * @return the tieneArchivoErr
     */
    public boolean isTieneArchivoErr() {
        return tieneArchivoErr;
    }

    /**
     * @param tieneArchivoErr the tieneArchivoErr to set
     */
    public void setTieneArchivoErr(boolean tieneArchivoErr) {
        this.tieneArchivoErr = tieneArchivoErr;
    }

    /**
     * @return the tieneArchivoE
     */
    public boolean isTieneArchivoE() {
        return tieneArchivoE;
    }

    /**
     * @param tieneArchivoE the tieneArchivoE to set
     */
    public void setTieneArchivoE(boolean tieneArchivoE) {
        this.tieneArchivoE = tieneArchivoE;
    }

    /**
     * @return the tieneArchivoA
     */
    public boolean isTieneArchivoA() {
        return tieneArchivoA;
    }

    /**
     * @param tieneArchivoA the tieneArchivoA to set
     */
    public void setTieneArchivoA(boolean tieneArchivoA) {
        this.tieneArchivoA = tieneArchivoA;
    }

    /**
     * @return the tienePackingList
     */
    public boolean isTienePackingList() {
        return tienePackingList;
    }

    /**
     * @param tienePackingList the tienePackingList to set
     */
    public void setTienePackingList(boolean tienePackingList) {
        this.tienePackingList = tienePackingList;
    }

    /**
     * @return the tieneFacturasComerciales
     */
    public boolean isTieneFacturasComerciales() {
        return tieneFacturasComerciales;
    }

    /**
     * @param tieneFacturasComerciales the tieneFacturasComerciales to set
     */
    public void setTieneFacturasComerciales(boolean tieneFacturasComerciales) {
        this.tieneFacturasComerciales = tieneFacturasComerciales;
    }

    /**
     * @return the tieneEdocuments
     */
    public boolean isTieneEdocuments() {
        return tieneEdocuments;
    }

    /**
     * @param tieneEdocuments the tieneEdocuments to set
     */
    public void setTieneEdocuments(boolean tieneEdocuments) {
        this.tieneEdocuments = tieneEdocuments;
    }

    /**
     * @return the tieneCOVESXML
     */
    public boolean isTieneCOVESXML() {
        return tieneCOVESXML;
    }

    /**
     * @param tieneCOVESXML the tieneCOVESXML to set
     */
    public void setTieneCOVESXML(boolean tieneCOVESXML) {
        this.tieneCOVESXML = tieneCOVESXML;
    }

    /**
     * @return the tieneCOVESPDF
     */
    public boolean isTieneCOVESPDF() {
        return tieneCOVESPDF;
    }

    /**
     * @param tieneCOVESPDF the tieneCOVESPDF to set
     */
    public void setTieneCOVESPDF(boolean tieneCOVESPDF) {
        this.tieneCOVESPDF = tieneCOVESPDF;
    }

    /**
     * @return the tieneManifestacionValor
     */
    public boolean isTieneManifestacionValor() {
        return tieneManifestacionValor;
    }

    /**
     * @param tieneManifestacionValor the tieneManifestacionValor to set
     */
    public void setTieneManifestacionValor(boolean tieneManifestacionValor) {
        this.tieneManifestacionValor = tieneManifestacionValor;
    }

    /**
     * @return the tieneHojaCalculo
     */
    public boolean isTieneHojaCalculo() {
        return tieneHojaCalculo;
    }

    /**
     * @param tieneHojaCalculo the tieneHojaCalculo to set
     */
    public void setTieneHojaCalculo(boolean tieneHojaCalculo) {
        this.tieneHojaCalculo = tieneHojaCalculo;
    }

    /**
     * @return the tieneCertificadoOrigen
     */
    public boolean isTieneCertificadoOrigen() {
        return tieneCertificadoOrigen;
    }

    /**
     * @param tieneCertificadoOrigen the tieneCertificadoOrigen to set
     */
    public void setTieneCertificadoOrigen(boolean tieneCertificadoOrigen) {
        this.tieneCertificadoOrigen = tieneCertificadoOrigen;
    }

    /**
     * @return the tieneGuiaAWB
     */
    public boolean isTieneGuiaAWB() {
        return tieneGuiaAWB;
    }

    /**
     * @param tieneGuiaAWB the tieneGuiaAWB to set
     */
    public void setTieneGuiaAWB(boolean tieneGuiaAWB) {
        this.tieneGuiaAWB = tieneGuiaAWB;
    }

    /**
     * @return the tieneGuiaBL
     */
    public boolean isTieneGuiaBL() {
        return tieneGuiaBL;
    }

    /**
     * @param tieneGuiaBL the tieneGuiaBL to set
     */
    public void setTieneGuiaBL(boolean tieneGuiaBL) {
        this.tieneGuiaBL = tieneGuiaBL;
    }
    
}
