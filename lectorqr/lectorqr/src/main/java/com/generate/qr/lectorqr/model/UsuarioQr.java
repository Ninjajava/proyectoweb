package com.generate.qr.lectorqr.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioQr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String nombre;
    private String apellido;
    @JsonProperty("identificacion")
    private String numeroIdentificacion;
    private String correo;
    private int celular;
    private String qrPath;



    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public void setQrPath(String qrPath) {
        this.qrPath = qrPath;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public int getCelular() {
        return celular;
    }

    public String getQrPath() {
        return qrPath;
    }
}
