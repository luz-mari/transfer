package com.proyecto.ntt.transfer.service.impl;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.repository.TransferRepository;
import com.proyecto.ntt.transfer.repository.dao.TransferDao;
import com.proyecto.ntt.transfer.service.dto.Movimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TransferServiceImplTest {

    @Mock
    private TransferRepository repository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private TransferServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mockeo del flujo del WebClient
        /*when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.body(any(), eq(Movimiento.class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(Movimiento.class))).thenReturn(Mono.just(new Movimiento()));*/
    }

    /*@Test
    void createTransfer_ShouldCreateTransferSuccessfully() {
        // Arrange
        Transfer transfer = new Transfer();
        transfer.setNumeroCuentaCliente(1001);
        transfer.setNumeroCuentaDestino(1002);
        transfer.setMonto(200.0);
        transfer.setFecha(LocalDate.now());

        TransferDao savedTransferDao = new TransferDao();
        savedTransferDao.setId(1);
        savedTransferDao.setNumeroCuentaCliente(1001);
        savedTransferDao.setNumeroCuentaDestino(1002);
        savedTransferDao.setMonto(200.0);
        savedTransferDao.setFecha(LocalDate.now());

        when(repository.save(any(TransferDao.class))).thenReturn(savedTransferDao);

        // Act
        Mono<Transfer> resultMono = service.createTransfer(transfer);
        Transfer result = resultMono.block();

        // Assert
        assertNotNull(result);
        assertEquals(1001, result.getNumeroCuentaCliente());
        assertEquals(200.0, result.getMonto());
        verify(repository, times(1)).save(any(TransferDao.class));
        verify(webClient, times(2)).post(); // Dos llamadas: retiro y depósito
    }*/

    @Test
    void getTransfer_ShouldReturnTransfer() {
        // Arrange
        TransferDao transferDao = new TransferDao();
        transferDao.setId(1);
        transferDao.setNumeroCuentaCliente(1001);
        transferDao.setNumeroCuentaDestino(1002);
        transferDao.setMonto(200.0);
        transferDao.setFecha(LocalDate.now());

        when(repository.findById(1)).thenReturn(Optional.of(transferDao));

        // Act
        Transfer result = service.getTransfer(1);

        // Assert
        assertNotNull(result);
        assertEquals(1001, result.getNumeroCuentaCliente());
        assertEquals(200.0, result.getMonto());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void transferList_ShouldReturnTransferList() {
        // Arrange
        TransferDao transferDao = new TransferDao();
        transferDao.setId(1);
        transferDao.setNumeroCuentaCliente(1001);
        transferDao.setNumeroCuentaDestino(1002);
        transferDao.setMonto(200.0);
        transferDao.setFecha(LocalDate.now());

        when(repository.findAll()).thenReturn(List.of(transferDao));

        // Act
        List<Transfer> result = service.transferList();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1001, result.get(0).getNumeroCuentaCliente());
        verify(repository, times(1)).findAll();
    }
}
