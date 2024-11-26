package com.proyecto.ntt.transfer.repository;

import com.proyecto.ntt.transfer.repository.dao.TransferDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends MongoRepository<TransferDao, Integer> {
}
