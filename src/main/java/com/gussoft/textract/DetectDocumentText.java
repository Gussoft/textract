// snippet-sourcedescription:[DetectDocumentText.java demonstrates how to detect text in the input document.]
// snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Textract]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/06/2020]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/


package com.gussoft.textract;

// snippet-start:[textract.java2._detect_doc_text.import]

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
// snippet-end:[textract.java2._detect_doc_text.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 * <p>
 * For information, see this documentation topic:
 * <p>
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class DetectDocumentText {
    static final String ACCESS_KEY = "AKIAJVSRPDO66O2DRMFQ";
    static final String ACCESS_SECRET = "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";

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DetectDocumentText <sourceDoc> \n\n" +
                "Where:\n" +
                "    sourceDoc - the path where the document is located (must be an image, for example, C:/AWS/book.png). \n";

        Region region = Region.US_EAST_2;
        TextractClient textractClient = TextractClient.builder()
                .region(region)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
        String guss = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\DNI-Guss.jpeg";
        String luis = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\DNI_luis.jpg";
        String juan = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\juan.jpg";//vx
        String emilio = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\emilio.jpeg";//vv
        String david = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\david.jpeg";//vv
        String javier = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\javier.jpeg";//vv
        String cristian = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\cristian.jpeg";//vx
        String ricardo = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\ricardo.jpeg";
        String dante = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\dante.jpeg";//vv
        String luism = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\luism.jpeg";//vv
        String jorge = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\jorge.jpeg";//v
        String prisila = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\pri.jpeg";//xxx
        String valiente = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\valiente.jpeg";//xx
        String alejandra = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\alejandra.jpeg";//x2
        String martin = "C:\\Guss\\BIGPRIME\\ejemplos\\textract\\src\\main\\resources\\martin.jpeg";//v
        String texto = detectDocText(textractClient, alejandra);
        textractClient.close();
        ObtenerDatos(texto);// true si es azul
    }

    private static void ObtenerDatos(String texto) {
        boolean tipo = true;
        String elec = "";
        String dni = "No Found";
        String fechaCad = "No Found";
        int find = 0;
        if (texto != null) {
            if (texto.contains("REPÚBLICA") || texto.contains("PERÚ")) {
                tipo = false;
                elec = "Electronico";
            } else if (texto.contains("REPUBLICA") || texto.contains("PERU")) {
                tipo = true;
            }
        }
        if (tipo) {
            if (texto.contains("IDENTIDAD")) {
                find = texto.indexOf("IDENTIDAD");
                dni = texto.substring(find + 13, find + 22);
                dni = dni.trim();
                String[] d = dni.split(",");
                if (!stringEmpty(d[0])) {
                    dni = d[0];
                } else {
                    dni = d[1];
                }
            } else if (dni.equals("No Found")) {
                if (texto.contains("DNI")) {
                    find = texto.indexOf("DNI");
                    dni = texto.substring(find + 4, find + 12);
                }
            }
        } else {
            String[] partes = texto.split(",");
            dni = partes[6].split("-")[0];
//            boolean isNumeric2 = false;//dni.matches("[+-]?\\d*(\\.\\d+)?");
            if (!isNumeric(dni)) {
                //if (!isNumeric2){
                dni = partes[5].split("-")[0];
                if (dni.equalsIgnoreCase("Primer Apellido")) dni = partes[4].split("-")[0];
            }
        }
        if (texto.contains("Caducidad")) {
            find = texto.indexOf("Caducidad");
            fechaCad = texto.substring(find + 21, find + 32);
            if (tipo) {
                if (texto.contains("Caducidad,Pre")) {
                    String[] div = fechaCad.split(",");
                    if (!stringEmpty(div[0])) {
                        fechaCad = div[0];
                    } else {
                        fechaCad = div[1];
                    }
                }else{
                    find = texto.indexOf("Nacimiento")-11;
                    fechaCad = texto.substring(find,find+11);
                    String[] div = fechaCad.split(",");
                    if (!stringEmpty(div[0])) {
                        fechaCad = div[0];
                    }else {
                        fechaCad = div[1];
                    }
                }

            } else{
                String[] div = fechaCad.split(",");
                fechaCad = div[0];
            }
        }

        fechaCad = fechaCad.replaceAll("\\.", " ");
        System.out.println("dni " + elec + " = " + dni);
        System.out.println("fechaCad = " + fechaCad);
        System.out.println("texto = " + texto);
    }
    
    // snippet-start:[textract.java2._detect_doc_text.main]
    public static String detectDocText(TextractClient textractClient, String sourceDoc) {
        StringBuilder texto = new StringBuilder();
        try {

            InputStream sourceStream = new FileInputStream(new File(sourceDoc));
            SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

            // Get the input Document object as bytes
            Document myDoc = Document.builder()
                    .bytes(sourceBytes)
                    .build();

            DetectDocumentTextRequest detectDocumentTextRequest = DetectDocumentTextRequest.builder()
                    .document(myDoc)
                    .build();

            // Invoke the Detect operation
            DetectDocumentTextResponse textResponse = textractClient.detectDocumentText(detectDocumentTextRequest);

            List<Block> docInfo = textResponse.blocks();

            Iterator<Block> blockIterator = docInfo.iterator();

            while (blockIterator.hasNext()) {
                Block block = blockIterator.next();
                if (block.blockType() == BlockType.LINE) {
                    //System.out.println(block.text() + ",");
                    texto.append(block.text()).append(',');
                }
//                System.out.println("The block type is " +block.blockType().toString());

            }

            DocumentMetadata documentMetadata = textResponse.documentMetadata();
            System.out.println("The number of pages in the document is " + documentMetadata.pages());

        } catch (TextractException | FileNotFoundException e) {

            System.err.println(e.getMessage());
            System.exit(1);
        }
        // snippet-end:[textract.java2._detect_doc_text.main]
        return texto.toString();
    }

    public static boolean stringEmpty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNumeric(String cadena) {
        boolean result;
        try {
            Integer.parseInt(cadena);
            result = true;
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}


/*
        if (texto.contains("Nacimiento")){//Caducidad
            find = texto.indexOf("Nacimiento")-11;
            //fechaCad = texto.substring(find+21,find+32);
            fechaCad = texto.substring(find,find+11);
            if (tipo){
                String[] div = fechaCad.split(",");
                if (!stringEmpty(div[0])) {
                    fechaCad = div[0];
                }else {
                    fechaCad = div[1];
                }
            }else{
                find = texto.indexOf("Caducidad");
                fechaCad = texto.substring(find+21,find+32);
                String[] div = fechaCad.split(",");
                fechaCad = div[0];
            }
        }*/