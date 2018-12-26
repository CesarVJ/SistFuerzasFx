package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
	
	
	static String folderName="";
    File zip;
    ZipOutputStream output;

    public Zipper(File zip) throws FileNotFoundException{
        this.output = new ZipOutputStream(new FileOutputStream(zip));
    }

    private boolean zipFile(File file){
        try {
            byte[] buf = new byte[1024];
            if(file.getParentFile().getName().equals(folderName)) {
            	
                output.putNextEntry(new ZipEntry(file.getName()));
            }else {
                output.putNextEntry(new ZipEntry(file.getParentFile().getName()+"\\"+file.getName()));
            }
            FileInputStream fis = new FileInputStream(file);
              int len=0;
              while ((len = fis.read(buf)) !=-1) {
                output.write(buf, 0, len);
              }
              output.closeEntry();
              fis.close();
              return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private boolean zipDir(File file) {
    	if(file.getName().equals(folderName))return true;
        try {
            output.putNextEntry(new ZipEntry(file.getName()+"\\"));
            output.closeEntry();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private boolean add(File... files){
        for (File file : files){
            if (file.isDirectory()){
                zipDir(file);
                add(file.listFiles());
            } else {
                zipFile(file);
            }
        }
        return true;
    }

    public void zip(File files) throws IOException{
    	folderName=files.getName();
        add(files);
        output.finish();
        output.close();
    }

 
}