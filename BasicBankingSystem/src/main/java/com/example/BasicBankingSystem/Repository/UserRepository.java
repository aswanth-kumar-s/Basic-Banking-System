package com.example.BasicBankingSystem.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.BasicBankingSystem.Entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
	List<UserEntity> findByEmail(String email);
}
