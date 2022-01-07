package com.gussoft.textract;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;

public class zip {
    public static void main(String[] args) throws ZipException {
        String pathZipInput = "C:\\prueba"; // carpeta a comprimir
        String pathOutputZip = "C:\\prueba.zip"; // ruta y nombre del zip que se genera
        //paso1(pathZipInput, pathOutputZip);
        //Boolean f = addFoldInZip(pathZipInput,pathZipInput,pathOutputZip,"123");
        example2();
    }

    public static boolean addFoldInZip(String inPath,String storagePath,String outPath,String password) {
        try {
            ZipFile zipFile = new ZipFile(outPath);
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parameters.setRootFolderInZip(storagePath);  ;
            if(password!=null&&!password.equals("")){
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                parameters.setPassword(password);
            }
            zipFile.addFolder(inPath, parameters);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void paso1(String pathZipInput, String pathOutputZip){
        try {
            ZipFile zipFile = new ZipFile(pathOutputZip);
            ArrayList<File> filesToAdd = new ArrayList<File>();
            filesToAdd.add(new File(pathZipInput));


            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            parameters.setRootFolderInZip("target/");

            zipFile.addFiles(filesToAdd, parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }


    public static void example2(){
        try {
          ZipFile zipFile = new ZipFile("src/main/resources/Ejemplo.zip");
          ArrayList<File> filesToAdd = new ArrayList<File>();
          filesToAdd.add(new File("src/main/resources/demo4.pdf"));
          filesToAdd.add(new File("src/main/resources/efile.pdf"));
          filesToAdd.add(new File("src/main/resources/demoS.pdf"));

          ZipParameters parameters = new ZipParameters();
          parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

          parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
          parameters.setRootFolderInZip("target/");

          zipFile.addFiles(filesToAdd, parameters);
      } catch (ZipException e) {
          e.printStackTrace();
      }
    }
}
