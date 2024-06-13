package com.appbraham.mediapp_backend.dto;

import com.appbraham.mediapp_backend.model.Consulta;
import com.appbraham.mediapp_backend.model.Examen;

import java.util.List;

public class ConsultaListaExamenDTO {

    private Consulta consulta;

    private List<Examen> listaExamen;

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<Examen> getListaExamen() {
        return listaExamen;
    }

    public void setListaExamen(List<Examen> listaExamen) {
        this.listaExamen = listaExamen;
    }
}
