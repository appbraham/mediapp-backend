package com.appbraham.mediapp_backend.service;

import com.appbraham.mediapp_backend.dto.ConsultaListaExamenDTO;
import com.appbraham.mediapp_backend.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta, Integer>{

    Consulta registrarTransaccional(ConsultaListaExamenDTO dto);

}
