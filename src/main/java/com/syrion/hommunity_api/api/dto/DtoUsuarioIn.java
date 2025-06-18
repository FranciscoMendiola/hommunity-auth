package com.syrion.hommunity_api.api.dto;

import com.syrion.hommunity_api.api.entity.Familia;
import com.syrion.hommunity_api.api.entity.Zona;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "usuario")
public class DtoUsuarioIn{

    @Column(name = "nombre")
    @NotNull(message = "El nombre es obligatorio") 
    private String nombre;

    @Column(name = "apellido_paterno")
    @NotNull(message = "El apellido paterno es obligatorio")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "correo")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="El correo tiene un formato inválido")
    @NotNull(message = "El correo es obligatorio")
    private String correo;

    @Column(name = "contraseña")
    @NotNull(message = "La contraseña es obligatoria")
    private String contraseña;

    @Column(name = "foto_identificacion")
    @NotNull(message = "La foto de identificación es obligatoria")
    private String fotoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    private Zona idZona;

    @ManyToOne
    @JoinColumn(name = "id_familia", referencedColumnName = "id_familia")
    private Familia idFamilia;
}