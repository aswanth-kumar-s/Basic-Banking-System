package com.example.BasicBankingSystem.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.BasicBankingSystem.Entity.HistoryEntity;

@Repository
public interface HistoryRepository extends CrudRepository<HistoryEntity, Integer>{}
