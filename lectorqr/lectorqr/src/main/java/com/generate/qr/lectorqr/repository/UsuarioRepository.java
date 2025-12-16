package com.generate.qr.lectorqr.repository;

import com.generate.qr.lectorqr.model.UsuarioQr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioQr,Long > {

    @Query("select u from UsuarioQr u where u.numeroIdentificacion = :identificacion")
    UsuarioQr buscarPorIdentificaicon(@Param("identificacion") String identificacion);
}
