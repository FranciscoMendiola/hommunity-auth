package com.syrion.hommunity_api.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syrion.hommunity_api.api.entity.Casa;

public interface CasaRepository extends JpaRepository<Casa, Long> {

    List<Casa> findByIdZonaIdZona(Long idZona); 
}
