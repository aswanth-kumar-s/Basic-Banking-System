package com.example.BasicBankingSystem.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BasicBankingSystem.Entity.HistoryEntity;
import com.example.BasicBankingSystem.Entity.UserEntity;
import com.example.BasicBankingSystem.Repository.HistoryRepository;
import com.example.BasicBankingSystem.Repository.UserRepository;

@Service
public class BBSServiceImpl implements BBSService {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private HistoryRepository historyrepo;
	
	@Override
	public List<UserEntity> getUser(){
		return (List<UserEntity>) userrepo.findAll();
	}

	@Override
	public List<HistoryEntity> getHistory() {
		return (List<HistoryEntity>) historyrepo.findAll();
	}

	@Override
	public String getById(int id) {
		String optional = userrepo.findById(id).get().getEmail();
		return optional;
	}
	
	@Override
	public List<String> getEmail(){
		return ((List<UserEntity>) userrepo.findAll()).stream().map(UserEntity::getEmail).collect(Collectors.toList());
	}

	@Override
	public boolean saveHistory(HistoryEntity history) {
		List<UserEntity> fromUser=userrepo.findByEmail(history.getFromMail());
		
		if(history.getAmount()<=fromUser.get(0).getBalance()) {
			historyrepo.save(history);
			
	        fromUser.get(0).setBalance(fromUser.get(0).getBalance()-history.getAmount());
	        userrepo.saveAll(fromUser);
	        
	        List<UserEntity> toUser=userrepo.findByEmail(history.getToMail());
			toUser.get(0).setBalance(toUser.get(0).getBalance()+history.getAmount());
			userrepo.saveAll(toUser);
			
			return true;
	    }
		else
			return false;
		
	}
	
}
