package tn.esprit.spring;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Repositories.BlocRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.FoyerRepository;
import tn.esprit.spring.FoyerApplication;

import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest(classes = FoyerApplication.class)
@ExtendWith(SpringExtension.class)

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Utilise H2
class BlocRepositoryUnitTest {

    @Mock
    BlocRepository blocRepository;

    @BeforeEach
    void setUp() {
        // Utilisation de Mockito pour simuler le comportement du repository
       blocRepository.save(new Bloc(1L, "Math", 5, null, new ArrayList<>()));
        blocRepository.save(new Bloc(2L, "Physique", 20, null, new ArrayList<>()));
    }

    @AfterEach
    void destroy() {
        // Reset le mock après chaque test pour éviter des interférences entre tests
        blocRepository.deleteAll();
    }

    @Test
    void testGetInvalidBloc() {
        // Test pour vérifier qu'une exception est levée pour un bloc inexistant
        assertThrows(NoSuchElementException.class, () -> {
            blocRepository.findById(1L).orElseThrow();
        });
    }

    @Test
    void testDeleteBloc() {
        // Création et suppression d'un bloc pour le test
        Bloc savedBloc = new Bloc(5L, "Chimie", 15, null, new ArrayList<>());
        blocRepository.save(savedBloc);
        blocRepository.delete(savedBloc);

        // Vérification que le bloc n'existe plus
        assertThrows(NoSuchElementException.class, () -> {
            blocRepository.findById(5L).orElseThrow();
        });
    }
}
