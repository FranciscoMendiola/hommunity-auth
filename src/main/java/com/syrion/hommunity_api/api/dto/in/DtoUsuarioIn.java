package com.syrion.hommunity_api.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syrion.hommunity_api.api.entity.Familia;
import com.syrion.hommunity_api.api.entity.Zona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DtoUsuarioIn{

    @JsonProperty("nombre")
    @NotNull(message = "El nombre es obligatorio") 
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @JsonProperty("apellidoPaterno")
    @NotNull(message = "El apellido paterno es obligatorio")
    @NotBlank(message = "El apellido paterno no puede estar en blanco")
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    @NotBlank(message = "El apellido materno no puede estar en blanco")
    private String apellidoMaterno;

    @JsonProperty("correo")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="El correo tiene un formato inválido")
    @NotNull(message = "El correo es obligatorio")
    @NotBlank(message = "El correo no puede estar en blanco")
    private String correo;

    @JsonProperty("contraseña")
    @NotNull(message = "La contraseña es obligatoria")
    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String contraseña;

    @JsonProperty("fotoIdentificacion")
    @NotNull(message = "La foto de identificación es obligatoria")
    @NotBlank(message = "La foto de identificación no puede estar en blanco")
    private String fotoIdentificacion;

    @JsonProperty("idZona")
    @NotNull(message = "La zona es obligatoria")
    private Long idZona;
    
    @JsonProperty("idFamilia")
    @NotNull(message = "La familia es obligatoria")
    private Long idFamilia;
}