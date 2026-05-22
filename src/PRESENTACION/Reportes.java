package PRESENTACION;

import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
// IMPORT PARA EJECUTAR SOLO EN EL MMISO REPORTES.JAVA (OJO) 
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
public class Reportes {

    // --- CLASE PARA CABECERA Y PIE DE PÁGINA (Se repite en todas las hojas) ---
    class HeaderFooter extends PdfPageEventHelper {
        private Image imgLogo;
        private Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        private Font fontFooter = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);

        public HeaderFooter() {
            try {
                // Cargamos la imagen desde tu carpeta src/IMAGENES
                imgLogo = Image.getInstance("src/IMAGENES/logo.png");
                imgLogo.scaleToFit(70, 70); // Tamaño ajustado para las esquinas
            } catch (Exception e) {
                System.out.println("Error: No se encontró el logo en src/IMAGENES/logo.png");
            }
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            try {
                // 1. TABLA DE CABECERA (3 columnas: Logo Izq | Título Centro | Logo Der)
                PdfPTable header = new PdfPTable(3);
                header.setTotalWidth(527); // Ancho total de la página A4 menos márgenes
                header.setLockedWidth(true);
                header.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                header.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

                if (imgLogo != null) {
                    // Logo Izquierdo
                    PdfPCell cellLogoIzq = new PdfPCell(imgLogo);
                    cellLogoIzq.setBorder(Rectangle.NO_BORDER);
                    cellLogoIzq.setHorizontalAlignment(Element.ALIGN_LEFT);
                    header.addCell(cellLogoIzq);

                    // Título Central
                    PdfPCell cellTitulo = new PdfPCell(new Phrase("REPORTES FERREYROS S.A.C", fontTitulo));
                    cellTitulo.setBorder(Rectangle.NO_BORDER);
                    cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTitulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    header.addCell(cellTitulo);

                    // Logo Derecho
                    PdfPCell cellLogoDer = new PdfPCell(imgLogo);
                    cellLogoDer.setBorder(Rectangle.NO_BORDER);
                    cellLogoDer.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    header.addCell(cellLogoDer);
                }

                // Escribir la cabecera en la parte superior (Y = 820 aprox)
                header.writeSelectedRows(0, -1, document.leftMargin(), 820, cb);

                // 2. PIE DE PÁGINA
                Phrase footerText = new Phrase("Reporte Generado por FERREYROS S.A.C - Página " + writer.getPageNumber(), fontFooter);
                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footerText, 
                        (document.right() - document.left()) / 2 + document.leftMargin(), 
                        document.bottom() - 10, 0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // --- MÉTODO PRINCIPAL PARA GENERAR EL REPORTE ---
    public void GenerarReporteMaestro() {
        Connection cn = null;
        // Definimos márgenes: Izq: 36, Der: 36, Top: 130 (para que no choque con logo), Bot: 36
        Document doc = new Document(PageSize.A4, 36, 36, 130, 36);

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Reporte_Final_Ferreyros.pdf"));
            
            // ACTIVAR CABECERA Y PIE
            writer.setPageEvent(new HeaderFooter());

            doc.open();

            // CONEXIÓN A ORACLE
            Class.forName("oracle.jdbc.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.27:1521/Xe",
                    "system",
                    "aaron25"
            );

            // TABLA 1: CLIENTES
            agregarSeccionTabla(doc, cn, "REPORTE DE CLIENTES", "SELECT * FROM clientes");

            // Espacio entre tablas
            doc.add(new Paragraph("\n")); 
            doc.newPage();

            // TABLA 2: FINANCIAMIENTO
            agregarSeccionTabla(doc, cn, "REPORTE DE FINANCIAMIENTO", "SELECT * FROM financiamiento");
            
            // ESPACIO ENTRE TABLAS
            doc.add(new Paragraph("\n"));
            doc.newPage();
            //--------------------//
            // TABLA 3: EMPLEADOS
            agregarSeccionTabla(doc, cn, "REPORTE DE EMPLEADOS", "SELECT * FROM Empleados");

            // Espacio entre tablas
            doc.add(new Paragraph("\n")); 
            doc.newPage();

            // TABLA 4: PEDIDO
            agregarSeccionTabla(doc, cn, "REPORTE DE PEDIDO", "SELECT * FROM pedido");
            
            // ESPACIO ENTRE TABLAS
            // CERRAMOS EL DOCUMENTO UNA SOLA VEZ AL FINAL
            doc.close();
            doc.newPage();
            JOptionPane.showMessageDialog(null, "¡PDF Generado con éxito! Revisa la carpeta del proyecto.");

        } catch (Exception e) {
            if (doc.isOpen()) doc.close();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error General: " + e.getMessage());
        } finally {
            try { if (cn != null) cn.close(); } catch (SQLException e) { }
        }
    }

    // --- MÉTODO AUXILIAR PARA DIBUJAR LAS TABLAS ---
    private void agregarSeccionTabla(Document doc, Connection cn, String titulo, String sql) throws Exception {
        // Título de la sección en azul como en tu ejemplo
        Font fontSeccion = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
        Paragraph p = new Paragraph(titulo, fontSeccion);
        p.setSpacingAfter(10);
        doc.add(p);

        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        PdfPTable tabla = new PdfPTable(cols);
        tabla.setWidthPercentage(100);

        // Cabeceras de la tabla (Gris)
        for (int i = 1; i <= cols; i++) {
            PdfPCell header = new PdfPCell(new Phrase(rsmd.getColumnName(i), new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(header);
        }

        // Datos de la base de datos
        while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
                String dato = rs.getString(i);
                PdfPCell celda = new PdfPCell(new Phrase(dato != null ? dato : "", new Font(Font.FontFamily.HELVETICA, 9)));
                tabla.addCell(celda);
            }
        }

        doc.add(tabla);
        rs.close();
        ps.close();
        // NOTA: ¡Aquí NO se pone doc.close()! Se cierra arriba en el método principal.
    }
    // --- MÉTODO MAIN PARA EJECUTAR SOLO ESTA CLASE ---
    public static void main(String[] args) {
        try {
            // Aplicamos el tema profesional
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 15);
            
            // Creamos el reporte y lo ejecutamos
            Reportes app = new Reportes();
            app.GenerarReporteMaestro();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
}    
}