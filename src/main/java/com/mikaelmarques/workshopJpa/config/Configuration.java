package com.mikaelmarques.workshopJpa.config;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.enums.ExpensesEnum;
import com.mikaelmarques.workshopJpa.enums.RevenueEnum;
import com.mikaelmarques.workshopJpa.repositories.ExpensesRepository;
import com.mikaelmarques.workshopJpa.repositories.RevenueRepository;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;

import jakarta.transaction.Transactional;

@org.springframework.context.annotation.Configuration
@Profile("dev")
public class Configuration implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RevenueRepository revenueRepository;
	
	@Autowired
	private ExpensesRepository expensesRepository;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
	        
		User user1 = new User(null, "Josias", "josias@gmail.com", "123456");
		userRepository.save(user1);
		
		Revenue revenue1 = new Revenue(null, 4500.00, "Salário do mês", LocalDate.now(), RevenueEnum.SALARY ,user1);
		Revenue revenue2 = new Revenue(null, 1500.00, "Extra com freelancer", LocalDate.now(), RevenueEnum.SALARY ,user1);
		revenueRepository.save(revenue1);
		revenueRepository.save(revenue2);
		
		Expenses expense1 = new Expenses(null, 800.00, "Aluguel", LocalDate.now(), ExpensesEnum.HOUSING, user1);
		Expenses expense2 = new Expenses(null, 200.00, "Plano de saúde", LocalDate.now(), ExpensesEnum.HEALTH, user1);
		Expenses expense3 = new Expenses(null, 1200.00, "Despesas alimentar", LocalDate.now(), ExpensesEnum.FOOD, user1);
		Expenses expense4 = new Expenses(null, 900.00, "Lazer com esposa e filhos", LocalDate.now(), ExpensesEnum.LEISURE, user1);
		expensesRepository.save(expense1);
		expensesRepository.save(expense2);
		expensesRepository.save(expense3);
		expensesRepository.save(expense4);
		
		User user2 = new User(null, "José", "jose@gmail.com", "123456");
		userRepository.save(user2);
		
		Revenue revenue3 = new Revenue(null, 7500.00, "Salário do mês", LocalDate.now(), RevenueEnum.SALARY ,user2);
		revenueRepository.save(revenue3);
		
		
		Expenses expense6 = new Expenses(null, 200.00, "Plano de saúde", LocalDate.now(), ExpensesEnum.HEALTH, user2);
		Expenses expense7 = new Expenses(null, 1200.00, "Despesas alimentar", LocalDate.now(), ExpensesEnum.FOOD, user2);
		Expenses expense8 = new Expenses(null, 900.00, "Lazer com esposa e filhos", LocalDate.now(), ExpensesEnum.LEISURE, user2);
		expensesRepository.save(expense6);
		expensesRepository.save(expense7);
		expensesRepository.save(expense8);
		
		User user3 = new User(null, "Erinaldo", "erinaldo@gmail.com", "123456");
		userRepository.save(user3);
		
		Revenue revenue4 = new Revenue(null, 2550.00, "Salário do mês", LocalDate.now(), RevenueEnum.SALARY ,user3);
		revenueRepository.save(revenue4);
		
		Expenses expense10 = new Expenses(null, 200.00, "Plano de saúde", LocalDate.now(), ExpensesEnum.HEALTH, user3);
		Expenses expense11 = new Expenses(null, 1200.00, "Despesas alimentar", LocalDate.now(), ExpensesEnum.FOOD, user3);
		Expenses expense12 = new Expenses(null, 900.00, "Lazer com esposa e filhos", LocalDate.now(), ExpensesEnum.LEISURE, user3);
		expensesRepository.save(expense10);
		expensesRepository.save(expense11);
		expensesRepository.save(expense12);
		
		
	}

}
