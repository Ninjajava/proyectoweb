package com.generate.qr.lectorqr.controller;

import com.generate.qr.lectorqr.model.UsuarioQr;
import com.generate.qr.lectorqr.service.imp.ILectorQR;
import com.generate.qr.lectorqr.service.imp.EmailService;
import com.generate.qr.lectorqr.service.imp.IGeneradorQR;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v2")
public class ControllerQR {

    private final EmailService serviceEmail;

    private final ILectorQR scanQR;

    private final IGeneradorQR serviceQR;


    public ControllerQR(EmailService serviceEmail, ILectorQR scanQR, IGeneradorQR serviceQR) {
        this.serviceEmail = serviceEmail;
        this.scanQR = scanQR;
        this.serviceQR = serviceQR;
    }


    @PostMapping("/crearqr")
    public ResponseEntity<?> getQRCode(@RequestBody UsuarioQr usuarioQr) {
        byte[] qrImage = serviceQR.crearQr(usuarioQr);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qr.png\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }

    @GetMapping("/scanqr")
    public  ResponseEntity<?>scanQRCode(@RequestParam String identificacion) throws IOException {
        String dto = scanQR.lectorQR(identificacion);
      return ResponseEntity.ok().body(dto);
    }


}

