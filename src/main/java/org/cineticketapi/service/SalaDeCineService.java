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
    private AsientoService asientoService;
    @Autowired
    private FuncionService funcionService;
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

        SalaDeCine salaDeCine = salaDeCineMapper.forCreation(salaDtoReq);
        SalaDeCine createdsalaDeCine = salaCineRepository.save(salaDeCine);

        /* crear todos los asientos correspondientes a la nueva sala */
        asientoService.createAsientoSalaCine(createdsalaDeCine.getIdSala(),12, salaDtoReq.getCapacidadTotal());

        return Optional.ofNullable(salaDeCineMapper.DomainToDto(createdsalaDeCine));

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

        /**
        *   Si estado cambia a MANTENIMIENTO o FUERA_DE_SERVICIO:
        *   Se cancelan las funciones futuras
        * */
        String estadoSala = Optional.ofNullable(salaDtoReq.getEstado()).map(String::toUpperCase).orElse(null);
        if (estadoSala != null &&
                (ModelEnums.EstadoSala.MANTENIMIENTO.name().equals(estadoSala) ||
                ModelEnums.EstadoSala.FUERA_DE_SERVICIO.name().equals(estadoSala))) {
            funcionService.actualizarEstadoFuncionFutura(salaDtoReq.getIdSala(), ModelEnums.EstadoFuncion.CANCELADA);
        }

        Optional<SalaDeCine> modelUpdate = salaCineRepository.findByIdSala(salaDtoReq.getIdSala());
        salaDeCineMapper.forUpdate(salaDtoReq, modelUpdate.get());

        return Optional.ofNullable(salaDeCineMapper.DomainToDto(salaCineRepository.save(modelUpdate.get())));
    }

    public Long deleteSala(Long idSala) {

        Optional<SalaCineDto> salaExiste = this.findSalaById(idSala);
        if (salaExiste.get().getIdSala() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sala " + Constants.MSG_NO_ENCONTRADO);
        }

        /* Cancelar funciones futuras */
        funcionService.actualizarEstadoFuncionFutura(idSala, ModelEnums.EstadoFuncion.CANCELADA);

        // Simular borrado logico de sala
        salaExiste.get().setEstado(String.valueOf(ModelEnums.EstadoSala.FUERA_DE_SERVICIO));
        SalaDeCine updatedModel = salaCineRepository.save(salaDeCineMapper.DtoToDomain(salaExiste.get()));
        return updatedModel.getIdSala();
    }
}
