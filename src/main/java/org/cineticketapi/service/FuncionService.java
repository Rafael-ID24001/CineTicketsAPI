package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.FuncionReqDto;
import org.cineticketapi.dto.FuncionRespDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.FuncionMapper;
import org.cineticketapi.model.Funcion;
import org.cineticketapi.projections.FuncionProjection;
import org.cineticketapi.repository.FuncionRepository;
import org.cineticketapi.util.Constants;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FuncionService {
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private FuncionMapper funcionMapper;

    public List<FuncionRespDto> getFunciones() {

        List<FuncionProjection> listFunciones = funcionRepository.getFuncionesDetalles();

        return listFunciones.stream().map(funcionMapper::toFuncionRespDto).toList();
    }

    public Optional<FuncionRespDto> findFuncionById(Long idSala) {
        Optional<Funcion> existFuncion = funcionRepository.findById(idSala);
        if (!existFuncion.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Registro no encontrada");
        }

        Optional<FuncionProjection> funcionDetails = funcionRepository.getFuncionDetallesByIdFuncion(idSala);

        return Optional.ofNullable(funcionMapper.toFuncionRespDto(funcionDetails.get()));
    }
    
    public Optional<FuncionRespDto> createFuncion(FuncionReqDto reqDto) {

        /* verificar si hay una funcion programada con la misma informacion*/
        Optional<Funcion> existFuncion = funcionRepository.findByIdPeliculaAndIdSalaEqualsAndFechaHora
                                        (reqDto.getIdPelicula(), reqDto.getIdSala(), reqDto.getFechaHora());

        if (existFuncion.isPresent() &&
                (existFuncion.get().getEstado() != ModelEnums.EstadoFuncion.CANCELADA||
                existFuncion.get().getEstado() != ModelEnums.EstadoFuncion.FINALIZADA)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Ya existe un registro con la misma informacion");
        }

        Funcion createdFuncion = funcionRepository.save(funcionMapper.forCreation(reqDto));

        Optional<FuncionProjection> funcionProj = funcionRepository.getFuncionDetallesByIdFuncion(createdFuncion.getIdFuncion());

        return Optional.ofNullable(funcionMapper.toFuncionRespDto(funcionProj.get()));
    }

    public Optional<FuncionRespDto> updateFuncion(Long idFuncion, String estadoFuncion) {
        Optional<Funcion> funcionExist = funcionRepository.findById(idFuncion);

        if (!funcionExist.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, Constants.MSG_REGISTRO_NO_ENCONTRADO);
        }

        funcionExist.get().setEstado(ModelEnums.EstadoFuncion.valueOf(estadoFuncion));

        Funcion createdFuncion = funcionRepository.save(funcionExist.get());
        Optional<FuncionProjection> updatedFuncion = funcionRepository.getFuncionDetallesByIdFuncion(createdFuncion.getIdFuncion());

        return Optional.ofNullable(funcionMapper.toFuncionRespDto(updatedFuncion.get()));
    }

    public Long deleteFuncion(Long idFuncion) {
        Optional<FuncionRespDto> deletedFuncion =
                this.updateFuncion(idFuncion, String.valueOf(ModelEnums.EstadoFuncion.CANCELADA));
        return deletedFuncion.get().getIdFuncion();
    }

    public int actualizarEstadoFuncionFutura(Long idSalaCine, ModelEnums.EstadoFuncion estadoFuncion) {
        return funcionRepository.actualizarEstadoFuncionesFuturas(idSalaCine, estadoFuncion);
    }

}
