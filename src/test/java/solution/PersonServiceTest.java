package solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.betrybe.agrix.agrix.models.entities.Person;
import com.betrybe.agrix.agrix.exceptions.PersonNotFoundException;
import com.betrybe.agrix.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.agrix.security.Role;
import com.betrybe.agrix.agrix.services.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @MockBean
  PersonRepository personRepository;

  @Autowired
  PersonService personService;

  @Test
  @DisplayName("Service - Deve retornar objeto correto contendo a pessoa referente ao id")
  public void testGetPersonByIdService() {

    Person mockGetPersonByIdService = new Person();

    mockGetPersonByIdService.setId(1L);
    mockGetPersonByIdService.setUsername("Matheus");
    mockGetPersonByIdService.setPassword("875632");
    mockGetPersonByIdService.setRole(Role.ADMIN);
    mockGetPersonByIdService.setRole(Role.MANAGER);
    mockGetPersonByIdService.setRole(Role.USER);

    Mockito.when(
        personRepository.findById(1L)
    ).thenReturn(
        Optional.of(mockGetPersonByIdService)
    );

    Person getPersonByIdService = personService.getPersonById(1L);

    assertNotNull(getPersonByIdService);
    assertEquals(mockGetPersonByIdService, getPersonByIdService);

  }

  @Test
  @DisplayName("Service - Deve lançar exceção ao tentar buscar pessoa inexistente!")
  public void testGetPersonByIdServiceException() {

    Mockito.when(
        personRepository.findById(1L)
    ).thenReturn(
        Optional.empty()
    );

    assertThrows(
        PersonNotFoundException.class,
        () -> personService.getPersonById(1L)
    );

  }

  @Test
  @DisplayName("Service - Deve retornar objeto correto contendo a pessoa quando dado um username")
  public void testGetPersonByUsernameService() {

    Person mockGetPersonByUsernameService = new Person();

    mockGetPersonByUsernameService.setId(1L);
    mockGetPersonByUsernameService.setUsername("Matheus");
    mockGetPersonByUsernameService.setPassword("875632");
    mockGetPersonByUsernameService.setRole(Role.ADMIN);
    mockGetPersonByUsernameService.setRole(Role.MANAGER);
    mockGetPersonByUsernameService.setRole(Role.USER);

    Mockito.when(
        personRepository.findByUsername("Matheus")
    ).thenReturn(
        Optional.of(mockGetPersonByUsernameService)
    );

    Person getPersonByUsernameService = personService.getPersonByUsername("Matheus");

    assertNotNull(getPersonByUsernameService);
    assertEquals(mockGetPersonByUsernameService, getPersonByUsernameService);

  }

  @Test
  @DisplayName("Service - Deve lançar exceção ao tentar buscar pessoa pelo username inexistente!")
  public void testGetPersonByUsernameServiceException() {

    Mockito.when(
        personRepository.findByUsername("Matheus")
    ).thenReturn(
        Optional.empty()
    );

    assertThrows(
        PersonNotFoundException.class,
        () -> personService.getPersonByUsername("Matheus")
    );

  }

  @Test
  @DisplayName("Service - Deve criar uma nova pessoa")
  public void testCreatePersonService() {

    Person mockCreatePersonService = new Person();

    mockCreatePersonService.setUsername("Matheus");
    mockCreatePersonService.setPassword("875632");
    mockCreatePersonService.setRole(Role.ADMIN);
    mockCreatePersonService.setRole(Role.MANAGER);
    mockCreatePersonService.setRole(Role.USER);

    Mockito.when(
        personRepository.save(mockCreatePersonService)
    ).thenReturn(
        mockCreatePersonService
    );

    Person createPersonService = personService.create(mockCreatePersonService);

    assertNotNull(createPersonService);
    assertEquals(mockCreatePersonService, createPersonService);

  }

}
