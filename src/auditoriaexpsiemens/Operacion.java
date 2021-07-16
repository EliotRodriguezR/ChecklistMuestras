/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoriaexpsiemens;

import java.util.ArrayList;

/**
 *
 * @author Rene
 */
public class Operacion {
    
    private String pedimento;
    private String referencia;
    private String aduana_des;
    private String patente;
    
    private String tipoOperacion;
    private String tieneContingencia;
    
    private ArrayList<String> listCoves = null; // xml
    private ArrayList<String> listCovesPDF = null;
    private ArrayList<String> listEdocument = null;

    public Operacion(String nombreDir){
        listCoves = new ArrayList();
        listCovesPDF = new ArrayList();
        listEdocument = new ArrayList();
        
        int len = nombreDir.length();
        aduana_des = nombreDir.substring(len - 14, len - 12);
        patente = nombreDir.substring(len - 11, len - 7);
        pedimento = nombreDir.substring(len - 7, len);     
        //System.out.println("aduana des " + aduana_des + " " + patente + " " + pedimento);
    }
    
    public boolean agregaCove(String _cove){
        return listCoves.add(_cove);
    }
    
    public boolean agregaCovePDF(String _covePDF){
        return listCovesPDF.add(_covePDF);
    }
    
    public boolean agregaEdocument(String _edocument){
        return listEdocument.add(_edocument);
    }
    
    /**
     * @return the pedimento
     */
    public String getPedimento() {
        return pedimento;
    }

    /**
     * @param pedimento the pedimento to set
     */
    public void setPedimento(String pedimento) {
        this.pedimento = pedimento;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the listCoves
     */
    public ArrayList<String> getListCoves() {
        return listCoves;
    }

    /**
     * @param listCoves the listCoves to set
     */
    public void setListCoves(ArrayList listCoves) {
        this.listCoves = listCoves;
    }

    /**
     * @return the listEdocument
     */
    public ArrayList<String> getListEdocument() {
        return listEdocument;
    }

    /**
     * @param listEdocument the listEdocument to set
     */
    public void setListEdocument(ArrayList listEdocument) {
        this.listEdocument = listEdocument;
    }

    /**
     * @return the aduana_des
     */
    public String getAduana_des() {
        return aduana_des;
    }

    /**
     * @param aduana_des the aduana_des to set
     */
    public void setAduana_des(String aduana_des) {
        this.aduana_des = aduana_des;
    }

    /**
     * @return the patente
     */
    public String getPatente() {
        return patente;
    }

    /**
     * @param patente the patente to set
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }

    /**
     * @return the listCovesPDF
     */
    public ArrayList<String> getListCovesPDF() {
        return listCovesPDF;
    }

    /**
     * @param listCovesPDF the listCovesPDF to set
     */
    public void setListCovesPDF(ArrayList<String> listCovesPDF) {
        this.listCovesPDF = listCovesPDF;
    }

    /**
     * @return the tipoOperacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion the tipoOperacion to set
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the tieneContingencia
     */
    public String getTieneContingencia() {
        return tieneContingencia;
    }

    /**
     * @param tieneContingencia the tieneContingencia to set
     */
    public void setTieneContingencia(String tieneContingencia) {
        this.tieneContingencia = tieneContingencia;
    }
}
