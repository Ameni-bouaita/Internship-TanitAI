package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Repositories.BlocRepository;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.FoyerApplication;
import tn.esprit.spring.Services.Bloc.BlocService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(classes = FoyerApplication.class)
@ExtendWith(SpringExtension.class)

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Utilise H2
class BlocServiceUnitTest {

    @Mock
    private BlocRepository blocRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private BlocService blocService;

    @BeforeEach
    public void setup() {
        // Initialisation des mocks si nécessaire
    }

    @Test
    void testGetAllBlocs() {
        Bloc bloc1 = new Bloc(1L, "BlocA", 10, null, Arrays.asList(new Chambre()));
        Bloc bloc2 = new Bloc(2L, "BlocB", 20, null, Arrays.asList(new Chambre()));
        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc1, bloc2));

        List<Bloc> blocList = blocService.findAll();

        assertEquals(2, blocList.size());
        assertEquals("BlocA", blocList.get(0).getNomBloc());
        assertEquals("BlocB", blocList.get(1).getNomBloc());
    }

    @Test
    void testGetBlocById() {
        Bloc bloc = new Bloc(1L, "BlocA", 10, null, null);
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        Bloc blocById = blocService.findById(1L);

        assertNotNull(blocById);
        assertEquals("BlocA", blocById.getNomBloc());
    }

    @Test
    void testGetInvalidBlocById() {
        when(blocRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            blocService.findById(999L);
        });

        assertTrue(exception.getMessage().contains("No value present"));
    }

    @Test
    void testCreateBloc() {
        // Création d'une liste vide de chambres
        List<Chambre> chambres = new ArrayList<>();

        // Création d'un objet Bloc avec une liste de chambres initialisée
        Bloc bloc = new Bloc(0L, "BlocC", 30, null, chambres); // Utilisez 0L pour l'ID initial

        // Mock de la méthode save pour renvoyer un bloc créé avec un ID
        when(blocRepository.save(any(Bloc.class))).thenAnswer(invocation -> {
            Bloc b = invocation.getArgument(0);
            b.setIdBloc(3L); // Simulez l'attribution d'un ID
            return b;
        });

        // Appel de la méthode addOrUpdate pour tester
        Bloc createdBloc = blocService.addOrUpdate(bloc);

        // Vérification des interactions avec le repository
        verify(blocRepository, times(1)).save(bloc);

        // Assertions
        assertNotNull(createdBloc.getIdBloc()); // Vérifiez que l'ID a été attribué
        assertEquals("BlocC", createdBloc.getNomBloc());
    }

        @Test
    void testDeleteBloc() {
        Bloc bloc = new Bloc(2L, "BlocB", 20, null, null);
        when(blocRepository.findById(2L)).thenReturn(Optional.of(bloc));

        blocService.deleteById(bloc.getIdBloc());

        verify(blocRepository, times(1)).deleteById(2L);
    }

    @Test
    void testDeleteInvalidBloc() {
        // Simuler que le bloc n'existe pas
        when(blocRepository.findById(999L)).thenReturn(Optional.empty());

        // Exécution de la méthode deleteById qui ne lance pas d'exception dans votre implémentation actuelle
        blocService.deleteById(999L);

        // Vérification que la méthode deleteById a bien été appelée, même si elle ne lève pas d'exception
        verify(blocRepository, times(1)).deleteById(999L);
    }

}
