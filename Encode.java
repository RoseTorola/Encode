/*
 * Rosemary Torola
 * Encode.java
 */

 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.File;
 import java.io.IOException;
 import java.io.FileNotFoundException;


public class Encode {

   public static void main(String [] args){

     //check that the proper command line arguments were given
     if(args.length != 2){
       System.err.println("Use: <File to encode> <Text file> ");
       System.exit(0);
     }

     try {
       String encodeFile = args[0];
       String anyText = args[1];
       StringBuilder sb = new StringBuilder("code").append(encodeFile);
       String newFilename = sb.toString();
       String workingDirectory = System.getProperty("user.dir");
       File newFile = new File(workingDirectory, newFilename);

       Encode en1 = new Encode(encodeFile, anyText, newFilename);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }

   public Encode(String encodeFile, String anyText, String newFilename){

     try (
           BufferedReader encode = new BufferedReader(new FileReader(encodeFile));

           BufferedWriter newFile = new BufferedWriter(new FileWriter(newFilename));
     ) {

           BufferedReader textFile = new BufferedReader(new FileReader(anyText));

           int nextChar, codeChar;

           while((nextChar = encode.read()) != -1){//each char in the file
             //get next textFile byte
             if ((codeChar = textFile.read()) == -1){
               //restart textFile BufferedReader if end of file is reached
               textFile.close();
               textFile = new BufferedReader(new FileReader(newFilename));
               codeChar = textFile.read();
             }

             //write the XOR of the two bytes to the new file
             newFile.write(nextChar ^ codeChar);
           }


         } catch (FileNotFoundException fnfe) {
           fnfe.printStackTrace();
         } catch (IllegalArgumentException iae) {
           iae.printStackTrace();
         } catch (IOException ioe) {
           ioe.printStackTrace();
         }
   }






//output file as old filename_x.txt

//take in the file to encode
//take in the encoder files
//use XOR to write bits
//return altered files

//if they input an encoded file, should output a nonencoded file

//watch for excessive object/copy construction, excessive null checks, memory leaks



 }
