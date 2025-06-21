package com.syrion.hommunity_api.api.entity;

import com.syrion.hommunity_api.api.enums.EstadoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "correo")

    private String correo;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "estado")
    private EstadoUsuario estado;

    @Column(name = "foto_identificacion")
    private String fotoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol idRol;

    @ManyToOne
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    private Zona idZona;

    @ManyToOne
    @JoinColumn(name = "id_familia", referencedColumnName = "id_familia")
    private Familia idFamilia;
}