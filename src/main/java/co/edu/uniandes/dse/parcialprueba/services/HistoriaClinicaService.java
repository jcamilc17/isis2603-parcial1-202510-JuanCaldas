package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private HistoriaClinicaRepository historiaClinicaRepository;

    @Transactional
    public PacienteEntity addHistoriaClinica (Long historiaId, Long pacienteId) throws EntityNotFoundException{

        log.info("Inicia proceso de asociarle una historia clinica al paciente con id = {0}", pacienteId);

        Optional<HistoriaClinicaEntity> historiaClinicaEntity = historiaClinicaRepository.findById(historiaId);
        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);

        if (historiaClinicaEntity.isEmpty())
			throw new EntityNotFoundException("No se pudo encontrar la historia clinica.");

		if (pacienteEntity.isEmpty())
			throw new EntityNotFoundException("No se pudo encontrar el paciente.");

        pacienteEntity.get().getHistoriasClinicas().add(historiaClinicaEntity.get());
        log.info("Termina proceso de asociarle una historia clinica al paciente con id = {0}", pacienteId);
        return pacienteEntity.get();
    }

}
