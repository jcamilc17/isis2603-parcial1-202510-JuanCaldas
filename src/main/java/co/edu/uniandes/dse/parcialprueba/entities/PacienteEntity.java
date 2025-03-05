package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PacienteEntity extends BaseEntity{

    private String nombre;
    private String correo;
    private String telefono;

    @OneToMany
    private List<HistoriaClinicaEntity> historiasClinicas;

    @OneToOne
    private PacienteEntity acudiente;
}
