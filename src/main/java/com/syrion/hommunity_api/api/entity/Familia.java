package com.syrion.hommunity_api.api.entity;
import java.sql.Timestamp;

import com.syrion.hommunity_api.api.enums.EstadoUsuario;
import com.syrion.hommunity_api.common.mapper.MapperEstadoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
@Table(name = "familia")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_familia")
    private Long idFamilia;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "estado")
    @Convert(converter = MapperEstadoUsuario.class)
    private EstadoUsuario estado;


    
    @Column(name = "foto_identificacion")
    private String fotoIdentificacion;

    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_casa", referencedColumnName = "id_casa")
    private Casa idCasa;

    @ManyToOne
    @JoinColumn(name = "id_usuario_registrador", referencedColumnName = "id_usuario")
    private Usuario idUsuarioRegistrador;
}
