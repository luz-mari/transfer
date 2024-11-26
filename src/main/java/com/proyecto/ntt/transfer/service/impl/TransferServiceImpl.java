package com.proyecto.ntt.transfer.service.impl;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.repository.TransferRepository;
import com.proyecto.ntt.transfer.repository.dao.TransferDao;
import com.proyecto.ntt.transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;


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
        return Mono.just(transferdto);
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
}