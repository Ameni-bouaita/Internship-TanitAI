package tn.esprit.spring;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Repositories.BlocRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.FoyerApplication;
import tn.esprit.spring.RestControllers.BlocRestController;
import tn.esprit.spring.Services.Bloc.IBlocService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.http.ResponseEntity;
@SpringBootTest(classes = FoyerApplication.class)
@RunWith(MockitoJUnitRunner.class)

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Utilise H2
class BlocRCTest {
    @Mock
    private IBlocService blocService;

    @InjectMocks
    private BlocRestController blocController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllBlocs() {
        // Mock data
        Bloc bloc1 = new Bloc(1L, "BlocA", 10, null, new ArrayList<>());
        Bloc bloc2 = new Bloc(2L, "BlocB", 20, null, new ArrayList<>());
        List<Bloc> blocList = Arrays.asList(bloc1, bloc2);

        // Mocking behavior
        when(blocService.findAll()).thenReturn(blocList);

        // Perform the test
        List<Bloc> result = blocController.findAll();

        // Verify the interactions
        verify(blocService, times(1)).findAll();

        // Assertions
        assertEquals(2, result.size());
    }

    @Test
    void testGetBlocById() {
        // Mock data
        Bloc bloc = new Bloc(1L, "BlocA", 10, null, new ArrayList<>());

        // Mocking behavior
        when(blocService.findById(1L)).thenReturn(bloc);

        // Perform the test
        Bloc result = blocController.findById(1L);

        // Verify the interactions
        verify(blocService, times(1)).findById(1L);

        // Assertions
        assertEquals(bloc, result);
    }

    @Test
    void testAddOrUpdateBloc() {
        // Mock data
        Bloc bloc = new Bloc(1L, "BlocA", 10, null, new ArrayList<>());

        // Mocking behavior
        when(blocService.addOrUpdate(any(Bloc.class))).thenReturn(bloc);

        // Perform the test
        Bloc result = blocController.addOrUpdate(bloc);

        // Verify the interactions
        verify(blocService, times(1)).addOrUpdate(any(Bloc.class));

        // Assertions
        assertNotNull(result);
        assertEquals("BlocA", result.getNomBloc());
    }

    @Test
    void testDeleteBloc() {
        // Mock data
        Bloc bloc = new Bloc(1L, "BlocA", 10, null, new ArrayList<>());

        // Perform the test
        blocController.delete(bloc);

        // Verify the interactions
        verify(blocService, times(1)).delete(bloc);
    }

    @Test
    void testDeleteBlocById() {
        // Perform the test
        blocController.deleteById(1L);

        // Verify the interactions
        verify(blocService, times(1)).deleteById(1L);
    }

    @Test
    void testAffecterChambresABloc() {
        // Mock data
        List<Long> numChambres = Arrays.asList(101L, 102L);
        String nomBloc = "BlocA";
        Bloc bloc = new Bloc(1L, "BlocA", 10, null, new ArrayList<>());

        // Mocking behavior
        when(blocService.affecterChambresABloc(numChambres, nomBloc)).thenReturn(bloc);

        // Perform the test
        Bloc result = blocController.affecterChambresABloc(numChambres, nomBloc);

        // Verify the interactions
        verify(blocService, times(1)).affecterChambresABloc(numChambres, nomBloc);

        // Assertions
        assertNotNull(result);
        assertEquals("BlocA", result.getNomBloc());
    }

    @Test
    void testAffecterBlocAFoyer() {
        // Mock data
        String nomBloc = "BlocA";
        String nomFoyer = "Foyer1";
        Bloc bloc = new Bloc(1L, "BlocA", 10, null, new ArrayList<>());

        // Mocking behavior
        when(blocService.affecterBlocAFoyer(nomBloc, nomFoyer)).thenReturn(bloc);

        // Perform the test
        Bloc result = blocController.affecterBlocAFoyer(nomBloc, nomFoyer);

        // Verify the interactions
        verify(blocService, times(1)).affecterBlocAFoyer(nomBloc, nomFoyer);

        // Assertions
        assertNotNull(result);
        assertEquals("BlocA", result.getNomBloc());
    }
}