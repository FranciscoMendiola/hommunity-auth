package com.syrion.hommunity_api.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DtoFamiliaIn {

    @JsonProperty("apellido")
    @NotNull(message = "El apellido es obligatorio")
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String apellido;

<<<<<<< Updated upstream
    @JsonProperty("estado")
    @NotNull(message = "El estado es obligatorio")
    @NotBlank(message = "El estado no puede estar en blanco")
    private String estado;
=======
    // @JsonProperty("estado")
    // private String estado;
>>>>>>> Stashed changes

    @JsonProperty("fotoIdentificacion")
    @NotNull(message = "La foto de identificación es obligatoria")
    @NotBlank(message = "La foto de identificación no puede estar en blanco")
    private String fotoIdentificacion;

    @JsonProperty("idCasa")
    @NotNull(message = "La casa es obligatoria")
    private Long idCasa;

    @JsonProperty("idUsuarioRegistrador")
<<<<<<< Updated upstream
    private Long idUsuarioRegistrador;   
=======
    @NotNull(message = "El usuario registrador es obligatorio")
    private Long idUsuarioRegistrador;
>>>>>>> Stashed changes
}
