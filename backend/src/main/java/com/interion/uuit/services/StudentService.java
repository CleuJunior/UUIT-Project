package com.interion.uuit.services;

import com.interion.uuit.dto.StudentRequest;
import com.interion.uuit.entities.Student;
import com.interion.uuit.entities.User;
import com.interion.uuit.exceptions.NotFoundException;
import com.interion.uuit.mapper.StudentFactory;
import com.interion.uuit.repositories.StudentRepository;
import com.interion.uuit.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.interion.uuit.enums.Role.STUDENT;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class StudentService implements CrudService<Student> {

    private final StudentRepository studentRepository;
    private final StudentFactory studentFactory;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Student insert(Student request) {
        var student = studentRepository.save(request);
//        var passwordEncoded = passwordEncoder.encode(studentS.password());
//        var user = new User<>(studentS, studentS.getEmail(), passwordEncoded, STUDENT);

//        userRepository.save(user);

        log.info("Student {} saved", student);
        return student;

//        return studentFactory.from(studentS);
    }

    @Override
    public void update(String id, Student request) {
        var student = this.findStudentOrThrow(id);

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setRegistration(request.getRegistration());
        student.setUpdateDate(LocalDateTime.now());

        studentRepository.save(student);
        log.info("Student {} updated", student);
    }

    @Override
    public Student findById(String id) {
        var student = this.findStudentOrThrow(id);
        log.info("Student found: {}", student);
//        return studentFactory.from(discipline);
        return student;
    }

    @Override
    public List<Student> findAll() {
        var students = studentRepository.findAll();

        if (students.isEmpty()) {
            log.warn("List of students is empty");
        }

        log.info("List of students return correctly");
//        return studentFactory.from(students);
        return students;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        var studentPage = studentRepository.findAll(pageable);

        if (studentPage.isEmpty()) {
            log.warn("Page of students is empty");
        }

        log.info("Page of students returned correctly");
        return studentPage;
//        return studentFactory.from(studentPage);
    }

    @Override
    public void delete(String id) {
        var student = this.findStudentOrThrow(id);
        studentRepository.delete(student);
        log.info("Student {} deleted", student);
    }

    private Student findStudentOrThrow(String id) {
        var objId = new ObjectId(id);

        return studentRepository.findById(objId).orElseThrow(() -> {
            log.warn("Student {} not found", id);
            throw new NotFoundException("Not Found");
        });
    }
}
