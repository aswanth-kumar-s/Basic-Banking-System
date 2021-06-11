package com.example.BasicBankingSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.BasicBankingSystem.Entity.HistoryEntity;
import com.example.BasicBankingSystem.Service.BBSService;

@RequestMapping("/BBS")
@Controller
public class MainController {
	
	  @Autowired
	  private BBSService bbs; 
	  
	  @GetMapping("/home")
	  public String viewHomePage(Model model) {
		  return "Home";
	  }
	  
	  @GetMapping("/viewUser")
	  public String viewUser(Model model) {
		  model.addAttribute("listUser", bbs.getUser());
		  return "User";
	  }
	  
	  @GetMapping("/viewHistory")
	  public String viewTransaction(Model model) {
		  model.addAttribute("listHistory", bbs.getHistory());
		  return "History";
	  }
	  
	  @GetMapping("/viewUser/sendMoney/{id}")
	  public String sendMoney(@PathVariable (value = "id" ) int id, Model model) {
		  model.addAttribute("fromId", id);
		  model.addAttribute("fromUser", bbs.getById(id));
		  model.addAttribute("listUser", bbs.getEmail());
		  return "sendMoney";
	  }
	  
	  @GetMapping("/home/aboutUs")
	  public String aboutUs() {
		  return "AboutUs";
	  }
	  
	  @PostMapping("/history/{fromUser}&{toUser}&{money}")
	  public String history(HistoryEntity history,Model model) {
		  if(bbs.saveHistory(history)) {
		  return "redirect:/BBS/viewHistory";
		  }
		  else {
			   model.addAttribute("error", "You don't have Rs."+history.getAmount()+" in your account to send!");
		       return "error";
		  }
	  }
	  
}