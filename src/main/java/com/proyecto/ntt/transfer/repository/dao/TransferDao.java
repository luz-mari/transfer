package com.proyecto.ntt.transfer.repository.dao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Document(collection="transfers")
public class TransferDao {
    @Id
    private Integer id= UUID.randomUUID().hashCode();
    @NotNull
    private Integer numeroCuentaCliente;
    @NotNull
    private Integer numeroCuentaDestino;
    @NotNull
    private double monto;
    @NotNull
    private LocalDate fecha;
}
