package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity paciente) throws IllegalOperationException{
        log.info("Inicia proceso de creacion de paciente.");
        if(paciente.getTelefono().length() > 11){
            throw new IllegalOperationException("El numero de telefono no es valido");
        }
        if (!paciente.getTelefono().substring(0, 2).equals("311")){
            throw new IllegalOperationException("El numero de telefono no es valido");
        }
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public PacienteEntity asociarAcudiente(Long pacienteId, Long acudienteId) throws IllegalOperationException, EntityNotFoundException {
        log.info("Inicia proceso de agregarle un acudiente al paciente con id = {0}", pacienteId);
        
        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(pacienteId);
        if (pacienteEntity.isEmpty())
			throw new EntityNotFoundException("Paciente no fue encontrado.");
        Optional<PacienteEntity> acudienteEntity = pacienteRepository.findById(acudienteId);
        if (acudienteEntity.isEmpty())
			throw new EntityNotFoundException("Acudiente no fue encontrado.");

        if (!(pacienteEntity.get().getAcudiente() == null)) {
            throw new IllegalOperationException("El paciente ya tiene un acudiente asociado");
        }

        if (!(acudienteEntity.get().getAcudiente() == null)) {
            throw new IllegalOperationException("El acudiente ya tiene un acudiente.");
        }

        if (acudienteEntity.get().getHistoriasClinicas().isEmpty()) {
            throw new IllegalOperationException("El acudiente no cuenta con una historia clinica");
        }

        pacienteEntity.get().setAcudiente(acudienteEntity.get());

        return pacienteEntity.get();
    }
}
