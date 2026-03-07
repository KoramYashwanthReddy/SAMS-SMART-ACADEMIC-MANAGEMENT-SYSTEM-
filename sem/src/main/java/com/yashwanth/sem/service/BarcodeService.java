package com.yashwanth.sem.service;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class BarcodeService {

    public byte[] generateBarcode(String code) throws Exception {

        Code128Bean barcode = new Code128Bean();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(
                        out,
                        "image/png",
                        300,
                        BufferedImage.TYPE_BYTE_BINARY,
                        false,
                        0
                );

        barcode.generateBarcode(canvas, code);

        canvas.finish();

        return out.toByteArray();
    }
}