package com.appbraham.mediapp_backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(description = "Información del Paciente") //Esto es para Swagger
@Entity //(name = "EntidadPaciente")
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @Schema(description = "Nombre debe de tener mínimo 3 caracteres") //Esto es para Swagger
    @Size(min = 3, message = "Nombres debe de tener un mínimo de 3 caracteres")
    @Column(name = "nombres", nullable = false, length = 70)
    private String nombres;

    @Size(min = 3, message = "Apellidos debe de tener un mínimo de 3 caracteres")
    @Column(name = "apellidos", nullable = false, length = 70)
    private String apellidos;

    @Size(min = 8, message = "DNI de tener un mínimo de 8 caracteres")
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;

    @Size(min = 3, max = 150, message = "Dirección debe de tener un mínimo de 3 y un máximo de 150 caracteres")
    @Column(name = "direccion", nullable = true, length = 150)
    private String direccion;

    @Size(min = 9, max = 9, message = "Teléfono debe de tener 9 caracteres")
    @Column(name = "telefono", nullable = true, length = 9)
    private String telefono;

    @Email
    @Column(name = "email", nullable = true, length = 120, unique = true)
    private String email;

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
