package com.syrion.hommunity_api.api.entity;

import java.sql.Timestamp;

import com.syrion.hommunity_api.api.enums.EstadoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private EstadoUsuario estado;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
}

