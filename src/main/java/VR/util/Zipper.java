package VR.util;

import VR.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.zeroturnaround.zip.ZipUtil;

public class Zipper {

    public Zipper() {

    }

    public void unZipp(String findFile, String outFolder) {
        String file = "";
        String outputFolder = "";
        try {
            file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/') + "/" + findFile;
            outputFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/') + "/" + outFolder;

        } catch (URISyntaxException ex) {
            Logger.getLogger(Zipper.class.getName()).log(Level.SEVERE, null, ex);
        }

        File folder = new File(outputFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        ZipUtil.explode(new File(file));
    }

    public void unZip(String findFile, String outFolder) {

        
        String file = "";
        String outputFolder = "";
        try {
            file = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/') + "/" + findFile;
            outputFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/') + "/" + outFolder;

        } catch (URISyntaxException ex) {
            Logger.getLogger(Zipper.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] buffer = new byte[1024];

        try {

            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
           
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);


                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }

            zis.close();


        } catch (IOException ex) {
            System.out.println("Zip error!");
            Main.login.error();
        }
    }
}
