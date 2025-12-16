import React, { useState } from "react";
import axios from "axios";

const Formulario = () => {
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [identificacion,setIdentificacion] = useState("");
  const [correo, setCorreo] = useState("");
  const [celular, setCelular] = useState("");
  const [qrImage, setQrImage] = useState(""); // Estado para la imagen QR
  const [mensaje, setMensaje] = useState("");
  const [formularioEnviado, setFormularioEnviado] = useState(false); // 🔹 Estado para ocultar el formulario

  const enviarFormulario = async () => {
    try {
      const response = await axios.post(
        "https://d5d8-2800-e2-467f-ec9d-a9f5-d7f7-23a7-96fd.ngrok-free.app/api/v2/crearqr",
        { nombre, apellido, identificacion, correo, celular },
        {
          headers: {
            "Content-Type": "application/json",
          },
          responseType: "arraybuffer", // Importante para recibir imágenes en binario
        }
      );

      // 🔹 Convertir el array de bytes en Base64 para mostrar la imagen
      const base64Image = btoa(
        new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), "")
      );

      // 🔹 Almacenar la imagen en formato Base64 y ocultar el formulario
      setQrImage(`data:image/png;base64,${base64Image}`);
      setMensaje("QR generado correctamente");
      setFormularioEnviado(true); // 🔹 Ocultar el formulario

    } catch (error) {
      console.error("Error en la solicitud:", error);
      setMensaje("Error al enviar el formulario");
    }
  };

  return (
    <div className="container">
      <div className="form-box">
        <h2>Creación QR</h2>

        {/* 🔹 Mostrar los campos solo si el formulario NO ha sido enviado */}
        {!formularioEnviado && (
          <>
            <input type="text" placeholder="Ingrese su nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} />
            <input type="text" placeholder="Ingrese su apellido" value={apellido} onChange={(e) => setApellido(e.target.value)} />
            <input type="text" placeholder="Ingrese su identificacion" value={identificacion} onChange={(e) => setIdentificacion(e.target.value)} />
            <input type="email" placeholder="Correo" value={correo} onChange={(e) => setCorreo(e.target.value)} />
            <input type="tel" placeholder="Celular" value={celular} onChange={(e) => setCelular(e.target.value)} />
            <button onClick={enviarFormulario}>Enviar</button>
          </>
        )}

        {/* Mostrar mensaje */}
        <p className="mensaje">{mensaje}</p>

        {/* 🔹 Mostrar la imagen del QR solo si está disponible */}
        {qrImage && <img src={qrImage} alt="Código QR" style={{ marginTop: "10px", width: "200px" }} />}
      </div>
    </div>
  );
};

export default Formulario;
