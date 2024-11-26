package com.proyecto.ntt.transfer.service.impl;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.repository.TransferRepository;
import com.proyecto.ntt.transfer.repository.dao.TransferDao;
import com.proyecto.ntt.transfer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;

    public TransferServiceImpl(TransferRepository repository) {
        this.repository = repository;
    }

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
        return null;
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