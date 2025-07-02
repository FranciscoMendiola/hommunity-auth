package com.syrion.hommunity_api.api.dto.out;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoFamiliaOut {

    private Long idFamilia;
    private String apellido;
    private String estado;
    private String fotoIdentificacion;
    private Timestamp fechaRegistro;
    private Long idCasa;
    private Long idUsuarioRegistrador;

}
