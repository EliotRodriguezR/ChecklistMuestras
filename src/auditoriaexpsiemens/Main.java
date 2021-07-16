/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoriaexpsiemens;

import java.io.File;

/**
 *
 * @author Rene
 */
public class Main {
    
    int cuentaCompletas = 0;
    int cuentaIncompletas = 0;
    
    public Main(String directorio){
        
        File file = new File(directorio);
        //File file = new File("C:\\Users\\Rene\\Documents\\files\\recovem\\RecordKeeping\\Agosto");
        // auditar en servidor
        //File file = new File("/home/DIGITALIZACION/SIEMENS/1-AEROPUERTO MAYO - AGOSTO/AGOSTO-2");
        //File file = new File("/home/DIGITALIZACION/SIEMENS/SEPTIEMBRE2017-2");
        
        File [] f = file.listFiles();
        
        AuditoriaExpSiemens auditoriaExpSiemens = null;
                //CARPETA	TO	ID	PC	PS	ERR	M	E	A	FC	AWB	BL    MV	HC	E-DOC PDF	COVE PDF	COVE XML	PL
        System.out.println("CARPETA\t\tTO\tID\tPC\tPS\tERR\tM\tE\tA\tFC\tAWB\tBL\tMV\tHC\tEDOC PDF\tCOVE PDF\tCOVE XML\tPL\tNo\tBD\tBD");
        for (File fi: f) {
            if (fi.isDirectory()) {
                auditoriaExpSiemens = new AuditoriaExpSiemens(fi.getAbsolutePath());//fi.getName());
                auditoriaExpSiemens.doAuditar();
                if (auditoriaExpSiemens.isEsCompleta()) {
                    cuentaCompletas++;
                } else {
                    cuentaIncompletas++;
                }
            }
        } // end for
        
        
        System.out.println("Total completas " + cuentaCompletas);
        System.out.println("Total Incompletas " + cuentaIncompletas);
    }
    
    public static void main(String [] args){
        new Main(args[0]);
    }
    
}
