package com.bnk03.bnklaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.bnk03.bnklaim.entity.CollectionId;
import com.bnk03.bnklaim.exception.DataNotFoundException;
import com.bnk03.bnklaim.repositories.CollectionIdRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CollectionIdServiceTest {

    @Mock
    CollectionIdRepository collectionIdRepository;

    @InjectMocks
    CollectionIdService collectionIdService;

    @Test
    void testGetCollection() throws DataNotFoundException {

        CollectionId collection = new CollectionId("case", 1);
        when(collectionIdRepository.findById("case")).thenReturn(Optional.of(collection));

        assertEquals(collection.toString(), collectionIdService.getCollection("case").toString());
    }

    @Test

    void testGetCollectionNotFound() {
        when(collectionIdRepository.findById("case")).thenReturn(Optional.empty());
        Exception exception = assertThrows(DataNotFoundException.class,
                () -> collectionIdService.getCollection("case"));

        String expectedMessage = "Collection name not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testIncreaseCollectionShouldReturnTwo() throws DataNotFoundException {
        CollectionId collection = new CollectionId();
        collection.setIdName("case");
        collection.setCurrentCollectionId(1);

        when(collectionIdRepository.findById("case")).thenReturn(Optional.of(collection));
        when(collectionIdRepository.save(collection)).thenReturn(collection);
        Integer actual = collectionIdService.increaseCollectionId("case", 1);

        assertEquals(2, actual);

        verify(collectionIdRepository, times(1)).save(collection);
    }

    @Test
    void testIncreaseCollectionIdThrowseError() {
        CollectionId collection = new CollectionId();
        collection.setIdName("case");

        when(collectionIdRepository.findById("case")).thenReturn(Optional.empty());

        Exception exception = assertThrows(DataNotFoundException.class,
                () -> collectionIdService.increaseCollectionId("case", 1));

        String expectedMessage = "Collection name not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(collectionIdRepository, never()).save(collection);
    }
}
