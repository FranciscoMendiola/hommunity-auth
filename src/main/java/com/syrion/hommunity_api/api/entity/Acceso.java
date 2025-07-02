package com.syrion.hommunity_api.api.entity;

import java.sql.Timestamp;

import com.syrion.hommunity_api.api.enums.TipoAcceso;

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
@Table(name = "acceso")
public class Acceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acceso")
    private Long idAcceso;

    @Column(name = "tipo")
    private TipoAcceso tipoAcceso;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "id_qr", referencedColumnName = "id_qr")
    private QR idQr;
}
