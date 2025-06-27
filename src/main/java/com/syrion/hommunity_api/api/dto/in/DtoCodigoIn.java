package com.syrion.hommunity_api.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCodigoIn {

    @JsonProperty("codigo")
    @NotNull(message = "El codigo es obligatorio") 
    private String codigo;

    @JsonProperty("idInvitado")
    @NotNull(message = "El id del invitado es obligatorio") 
    private Long idInvitado;
}
