package com.bnk03.bnklaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
