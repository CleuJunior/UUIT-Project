package com.interion.uuit.services;

import com.interion.uuit.dto.DisciplineJson;
import com.interion.uuit.dto.StudentSummaryJson;
import com.interion.uuit.entities.Discipline;
import com.interion.uuit.entities.Student;
import com.interion.uuit.exceptions.DisciplineClosedException;
import com.interion.uuit.exceptions.FullCapaticyException;
import com.interion.uuit.exceptions.NotFoundException;
import com.interion.uuit.exceptions.StudentAlreadyRegisteredException;
import com.interion.uuit.mapper.DisciplineFactory;
import com.interion.uuit.mapper.StudentFactory;
import com.interion.uuit.repositories.DisciplineRepository;
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
public class DisciplineService implements CrudService<DisciplineJson> {

    private final DisciplineRepository repository;
    private final StudentRepository studentRepository;
    private final DisciplineFactory disciplineFactory;
    private final StudentFactory studentFactory;

    @Override
    public DisciplineJson insert(DisciplineJson request) {
        var discipline = repository.save(disciplineFactory.from(request));
        log.info("Disciplne {} saved", discipline);
        return disciplineFactory.from(discipline);
    }

    @Override
    public void update(String id, DisciplineJson request) {
        var discipline = this.findDisciplineOrThrow(id);

        discipline.setName(request.name());
        discipline.setStart(request.start());
        discipline.setEnd(request.end());
        discipline.setCapacity(request.capacity());
        discipline.setTotal(request.total());
        discipline.setOpen(request.open());
        discipline.setUpdateDate(LocalDateTime.now());

        repository.save(discipline);
        log.info("Discipline {} updated", discipline);
    }

    public void addStudentIntoDiscipline(String disciplineId, String studentId) {
        var discipline = this.findDisciplineOrThrow(disciplineId);
        var student = this.findStudentOrThrow(studentId);
        var studentSummary = studentFactory.studentSummary(student);

        this.checkValidations(discipline, studentSummary);

        discipline.addStudent(studentSummary);
        discipline.setTotal(discipline.getStudents().size());
        discipline.setUpdateDate(LocalDateTime.now());
        repository.save(discipline);
        log.info("Student {} added into discipline {}", studentSummary.name(), discipline.getName());
    }

    private void checkValidations(Discipline discipline, StudentSummaryJson student) {
        if (discipline.getStudents().contains(student)) {
            log.warn("Student {} already registered in discipline {}", student.name(), discipline.getName());
            throw new StudentAlreadyRegisteredException();
        }

        if (!discipline.isOpen()) {
            log.warn("Discipline {} is closed", discipline.getName());
            throw new DisciplineClosedException();
        }

        if (discipline.getStudents().size() == discipline.getCapacity()) {
            log.warn("Discipline full capacity of {}", discipline.getTotal());
            throw new FullCapaticyException();
        }
    }

    private Student findStudentOrThrow(String id) {
        var objId = new ObjectId(id);

        return studentRepository.findById(objId).orElseThrow(() -> {
            log.warn("Student {} not found", objId);
            throw new NotFoundException("Not Found");
        });
    }

    @Override
    public DisciplineJson findById(String id) {
        var discipline = this.findDisciplineOrThrow(id);
        log.info("Discipline found: {}", discipline);
        return disciplineFactory.from(discipline);
    }

    @Override
    public List<DisciplineJson> findAll() {
        var disciplines = repository.findAll();

        if (disciplines.isEmpty()) {
            log.warn("List of disciplines is empty");
        }

        log.info("List of disciplines return correctly");
        return disciplineFactory.from(disciplines);
    }

    @Override
    public Page<DisciplineJson> findAll(Pageable pageable) {
        Page<Discipline> disciplinesPage = repository.findAll(pageable);

        if (disciplinesPage.isEmpty()) {
            log.warn("Page of disciplines is empty");
        }

        log.info("Page of disciplines returned correctly");
        return disciplinesPage.map(disciplineFactory::from);
    }

    @Override
    public void delete(String id) {
        var discipline = this.findDisciplineOrThrow(id);
        repository.delete(discipline);
        log.info("Discipline {} deleted", discipline);
    }

    private Discipline findDisciplineOrThrow(String id) {
        var objId = new ObjectId(id);

        return repository.findById(objId).orElseThrow(() -> {
            log.warn("Discipline {} not found", id);
            throw new NotFoundException("Not Found");
        });
    }
}
