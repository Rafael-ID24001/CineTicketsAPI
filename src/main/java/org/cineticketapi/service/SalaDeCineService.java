package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.SalaCineDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.SalaDeCineMapper;
import org.cineticketapi.model.SalaDeCine;
import org.cineticketapi.repository.SalaDeCineRepository;
import org.cineticketapi.util.Constants;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalaDeCineService {
    @Autowired
    private SalaDeCineRepository salaCineRepository;
    @Autowired
    private SalaDeCineMapper salaDeCineMapper;


    public List<SalaCineDto> getSalas() {
        List<SalaDeCine> salaDeCines = salaCineRepository.findAll();
        return salaDeCines.stream().map(salaDeCineMapper::DomainToDto).collect(Collectors.toList());
    }

    public Optional<SalaCineDto> findSalaByNombre(SalaCineDto salaDto) {
        Optional<SalaDeCine> sala = salaCineRepository.findByNombre(salaDto.getNombre());
        if (sala.isPresent()) {
            salaDto = salaDeCineMapper.DomainToDto(sala.get());
        }
        return Optional.ofNullable(salaDto);
    }

    public Optional<SalaCineDto> findSalaById(Long idSala) {
        SalaCineDto salaDto = new SalaCineDto();
        Optional<SalaDeCine> sala = salaCineRepository.findByIdSala(idSala);
        if (sala.isPresent()) {
            salaDto = salaDeCineMapper.DomainToDto(sala.get());
        }
        return Optional.ofNullable(salaDto);
    }

    public Optional<SalaCineDto> createSala(SalaCineDto salaDtoReq) {
        Optional<SalaDeCine> existSala = salaCineRepository.findByNombre(salaDtoReq.getNombre());

        if (existSala.isPresent() && existSala.get().getIdSala() != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sala ya existe");
        }

        // TODO: ASIENTO:  crear todos los asientos correspondientes a la nueva sala

        SalaDeCine salaDeCine = salaDeCineMapper.forCreation(salaDtoReq);
        return Optional.ofNullable(salaDeCineMapper.DomainToDto(salaCineRepository.save(salaDeCine)));
    }

    public Optional<SalaCineDto> update(SalaCineDto salaDtoReq) {
        Optional<SalaCineDto> salaPorId = this.findSalaById(salaDtoReq.getIdSala());

        if (salaPorId.get().getIdSala() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sala " + Constants.MSG_NO_ENCONTRADO);
        }

        Optional<SalaCineDto> salaPorNombre = this.findSalaByNombre(salaDtoReq);

        if (!salaPorNombre.isEmpty() && salaPorNombre.get().getIdSala() != salaDtoReq.getIdSala()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Nombre de sala ya registrado");
        }

        /*
        * TODO: Cambio de CAPACIDAD o DISPOSICION de asientos
        *   - Agregar nuevos asientos si aumenta la capacidad
        *   - Eliminar/modificar asientos existentes si disminuye la capacidad
        *
        *TODO: Cambio de TIPO_SALA
        *   Actualizar tipo_asiento si cambia el tipo_sala
        *       - ASIENTO: Actualizar tipo_asiento para coincidir con el nuevo tipo de sala
        *       - FUNCION: Revisar compatibilidad con películas programadas
        *
        * TODO: Cambio de ESTADO de la sala
        *   Si estado cambia a MANTENIMIENTO o FUERA_DE_SERVICIO:
        *   - Cancelar funciones futuras programadas
        *   - Actualizar estado de funciones afectadas a CANCELADA
        *
        * */

        Optional<SalaDeCine> modelUpdate = salaCineRepository.findByIdSala(salaDtoReq.getIdSala());
        salaDeCineMapper.forUpdate(salaDtoReq, modelUpdate.get());

        return Optional.ofNullable(salaDeCineMapper.DomainToDto(salaCineRepository.save(modelUpdate.get())));
    }

    public Long deleteSala(Long idSala) {

        Optional<SalaCineDto> salaExiste = this.findSalaById(idSala);
        if (salaExiste.get().getIdSala() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sala " + Constants.MSG_NO_ENCONTRADO);
        }
        /*
        * TODO: ASIENTO - ELIMINACIÓN EN CASCADA
        *   Hacer un borrado logico
        * TODO: FUNCION
        *   - Las funciones asociadas NO se eliminan en cascada
        *   - Debes manejar manualmente:
        *       Cancelar funciones futuras
        *       Reubicar funciones o eliminar manualmente
        *
        * */

        // Simular borrado logico de sala
        salaExiste.get().setEstado(String.valueOf(ModelEnums.EstadoSala.FUERA_DE_SERVICIO));
        SalaDeCine updatedModel = salaCineRepository.save(salaDeCineMapper.DtoToDomain(salaExiste.get()));
        return updatedModel.getIdSala();
    }
}
