package tickets.aviotickets.service;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;
import tickets.aviotickets.entity.Ticket;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class TicketsPDFService {
    public byte[] createPdf(Ticket ticket) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);

        PageSize pageSize = new PageSize(750, 230);

        Document document = new Document(pdf, pageSize);
        document.setMargins(15, 15, 15, 15);

        DeviceRgb themeColor = new DeviceRgb(143, 31, 41);

        float[] mainColumns = {2.5f, 0.1f, 1.2f};
        Table mainLayout = new Table(UnitValue.createPointArray(mainColumns)).useAllAvailableWidth();

        Cell leftCell = new Cell().setBorder(Border.NO_BORDER);

        Table leftHeader = new Table(1).useAllAvailableWidth();
        leftHeader.addCell(new Cell().add(new Paragraph("BOARDING PASS")
                        .setFontColor(ColorConstants.WHITE).setBold().setMarginLeft(15))
                .setBackgroundColor(themeColor).setBorder(Border.NO_BORDER).setPadding(8));
        leftCell.add(leftHeader);

        Table leftInfo = new Table(new float[]{1, 1, 1}).useAllAvailableWidth().setMarginTop(10);

        leftInfo.addCell(createCell("NAME", ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getLastName(), 2));
        leftInfo.addCell(createCell("FROM", ticket.getFlight().getFromCity(), 1));

        leftInfo.addCell(createCell("FLIGHT", ticket.getFlight().getFlightNumber(), 1));
        leftInfo.addCell(createCell("DATE", ticket.getFlight().getDepartureTime()
                .format(DateTimeFormatter.ofPattern("MM/dd/yyyy/hh-mm-ss")), 1));
        leftInfo.addCell(createCell("TO", ticket.getFlight().getToCity(), 1));

        leftInfo.addCell(createCell("GATE", ticket.getFlight().getGate(), 1));
        leftInfo.addCell(createCell("SEAT", ticket.getSeatNumber(), 1));

        Barcode128 barcode = new Barcode128(pdf);
        barcode.setCode("A-FFD123456789001");

        barcode.setBaseline(-1);
        Image barcodeImg = new Image(barcode.createFormXObject(pdf))
                .scaleToFit(100, 40)
                .setHorizontalAlignment(HorizontalAlignment.LEFT)
                .setMarginTop(5);

        Cell barcodeCell = new Cell().add(barcodeImg).setBorder(Border.NO_BORDER);
        leftInfo.addCell(barcodeCell);

        leftCell.add(leftInfo);
        mainLayout.addCell(leftCell);

        mainLayout.addCell(new Cell().setBorder(Border.NO_BORDER)
                .setBorderLeft(new com.itextpdf.layout.borders.DashedBorder(ColorConstants.GRAY, 1)));

        Cell rightCell = new Cell().setBorder(Border.NO_BORDER).setPaddingLeft(10);

        Table rightHeader = new Table(1).useAllAvailableWidth();
        rightHeader.addCell(new Cell().add(new Paragraph("AIR TICKET")
                        .setFontColor(ColorConstants.WHITE).setBold())
                .setBackgroundColor(themeColor).setBorder(Border.NO_BORDER).setPadding(8).setTextAlignment(TextAlignment.CENTER));
        rightCell.add(rightHeader);

        Table rightGrid = new Table(new float[]{1, 1}).useAllAvailableWidth().setMarginTop(10);
        rightGrid.addCell(createCell("NAME", ticket.getPassenger().getLastName(), 2));
        rightGrid.addCell(createCell("FLIGHT", ticket.getFlight().getFlightNumber(), 1));
        rightGrid.addCell(createCell("DATE", ticket.getFlight().getDepartureTime()
                .format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), 1));
        rightGrid.addCell(createCell("FROM", ticket.getFlight().getFromCity(), 1));
        rightGrid.addCell(createCell("TO", ticket.getFlight().getToCity(), 1));
        rightGrid.addCell(createCell("SEAT", ticket.getSeatNumber(), 1));
        rightGrid.addCell(createCell("CLASS", ticket.getClassName().name() +" class", 1));

        rightCell.add(rightGrid);
        mainLayout.addCell(rightCell);

        document.add(mainLayout);
        document.close();

        return out.toByteArray();
    }

    private Cell createCell(String label, String value, int colspan) {
        Paragraph p = new Paragraph()
                .add(new Text(label + "\n").setFontSize(7).setFontColor(ColorConstants.GRAY))
                .add(new Text(value).setFontSize(10).setBold());
        return new Cell(1, colspan).add(p).setBorder(Border.NO_BORDER).setPaddingBottom(5);
    }
}