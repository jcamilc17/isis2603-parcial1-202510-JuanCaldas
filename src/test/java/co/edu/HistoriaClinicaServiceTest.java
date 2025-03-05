package co.edu;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialprueba.services.HistoriaClinicaService;
import co.edu.uniandes.dse.parcialprueba.services.PacienteService;
import co.edu.uniandes.dse.parcialprueba.entities.*;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HistoriaClinicaService.class)

public class HistoriaClinicaServiceTest {

    @Autowired
    private HistoriaClinicaService historiaClinicaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<HistoriaClinicaEntity> pacienteList = new ArrayList<>();

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
	}

    private void insertData() {
		for (int i = 0; i < 3; i++) {
			HistoriaClinicaEntity historiaClinicaEntity = factory.manufacturePojo(HistoriaClinicaEntity.class);
			entityManager.persist(historiaClinicaEntity);
			pacienteList.add(historiaClinicaEntity);
		}
	}

    @Test
	void testAddHistoriaClinica() throws EntityNotFoundException, IllegalOperationException {
		PacienteEntity newPaciente = factory.manufacturePojo(PacienteEntity.class);
		pacienteService.createPaciente(newPaciente);

		PacienteEntity pacienteEntity = historiaClinicaService.addHistoriaClinica(null, null)
		assertNotNull(pacienteEntity);

		assertEquals(pacienteEntity.getId(), newPaciente.getId());
		assertEquals(pacienteEntity.getNombre(), newPaciente.getNombre());
		assertEquals(pacienteEntity.getCorreo(), newPaciente.getCorreo());
		assertEquals(pacienteEntity.getTelefono(), newPaciente.getTelefono())

		PacienteEntity lastPaciente = authorBookService.getBook(author.getId(), newPaciente.getId());

		assertEquals(lastBook.getId(), newPaciente.getId());
		assertEquals(lastBook.getName(), newPaciente.getName());
		assertEquals(lastBook.getDescription(), newPaciente.getDescription());
		assertEquals(lastBook.getIsbn(), newPaciente.getIsbn());
		assertEquals(lastBook.getImage(), newPaciente.getImage());

	}
}
