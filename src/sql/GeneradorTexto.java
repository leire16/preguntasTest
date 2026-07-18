package sql;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeneradorTexto {

    public static void main(String[] args) {

        try {

            // PDF de entrada
            File pdf = new File("docs/200_Galdera-sorta_TEMARIO_COMUN_cas_1_.pdf");

            if (!pdf.exists()) {
                System.out.println("No se encuentra el PDF.");
                return;
            }

            // Abrir PDF
            PDDocument documento = Loader.loadPDF(pdf);

            // Extraer todo el texto
            PDFTextStripper stripper = new PDFTextStripper();
            String texto = stripper.getText(documento);

            // Cerrar PDF
            documento.close();

            // Guardar el texto en un archivo
            Path salida = Path.of("docs/texto_extraido_tema21_raw.txt");
            Files.writeString(salida, texto);

            System.out.println("--------------------------------");
            System.out.println("Texto extraído correctamente.");
            System.out.println("Archivo generado:");
            System.out.println(salida.toAbsolutePath());
            System.out.println("--------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}