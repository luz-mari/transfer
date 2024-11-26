package com.proyecto.ntt.transfer.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class Transfer {
    @NotNull
    private Integer id;
    @NotNull
    private Integer numeroCuentaCliente;
    @NotNull
    private Integer numeroCuentaDestino;
    @NotNull
    private double monto;
    @NotNull
    private LocalDate fecha;
}
