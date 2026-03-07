package com.yashwanth.sem.service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.yashwanth.sem.entity.User;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class IdCardPdfService {

    private final QRCodeService qrService;
    private final BarcodeService barcodeService;

    // Design Colors
    private static final Color COLOR_RED = new DeviceRgb(210, 35, 42);
    private static final Color COLOR_DARK = new DeviceRgb(33, 33, 33);
    private static final Color COLOR_GREY = new DeviceRgb(120, 120, 120);

    public IdCardPdfService(QRCodeService qrService, BarcodeService barcodeService) {
        this.qrService = qrService;
        this.barcodeService = barcodeService;
    }

    public byte[] generateIdCard(User user) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);

        // Standard ID Card size
        PageSize cardSize = new PageSize(242, 385);
        Document doc = new Document(pdf, cardSize);
        doc.setMargins(0, 0, 0, 0);

        drawFrontSide(doc, pdf, user);
        drawBackSide(doc, pdf, user);

        doc.close();
        return out.toByteArray();
    }

    private void drawFrontSide(Document doc, PdfDocument pdf, User user) throws Exception {
        PdfPage page = pdf.addNewPage();
        PdfCanvas canvas = new PdfCanvas(page);
        float w = page.getPageSize().getWidth();
        float h = page.getPageSize().getHeight();

        // 1. Header Background (Red Base + Dark Trapezoid)
        canvas.saveState()
                .setFillColor(COLOR_RED)
                .rectangle(0, h - 90, w, 90).fill()
                // Dark trapezoid center piece
                .setFillColor(COLOR_DARK)
                .moveTo(w * 0.20, h)
                .lineTo(w * 0.80, h)
                .lineTo(w * 0.85, h - 110)
                .lineTo(w * 0.15, h - 110)
                .closePath().fill()
                .restoreState();

        // 2. Business/College Title
        doc.showTextAligned(new Paragraph("SMART EXAM SYSTEM")
                .setFontColor(ColorConstants.WHITE).setBold().setFontSize(14).setMargin(0),
                w / 2, h - 35, TextAlignment.CENTER);
        
        doc.showTextAligned(new Paragraph("ACADEMIC IDENTITY")
                .setFontColor(ColorConstants.WHITE).setFontSize(6).setCharacterSpacing(1.5f),
                w / 2, h - 48, TextAlignment.CENTER);

        // 3. Circular Profile Photo
        if (user.getProfilePhoto() != null) {
            float radius = 45f;
            float centerX = w / 2;
            float centerY = h - 140;

            canvas.saveState().circle(centerX, centerY, radius).clip().endPath();
            Image img = new Image(ImageDataFactory.create(Files.readAllBytes(Paths.get(user.getProfilePhoto()))));
            img.scaleToFit(radius * 2, radius * 2);
            img.setFixedPosition(centerX - radius, centerY - radius);
            doc.add(img);
            canvas.restoreState();

            // White border around photo
            canvas.saveState().setStrokeColor(ColorConstants.WHITE).setLineWidth(3)
                    .circle(centerX, centerY, radius).stroke().restoreState();
        }

        // 4. User Personal Details
        String fullName = (user.getFirstName() + " " + user.getLastName()).toUpperCase();
        doc.showTextAligned(new Paragraph(fullName).setBold().setFontSize(14),
                w / 2, h - 210, TextAlignment.CENTER);
        
        doc.showTextAligned(new Paragraph(user.getRole() != null ? user.getRole().name() : "USER")
                .setFontSize(9).setFontColor(COLOR_GREY),
                w / 2, h - 225, TextAlignment.CENTER);

        // Details Block
        float detailY = h - 250;
        float spacing = 15f;
        String[][] details = {
            {"System ID", user.getSystemUserId()},
            {"College ID", user.getCollegeUserId()},
            {"Dept", user.getDepartment()}
        };

        for (String[] detail : details) {
            String val = detail[1] != null ? detail[1] : "N/A";
            doc.showTextAligned(new Paragraph(detail[0] + " : " + val).setFontSize(9),
                    w / 2, detailY, TextAlignment.CENTER);
            detailY -= spacing;
        }

        // 5. Footer (Dark Curve)
        canvas.saveState()
                .setFillColor(COLOR_DARK)
                .moveTo(0, 0).lineTo(w, 0).lineTo(w, 50)
                .curveTo(w * 0.5, 75, 0, 50) // Curve effect
                .closePath().fill().restoreState();

        // 6. Barcode (placed on footer)
        byte[] barcode = barcodeService.generateBarcode(user.getSystemUserId() != null ? user.getSystemUserId() : "000000");
        Image barImg = new Image(ImageDataFactory.create(barcode)).scaleToFit(130, 35);
        barImg.setFixedPosition((w - 130) / 2, 12);
        doc.add(barImg);
    }

    private void drawBackSide(Document doc, PdfDocument pdf, User user) throws Exception {
        PdfPage page = pdf.addNewPage();
        float w = page.getPageSize().getWidth();
        float h = page.getPageSize().getHeight();

        doc.showTextAligned(new Paragraph("TERMS & CONDITIONS")
                .setBold().setFontSize(12).setMarginTop(30),
                w / 2, h - 40, TextAlignment.CENTER);

        doc.showTextAligned(new Paragraph("1. This card is non-transferable.\n2. Access restricted to campus hours.\n3. Return if found to college office.")
                .setFontSize(8).setMultipliedLeading(1.5f),
                w / 2, h - 100, TextAlignment.CENTER);

        // QR Code on back
        byte[] qrBytes = qrService.generateQRCode(user.getSystemUserId() != null ? user.getSystemUserId() : "000000");
        Image qrImg = new Image(ImageDataFactory.create(qrBytes)).scaleToFit(80, 80);
        qrImg.setFixedPosition((w - 80) / 2, 120);
        doc.add(qrImg);

        doc.showTextAligned(new Paragraph("Authorized Signature").setFontSize(8).setItalic(),
                w / 2, 40, TextAlignment.CENTER);
    }
}