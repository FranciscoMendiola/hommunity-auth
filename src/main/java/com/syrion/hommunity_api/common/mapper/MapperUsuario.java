package com.syrion.hommunity_api.common.mapper;

import org.springframework.stereotype.Service;

import com.syrion.hommunity_api.api.entity.Usuario;
import com.syrion.hommunity_api.api.dto.in.DtoUsuarioIn;
import com.syrion.hommunity_api.api.dto.out.DtoUsuarioOut;
import com.syrion.hommunity_api.api.entity.Rol;
import com.syrion.hommunity_api.api.enums.EstadoUsuario;

@Service
public class MapperUsuario {

    public Usuario fromUsuario(DtoUsuarioIn in) {
        Usuario usuario = new Usuario();
        usuario.setNombre(in.getNombre());
        usuario.setApellidoMaterno(in.getApellidoMaterno());
        usuario.setApellidoPaterno(in.getApellidoPaterno());
        usuario.setCorreo(in.getCorreo());
        usuario.setContraseña(in.getContraseña());
        if (in.getIdFamilia() != null) {
            usuario.setEstado(EstadoUsuario.APROBADO);
            usuario.setIdFamilia(in.getIdFamilia());
        } else {
            usuario.setEstado(EstadoUsuario.PENDIENTE);
        }
        usuario.setIdZona(in.getIdZona());
        usuario.setFotoIdentificacion(in.getFotoIdentificacion());

        Rol rol = new Rol();
        rol.setIdRol(1L);
        rol.setNombreRol("Residente");
        usuario.setIdRol(rol);

        return usuario;
    }


    public DtoUsuarioOut fromDtoUsuario(Usuario usuario) {
        DtoUsuarioOut out = new DtoUsuarioOut();
        
        out.setIdUsuario(usuario.getIdUsuario());
        out.setNombre(usuario.getNombre());
        out.setApellidoMaterno(usuario.getApellidoMaterno());
        out.setApellidoPaterno(usuario.getApellidoPaterno());
        out.setCorreo(usuario.getCorreo());
        out.setIdFamilia(usuario.getIdFamilia().getIdFamilia());
        out.setIdZona(usuario.getIdZona().getIdZona());
        out.setEstado(usuario.getEstado());
        out.setFotoIdentificacion(usuario.getFotoIdentificacion());
        out.setIdRol(usuario.getIdRol().getIdRol());

        return out;
    }
}
