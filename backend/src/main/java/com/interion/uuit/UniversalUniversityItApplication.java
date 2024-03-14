package com.interion.uuit;

import com.interion.uuit.entities.Attendant;
import com.interion.uuit.entities.User;
import com.interion.uuit.enums.Role;
import com.interion.uuit.repositories.AttendantRepository;
import com.interion.uuit.repositories.DisciplineRepository;
import com.interion.uuit.repositories.StudentRepository;
import com.interion.uuit.repositories.UserRepository;
import com.interion.uuit.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class UniversalUniversityItApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UniversalUniversityItApplication.class, args);
	}

	@Autowired
	DisciplineRepository repository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	DisciplineService service;

	@Autowired
	AttendantRepository attendantRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository repositoryUser;


	@Override
	public void run(String... args) {
//		var password = "12345678";
//		var user = User.builder()
//				.firstName("fernando")
//				.lastName("fernadis")
//				.email("fernan@gmail.com")
//				.role(Role.ADMIN)
//				.password(passwordEncoder.encode(password))
//				.build();
//
//		repositoryUser.save(user);



//		var password = passwordEncoder.encode("13855");
//		var password2 = passwordEncoder.encode("13855");
//		var password3 = passwordEncoder.encode("13855");
//		var list = List.of(
//			new Attendant("Juliana", "Lilas", "email1@gmail.com", "13855", password),
//			new Attendant("Fernanda", "Lupina", "email2@gmail.com", "13345", password2),
//			new Attendant("Jesica", "Tulipa", "email3@gmail.com", "13345", password3)
//
//		);
//
//		attendantRepository.saveAll(list);

//
//		studentRepository.deleteAll();
//		repository.deleteAll();
//
//		var listaStudent = List.of(
//				new Student(new ObjectId(), "Cleonildo", "Junior", "email@gmail.com", "331BA"),
//		 new Student(new ObjectId(), "Fernanda", "Beltrano", "bentrafe@gmail.com", "33FFA"),
//		new Student(new ObjectId(), "Patricia", "Alencar", "alencarpati@gmail.com", "33BFA"),
//		new Student(new ObjectId(), "Fernando", "Altum", "fefetumtum@gmail.com", "57BFA"),
//		new Student(new ObjectId(), "Hercules", "Snow", "snownow@gmail.com", "55BFA")
//		);
//
//		studentRepository.saveAll(listaStudent);
//
//		var listaDisciplinas = List.of(
//				new Discipline(new ObjectId(), "Matematica Generica 2", "10:15", "13:45", 10, 0, true),
//				new Discipline(new ObjectId(), "Matematica Generica 1", "15:35", "16:55", 15, 0, true),
//				new Discipline(new ObjectId(), "Introducao a Programacao Funcional", "18:15", "21:35", 25, 0, true)
//		);
//
//		repository.saveAll(listaDisciplinas);


//		var student = new Student("Fernando", "Castilho", "email@gmail.com", "331CA", repository.findAll());
//
//		studentRepository.save(studentWithouDisciplines);
//		studentRepository.save(student);
//
//
//		var disciplinOne = new DisciplineJson(
//				"Computational Mathematics",
//				LocalTime.of(19, 45).toString(),
//				LocalTime.of(21, 15).toString(),
//				30,
//				2,
//				true
//
//				);
//		var d

//		service.insert(disciplinOne);

//		repository.deleteAll();
//		studentRepository.deleteAll();

//		System.out.println(service.findAll());
	}
}
