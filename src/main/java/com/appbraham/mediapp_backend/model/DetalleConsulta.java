package com.appbraham.mediapp_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_consulta")
public class DetalleConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;

    //@Transient => Hace que el atributo no representa nada en base de datos solo en una entidad de Java
    @JsonIgnore //Evita que se considere o que sea obligatorio el atributo en el JSON
    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_detalle"))
    private Consulta consulta;

    @Column(name = "diagnostico", nullable = false, length = 70)
    private String diagnostico;

    @Column(name = "tratamiento", nullable = false, length = 300)
    private String tratamiento;

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
