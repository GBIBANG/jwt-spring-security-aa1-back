package org.gar;

import java.util.stream.Stream;

import org.gar.entites.AppRole;
import org.gar.entites.AppUser;
import org.gar.entites.Task;
import org.gar.repository.TaskRepository;
import org.gar.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtSecurityApplication implements CommandLineRunner{

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
		restConfiguration.exposeIdsFor(Task.class);

		accountService.saveUser(new AppUser(null, "admin", "1234", null));
		accountService.saveUser(new AppUser(null, "user", "1234", null));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUse("admin", "ADMIN");
		accountService.addRoleToUse("admin", "USER");
		accountService.addRoleToUse("user", "USER");

		
		Stream.of("T1", "T2", "T3").forEach(t->{
			taskRepository.save(new Task(null, t));
		});
		
		taskRepository.findAll().forEach(t-> {
			System.out.println(t.getTaskName());
		});
	}
	
	

}
