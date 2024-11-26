package com.proyecto.ntt.transfer.service.impl;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class TransferServiceImpl implements TransferService {
    @Override
    public List<Transfer> transferList() {
        return List.of();
    }

    @Override
    public Mono<Transfer> createTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public Transfer getTransfer(Integer id) {
        return null;
    }
}
