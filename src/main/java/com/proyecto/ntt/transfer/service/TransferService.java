package com.proyecto.ntt.transfer.service;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TransferService {
    List<Transfer> transferList();
    Mono<Transfer> createTransfer(Transfer transfer);
    Transfer getTransfer (Integer id);


}
