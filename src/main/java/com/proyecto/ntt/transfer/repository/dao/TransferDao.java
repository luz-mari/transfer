package com.proyecto.ntt.transfer.repository.dao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection="transfers")
public class TransferDao {
    @Id
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
