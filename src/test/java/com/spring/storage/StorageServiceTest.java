package com.spring.storage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest {

    @InjectMocks
    private StorageService storageService = new StorageService();

    @InjectMocks
    private StorageRepository storageRepositoryMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldSaveStorage() throws Exception {


        // assert
        verify(storageRepositoryMock, times(1)).save(any(Storage.class));

    }

}