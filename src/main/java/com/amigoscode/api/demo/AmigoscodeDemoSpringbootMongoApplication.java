package com.amigoscode.api.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AmigoscodeDemoSpringbootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigoscodeDemoSpringbootMongoApplication.class, args);
	}

	

	/**
	 *  
	 * @param studentRepository
	 * @param mongoTemplate
	 * @return
	 * 
	 * 
	 * This code was just for refernce
	 * 
	 *
	 * import org.springframework.data.mongodb.core.query.Criteria;
		import org.springframework.data.mongodb.core.query.Query; 
	
	private void getStudentsByMail(Student student, StudentRepository studentRepository, MongoTemplate mongoTemplate) 
			throws IllegalAccessException {
		Query query = new Query();
		String email = student.getEmail();
		query.addCriteria(Criteria.where("email").is(email));
		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalAccessException("found may students with email: " + email);
		}

		if (students.isEmpty()) {
			System.out.println("##### Insert student " + student);
			studentRepository.insert(student);
		} else {
			System.out.println("Student alredy exists");
		}
	}
	
	*/	 
	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("Greece", "Sparta", "300");

			String email = "sebastianbaltazarbox@mail.com";

			Student student = new Student("Sebastian", "De Esparta", email, Gender.MALE, address,
					List.of("Computer Science", "Java", "Python", "NodeJe"), BigDecimal.TEN, LocalDateTime.now());

			studentRepository.findStudentByEmail(email).ifPresentOrElse(s -> {
				System.out.println("Student already exists");
			}, () -> {
				studentRepository.insert(student);
			});

		};
	}

}
