package org.cineticketapi.service;

import jakarta.transaction.Transactional;
import org.cineticketapi.dto.AsientoDto;
import org.cineticketapi.exception.ApiException;
import org.cineticketapi.mapper.AsientoMapper;
import org.cineticketapi.model.Asiento;
import org.cineticketapi.projections.AsientoProjection;
import org.cineticketapi.repository.AsientoRepository;
import org.cineticketapi.util.Constants;
import org.cineticketapi.util.enums.ModelEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsientoService {
    @Autowired
    private AsientoRepository asientoRepository;
    @Autowired
    private AsientoMapper asientoMapper;

    public List<AsientoDto> getAsientos(Long idSalaCine) {

        List<AsientoProjection> listFunciones = asientoRepository.listAsientosDetails(idSalaCine);

        return listFunciones.stream().map(asientoMapper::toAsientoDto).toList();
    }

    public Optional<AsientoDto> findAsientoById(Long idAsiento) {
        Optional<Asiento> existAsiento = asientoRepository.findById(idAsiento);
        if (!existAsiento.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Registro no encontrada");
        }

        Optional<AsientoProjection> funcionDetails = asientoRepository.getAsientosDetailsByID(idAsiento);

        return Optional.ofNullable(asientoMapper.toAsientoDto(funcionDetails.get()));
    }

    public void createAsientoSalaCine(Long idSalaCine,Integer asientosPorFila, Integer totalAsientos) {
        String[] letrasFilaAsiento = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Integer totalFilas = (int) Math.ceil((double) totalAsientos / asientosPorFila);

        for (int i = 0; i < totalFilas; i++) {
            String letraFila = letrasFilaAsiento[i];
            for (int j = 1; j < asientosPorFila; j++) {
                Asiento asientoToSave = Asiento.builder()
                        .idSalaCine(idSalaCine)
                        .fila(letraFila)
                        .numero(j)
                        .tipoAsiento(ModelEnums.TipoAsiento.REGULAR)
                        .estado(ModelEnums.EstadoAsiento.DISPONIBLE)
                        .build();
                asientoRepository.save(asientoToSave);
            }
        }
    }

    public Optional<AsientoDto> updateAsiento(Long idAsiento, String estadoAsiento) {
        Optional<Asiento> asientoExist = asientoRepository.findById(idAsiento);

        if (!asientoExist.isPresent()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, Constants.MSG_REGISTRO_NO_ENCONTRADO);
        }

        asientoExist.get().setEstado(ModelEnums.EstadoAsiento.valueOf(estadoAsiento));

        Asiento createdAsiento = asientoRepository.save(asientoExist.get());
        Optional<AsientoProjection> updatedFuncion = asientoRepository.getAsientosDetailsByID(createdAsiento.getIdAsiento());

        return Optional.ofNullable(asientoMapper.toAsientoDto(updatedFuncion.get()));
    }

    public Long deleteAsiento(Long idFuncion) {
        Optional<AsientoDto> deletedFuncion =
                this.updateAsiento(idFuncion, String.valueOf(ModelEnums.EstadoAsiento.MANTENIMIENTO));
        return deletedFuncion.get().getIdAsiento();
    }

}
