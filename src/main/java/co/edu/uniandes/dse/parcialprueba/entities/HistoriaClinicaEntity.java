package co.edu.uniandes.dse.parcialprueba.entities;

import jakarta.persistence.ManyToOne;

public class HistoriaClinicaEntity {

    private String diagnostico; 
    private String tratamiento; 
    private String fechaCreacion; 

    @ManyToOne
    private PacienteEntity paciente;

}
