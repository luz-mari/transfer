package com.proyecto.ntt.transfer.controller;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferControllerTest {

    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    private Transfer transfer;

    @BeforeEach
    void setUp() {
        // Inicializar mocks
        MockitoAnnotations.openMocks(this);

        // Crear un objeto Transfer de ejemplo
        transfer = new Transfer();
        transfer.setId(1);
        transfer.setNumeroCuentaCliente(123);
        transfer.setNumeroCuentaDestino(456);
        transfer.setMonto(100.0);
        transfer.setFecha(LocalDate.now());
    }

    @Test
    void testTransferList() {
        // Mockear la respuesta del servicio
        when(transferService.transferList()).thenReturn(List.of(transfer));

        // Llamar al método del controlador
        List<Transfer> result = transferController.transferList();

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(123, result.get(0).getNumeroCuentaCliente());

        // Verificar que el servicio fue llamado
        verify(transferService).transferList();
    }

    @Test
    void testCreateTransfer() {
        // Mockear la respuesta del servicio
        when(transferService.createTransfer(any(Transfer.class))).thenReturn(Mono.just(transfer));

        // Llamar al método del controlador
        Mono<Transfer> result = transferController.createTransfer(transfer);

        // Verificar el resultado
        assertNotNull(result);
        result.subscribe(res -> {
            assertEquals(1, res.getId());
            assertEquals(123, res.getNumeroCuentaCliente());
        });

        // Verificar que el servicio fue llamado
        verify(transferService).createTransfer(any(Transfer.class));
    }

    @Test
    void testGetTransfer() {
        // Mockear la respuesta del servicio
        when(transferService.getTransfer(1)).thenReturn(transfer);

        // Llamar al método del controlador
        Transfer result = transferController.getTransfer(1);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(123, result.getNumeroCuentaCliente());

        // Verificar que el servicio fue llamado
        verify(transferService).getTransfer(1);
    }

    @Test
    void testGetTransferNotFound() {
        // Mockear la respuesta del servicio para devolver null
        when(transferService.getTransfer(1)).thenReturn(null);

        // Llamar al método del controlador
        Transfer result = transferController.getTransfer(1);

        // Verificar el resultado
        assertNull(result);

        // Verificar que el servicio fue llamado
        verify(transferService).getTransfer(1);
    }
}
