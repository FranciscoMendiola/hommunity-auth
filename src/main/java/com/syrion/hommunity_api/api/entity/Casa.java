package com.syrion.hommunity_api.api.entity;

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
@Table(name = "casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_casa")
    private Long idCasa;

    @Column(name = "numero")
    private String numero;

    @Column(name = "calle")
    private String calle;

    @ManyToOne
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    private Zona idZona;
}
