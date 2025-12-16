package com.generate.qr.lectorqr.service.imp;


import com.generate.qr.lectorqr.model.UsuarioQr;
import com.generate.qr.lectorqr.repository.UsuarioRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service

public class GeneradoQR implements IGeneradorQR {


    private   UsuarioRepository usuarioRepository;

    private  EmailService email;

    public GeneradoQR(UsuarioRepository usuarioRepository, EmailService email) {
        this.usuarioRepository = usuarioRepository;
        this.email = email;
    }

    private static final String QR_DIRECTORY = "qr_codes/";


    @Override
    public byte[] crearQr(com.generate.qr.lectorqr.model.UsuarioQr usuarioQr) {
        int width = 300;
        int height = 300;
        String qrText = "https://d5d8-2800-e2-467f-ec9d-a9f5-d7f7-23a7-96fd.ngrok-free.app/api/v2/scanqr?identificacion=" + usuarioQr.getNumeroIdentificacion();
        System.out.println("url para lectura de este qr creado " +  qrText);
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, width, height);

            // Crear directorio si no existe
            File directorio = new File(QR_DIRECTORY);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Definir la ruta donde se guardará el QR
            String qrFileName = QR_DIRECTORY + "QR_" + usuarioQr.getIdUsuario() + ".png";
            Path path = Paths.get(qrFileName);

            // Guardar el QR como archivo
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            // Guardar la ruta en la base de datos
            usuarioQr.setQrPath(qrFileName);
            usuarioRepository.save(usuarioQr);


            // Convertir el QR en un array de bytes para enviarlo como respuesta
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();

        } catch (WriterException | IOException e) {
            throw new RuntimeException("Se generó un error al crear el QR: " + e.getMessage());
        }
    }


}
