package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
public class Unzipper {
 
	
	
	public void Descomprimir(String ficheroZip, String directorioSalida)
	          throws Exception {
	    final int TAM_BUFFER = 4096;
	    byte[] buffer = new byte[TAM_BUFFER];

	    ZipInputStream flujo = null;
	    try {
	      flujo = new ZipInputStream(new BufferedInputStream(
	              new FileInputStream(ficheroZip)));
	      ZipEntry entrada;
	      while ((entrada = flujo.getNextEntry()) != null) {
	        String nombreSalida = directorioSalida + File.separator
	                + entrada.getName();
	        System.out.println(entrada.isDirectory()+" => "+entrada.getName());
	        //entrada.dire
	        if (entrada.isDirectory() ||entrada.getName().charAt(entrada.getName().length()-1)==92) {
	          File directorio = new File(nombreSalida);
	          directorio.mkdir();
	        } else {
	          BufferedOutputStream salida = null;
	          try {
	            int leido;
	            salida = new BufferedOutputStream(
	                    new FileOutputStream(nombreSalida), TAM_BUFFER);
	            while ((leido = flujo.read(buffer, 0, TAM_BUFFER)) != -1) {
	              salida.write(buffer, 0, leido);
	            }
	          } finally {
	            if (salida != null) {
	              salida.close();
	            }
	          }
	        }
	      }
	    } finally {
	      if (flujo != null) {
	        flujo.close();
	      }

	    }
	  }
	
	/*
	public  void unzip(String directorioZip ) {
		//cadena que contiene la ruta donde están los archivos .zip
		//String directorioZip = "C:\\UNZIP\\";
		//ruta donde están los archivos .zip
		File carpetaExtraer = new File(directorioZip);
		
		//valida si existe el directorio
		if (carpetaExtraer.exists()) {
			//lista los archivos que hay dentro  del directorio
			//File[] ficheros = carpetaExtraer.listFiles();
			//System.out.println("Número de ficheros encontrados: " + ficheros.length);
			
			//ciclo para recorrer todos los archivos .zip
			//for (int i = 0; i < ficheros.length; i++) {
				//System.out.println("Nombre del fichero: " + ficheros[i].getName());
				//System.out.println("Descomprimiendo.....");
				try {
					//crea un buffer temporal para el archivo que se va descomprimir
					System.out.println(carpetaExtraer.getPath()+",,");
					ZipInputStream zis = new ZipInputStream(new FileInputStream(carpetaExtraer.getPath()));
					
					System.out.println(zis.getNextEntry()+" next Entrry");
					ZipEntry salida;
					//recorre todo el buffer extrayendo uno a uno cada archivo.zip y creándolos de nuevo en su archivo original 
					while (null != (salida = zis.getNextEntry())) {
						//System.out.println("Nombre del Archivo: "+salida.getName());	
							FileOutputStream fos = new FileOutputStream(carpetaExtraer.getParentFile().getPath() +"\\"+ carpetaExtraer.getName()+"1");
							int leer;
							byte[] buffer = new byte[1024];
							while (0 < (leer = zis.read(buffer))) {
								fos.write(buffer, 0, leer);
							}
							fos.close();
							zis.closeEntry();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			//}
			System.out.println("Directorio de salida: " + directorioZip);
		} else {
			System.out.println("No se encontró el directorio..");
		}
	}
	*/
}