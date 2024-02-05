package com.interion.uuit.services;

import com.interion.uuit.dto.DisciplineJson;
import com.interion.uuit.dto.StudentJson;
import com.interion.uuit.dto.StudentSummaryJson;
import com.interion.uuit.entities.Discipline;
import com.interion.uuit.entities.Student;
import com.interion.uuit.exceptions.DisciplineClosedException;
import com.interion.uuit.exceptions.FullCapaticyException;
import com.interion.uuit.exceptions.NotFoundException;
import com.interion.uuit.exceptions.StudentAlreadyRegisteredException;
import com.interion.uuit.mapper.StudentFactory;
import com.interion.uuit.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService implements CrudService<StudentJson> {

    private final StudentRepository repository;
    private final StudentFactory studentFactory;

    @Override
    public StudentJson insert(StudentJson request) {
        var student = repository.save(studentFactory.from(request));
        log.info("Student {} saved", student);
        return studentFactory.from(student);
    }

    @Override
    public void update(String id, StudentJson request) {
        var student = this.findStudentOrThrow(id);

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setRegistration(request.registration());
        student.setUpdateDate(LocalDateTime.now());

        repository.save(student);
        log.info("Student {} updated", student);
    }

    @Override
    public StudentJson findById(String id) {
        var discipline = this.findStudentOrThrow(id);
        log.info("Student found: {}", discipline);
        return studentFactory.from(discipline);
    }

    @Override
    public List<StudentJson> findAll() {
        var students = repository.findAll();

        if (students.isEmpty()) {
            log.warn("List of students is empty");
        }

        log.info("List of students return correctly");
        return studentFactory.from(students);
    }

    @Override
    public Page<StudentJson> findAll(Pageable pageable) {
        Page<Student> studentPage = repository.findAll(pageable);

        if (studentPage.isEmpty()) {
            log.warn("Page of students is empty");
        }

        log.info("Page of students returned correctly");
        return studentFactory.from(studentPage);
    }

    @Override
    public void delete(String id) {
        var student = this.findStudentOrThrow(id);
        repository.delete(student);
        log.info("Student {} deleted", student);
    }

    private Student findStudentOrThrow(String id) {
        var objId = new ObjectId(id);

        return repository.findById(objId).orElseThrow(() -> {
            log.warn("Student {} not found", id);
            throw new NotFoundException("Not Found");
        });
    }
}
