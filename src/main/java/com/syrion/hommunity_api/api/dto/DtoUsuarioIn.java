package com.syrion.hommunity_api.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.syrion.hommunity_api.api.entity.Familia;
import com.syrion.hommunity_api.api.entity.Zona;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "usuario")
public class DtoUsuarioIn{

    @JsonProperty("nombre")
    @NotNull(message = "El nombre es obligatorio") 
    private String nombre;

    @JsonProperty("apellidoPaterno")
    @NotNull(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("correo")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="El correo tiene un formato inválido")
    @NotNull(message = "El correo es obligatorio")
    private String correo;

    @JsonProperty("contraseña")
    @NotNull(message = "La contraseña es obligatoria")
    private String contraseña;

    @JsonProperty("fotoIdentificacion")
    @NotNull(message = "La foto de identificación es obligatoria")
    private String fotoIdentificacion;

    @JsonProperty("idZona")
    @ManyToOne
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    @NotNull(message = "La zona es obligatoria")
    private Zona idZona;
    
    @JsonProperty("idFamilia")
    @ManyToOne
    @JoinColumn(name = "id_familia", referencedColumnName = "id_familia")
    private Familia idFamilia;
}