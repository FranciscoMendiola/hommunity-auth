package com.syrion.hommunity_api.api.entity;


import java.sql.Date;
import java.sql.Time;

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
@Table(name = "invitado")
public class Invitado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invitado")
    private Long idInvitado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "fecha_visita")
    private Date fechaVisita;

    @Column(name = "hora_entrada")
    private Time horaEntrada;
    
    @Column(name = "hora_salida")
    private Time horaSalida;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario idUsuario;
}
