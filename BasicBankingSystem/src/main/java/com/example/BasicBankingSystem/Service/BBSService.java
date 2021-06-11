package com.example.BasicBankingSystem.Service;

import java.util.List;
import com.example.BasicBankingSystem.Entity.HistoryEntity;
import com.example.BasicBankingSystem.Entity.UserEntity;

public interface BBSService {
     List<UserEntity> getUser();
     String getById(int id);
     List<String> getEmail();
     List<HistoryEntity> getHistory();
     boolean saveHistory(HistoryEntity history);
}
