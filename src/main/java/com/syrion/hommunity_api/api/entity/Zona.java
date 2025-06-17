package com.syrion.hommunity_api.api.entity;

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
@Table(name = "zona")
public class Zona {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Long idZona;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "codigo_postal")
    private Integer codigoPostal;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "colonia")
    private String colonia;

      
}
