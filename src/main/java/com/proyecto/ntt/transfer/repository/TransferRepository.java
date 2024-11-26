package com.proyecto.ntt.transfer.repository;

import com.proyecto.ntt.transfer.repository.dao.TransferDao;
import org.springframework.data.repository.CrudRepository;

public interface TransferRepository extends CrudRepository<TransferDao, Integer> {
}
