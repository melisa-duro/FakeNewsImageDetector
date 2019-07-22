/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import fakenewsimagedetector.Main;
import static fakenewsimagedetector.Main.PathLogs;
import static fakenewsimagedetector.Main.PathProject;
import static fakenewsimagedetector.Main.PathOutput;
import static fakenewsimagedetector.Main.strDate;
import static fakenewsimagedetector.Main.logger;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;


/**
 *
 * @author melisa_duro
 */
public class Utiles {

   
    
    public static String initPaths() {

        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fpath = "";
        
        path = path.replace("%20", " ");
        
        path = path.substring(1);

        String[] arraypath = path.split("/");

        System.out.println(arraypath.length);
        
        for (int i = 0; i < arraypath.length - 1; i++) {

            if (i == 0) {

                fpath = arraypath[i];
                System.out.println(fpath);

            } else if (i == arraypath.length - 3) {
                fpath = fpath + "\\" + arraypath[i] + "\\";
                System.out.println(fpath);
            } else {
                fpath = fpath + "\\" + arraypath[i] + "\\";
                System.out.println(fpath);
            }

        }
        //fpath = "C:\\Users\\melisa_duro\\Documents\\NetBeansProjects\\FakeNewsImageDetector\\FakeNewsDetector\\";
        
        PathProject = fpath;
        PathLogs = PathProject + "Logs\\";
        PathOutput = PathProject + "Output\\";
        
        return fpath;
    }

    public static void initLogger() {

        try {

            SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyy_HHmmss");
            Date now = new Date();
            strDate = sdfDate.format(now);

            FileHandler fh;
            String strLoggerFile = PathLogs + "LogProcess_" + strDate + ".txt";
            fh = new FileHandler(strLoggerFile, true);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);

            //Para no mostrar lo que se incluye en el log por consola
            //logger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (IOException | SecurityException ex) {
            logger.logp(Level.WARNING, "fakenewsimagedetector ->", "initLogger", "Error creating Log Process");
            logger.severe(ex.toString());
        }

    }

}
