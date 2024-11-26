package com.proyecto.ntt.transfer.controller;

import com.proyecto.ntt.transfer.controller.dto.Transfer;
import com.proyecto.ntt.transfer.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping ("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService service;
    @GetMapping
    public List<Transfer> transferList(){
        return service.transferList();
    }
    @PostMapping
    public Mono<Transfer> createTransfer(@RequestBody @Valid Transfer transfer){
        return service.createTransfer(transfer);
    }

    @GetMapping("{id}")
    public Transfer getTransfer (@PathVariable Integer id){
        return service.getTransfer(id);
    }







}
