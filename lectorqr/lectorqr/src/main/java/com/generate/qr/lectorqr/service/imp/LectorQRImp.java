package com.generate.qr.lectorqr.service.imp;


import com.generate.qr.lectorqr.model.UsuarioQr;
import com.generate.qr.lectorqr.repository.UsuarioRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class LectorQRImp implements ILectorQR {

    private final UsuarioRepository usuarioRepository;

    private final EmailService email;

    public LectorQRImp(UsuarioRepository usuarioRepository, EmailService email) {
        this.usuarioRepository = usuarioRepository;
        this.email = email;
    }

    @Override
    public String lectorQR(String identificacion) throws IOException {
        UsuarioQr qr = usuarioRepository.buscarPorIdentificaicon(identificacion);

        InputStream inputStream = new ClassPathResource("templates/template-correo.html").getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);


       email.sendEmail(qr.getCorreo(),htmlContent);
        return htmlContent;
    }
}
