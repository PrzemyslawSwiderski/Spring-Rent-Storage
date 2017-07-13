package com.spring.storage;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  @InjectMocks
  private StorageService storageService = new StorageService();
  @InjectMocks
  private StorageRepository storageRepositoryMock;

  @Test
  public void shouldSaveStorage() throws Exception {

    // assert
    verify(storageRepositoryMock, times(1)).save(any(Storage.class));

  }

}