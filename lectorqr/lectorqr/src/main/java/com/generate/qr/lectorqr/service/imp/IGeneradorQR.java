package com.generate.qr.lectorqr.service.imp;


import com.generate.qr.lectorqr.model.UsuarioQr;

public interface IGeneradorQR {

    public byte[] crearQr(com.generate.qr.lectorqr.model.UsuarioQr usuarioQr);
}
