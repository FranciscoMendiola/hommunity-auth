package com.syrion.hommunity_api.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syrion.hommunity_api.api.entity.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Long> {

    // path: Familia.idCasa.idZona.idZona
    List<Familia> findByIdCasaIdZonaIdZona(Long idZona);
}
