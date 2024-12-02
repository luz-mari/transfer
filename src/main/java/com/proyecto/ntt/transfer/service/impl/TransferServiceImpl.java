package com.proyecto.ntt.transfer.service.impl;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.repository.TransferRepository;
import com.proyecto.ntt.transfer.repository.dao.TransferDao;
import com.proyecto.ntt.transfer.service.TransferService;
import com.proyecto.ntt.transfer.service.dto.Movimiento;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class TransferServiceImpl implements TransferService {
    public TransferServiceImpl(TransferRepository repository, WebClient webClient){
        this.repository = repository;
        this.webClient = webClient;

    }

    private final TransferRepository repository;
    private final WebClient webClient;



    @Override
    public List<Transfer> transferList() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(transferDao -> {
                    Transfer transfer = new Transfer();
                    transfer.setId(transferDao.getId());
                    transfer.setFecha(transferDao.getFecha());
                    transfer.setMonto(transferDao.getMonto());
                    transfer.setNumeroCuentaCliente(transferDao.getNumeroCuentaCliente());
                    transfer.setNumeroCuentaDestino(transferDao.getNumeroCuentaDestino());
                    return transfer;


                }).toList();
    }

    @Override
    public Mono<Transfer> createTransfer(Transfer transfer) {
        var deposit= new Movimiento();
        var withdrawal = new Movimiento();
        deposit.setDescripcion("deposito");
        deposit.setNumero_cuenta(transfer.getNumeroCuentaDestino());
        deposit.setFecha(transfer.getFecha());
        deposit.setMonto(transfer.getMonto());
        withdrawal.setDescripcion("retiro");
        withdrawal.setNumero_cuenta(transfer.getNumeroCuentaCliente());
        withdrawal.setFecha(transfer.getFecha());
        withdrawal.setMonto(transfer.getMonto()*-1);

        var callwithdrawal = registrarMovimiento(withdrawal);

        return callwithdrawal.flatMap(movimiento -> {
            var calldeposit = registrarMovimiento(deposit);
            return calldeposit;
        }).map(movimiento -> {
            var transferDao = new TransferDao();
            transferDao.setFecha(transfer.getFecha());
            transferDao.setMonto(transfer.getMonto());
            transferDao.setNumeroCuentaDestino(transfer.getNumeroCuentaDestino());
            transferDao.setNumeroCuentaCliente(transfer.getNumeroCuentaCliente());
            var transferRegistrada = repository.save(transferDao);
            Transfer transferdto = new Transfer();
            transferdto.setId(transferRegistrada.getId());
            transferdto.setFecha(transferRegistrada.getFecha());
            transferdto.setMonto(transferRegistrada.getMonto());
            transferdto.setNumeroCuentaCliente(transferRegistrada.getNumeroCuentaCliente());
            transferdto.setNumeroCuentaDestino(transferRegistrada.getNumeroCuentaDestino());
            return (transferdto);
        });
    }

    @Override
    public Transfer getTransfer(Integer id) {
        var transferFound = repository.findById(id);
        if (transferFound.isPresent()) {
            var transfer = new Transfer();
            var transferDao = transferFound.get();
            transfer.setId(transferDao.getId());
            transfer.setFecha(transferDao.getFecha());
            transfer.setMonto(transferDao.getMonto());
            transfer.setNumeroCuentaCliente(transferDao.getNumeroCuentaCliente());
            transfer.setNumeroCuentaDestino(transferDao.getNumeroCuentaDestino());
            return transfer;
        } else {
            return null;
        }
    }

    public Mono<Movimiento> registrarMovimiento(Movimiento movimiento) {
        return webClient.post()
                .uri("/movimientos") // La URL base ya está configurada
                .body(Mono.just(movimiento), Movimiento.class) // Cuerpo de la solicitud
                .retrieve() // Procesa la respuesta
                .bodyToMono(Movimiento.class); // Convierte la respuesta al tipo Movimiento
    }
}


