package com.appbraham.mediapp_backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

//Se usa Embeddable para que pueda ser usada en ConsultaExamen
@Embeddable
public class ConsultaExamenPK implements Serializable {


    @ManyToOne
    @JoinColumn(name = "id_examen", nullable = false)
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaExamenPK that = (ConsultaExamenPK) o;
        return Objects.equals(examen, that.examen) && Objects.equals(consulta, that.consulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examen, consulta);
    }
}
