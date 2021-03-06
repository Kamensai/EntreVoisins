package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addNeighbourWithSuccess() {
        Neighbour neighbourAdded = new Neighbour(13, "josé", "https://i.pravatar.cc/150?u=", "rue du paradis", "+33 45 78 95 62", "Rien à dire", false);
        service.createNeighbour(neighbourAdded);
        assertTrue(service.getNeighbours().contains(neighbourAdded));
    }

    @Test
    public void changeFavoriteWithSuccess() {
        Neighbour changedFavoriteNeighbour = service.getNeighbours().get(0);
        assertFalse(changedFavoriteNeighbour.getIsFavorite());
        // changeFavoriteNeighbour.isFavorite() is initially false.
        changedFavoriteNeighbour.setIsFavorite(true);
        service.updateNeighbourFavorite(changedFavoriteNeighbour);
        assertTrue(service.getNeighbours().get(0).getIsFavorite());
    }
}
