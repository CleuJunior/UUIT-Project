package com.interion.uuit.services;

import com.interion.uuit.dto.DisciplineJsonRequest;
import com.interion.uuit.dto.DisciplineJsonResponse;
import com.interion.uuit.entities.Discipline;
import com.interion.uuit.entities.Student;
import com.interion.uuit.exceptions.DisciplineClosedException;
import com.interion.uuit.exceptions.FullCapaticyException;
import com.interion.uuit.exceptions.NotFoundException;
import com.interion.uuit.exceptions.StudentAlreadyRegisteredException;
import com.interion.uuit.mapper.DisciplineMapper;
import com.interion.uuit.repositories.DisciplineRepository;
import com.interion.uuit.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DisciplineService implements CrudService<DisciplineJsonRequest, DisciplineJsonResponse> {

    private final DisciplineRepository repository;
    private final StudentRepository studentRepository;
    private final DisciplineMapper mapper;

    @Override
    public DisciplineJsonResponse insert(DisciplineJsonRequest request) {
        var discipline = repository.save(mapper.from(request));
        log.info("Disciplne {} saved", discipline);
        return mapper.from(discipline);
    }

    @Override
    public void update(String id, DisciplineJsonRequest request) {
        var discipline = this.findDisciplineOrThrow(id);

        discipline.setName(request.name());
        discipline.setStart(request.start());
        discipline.setEnd(request.end());
        discipline.setCapacity(request.capacity());
        discipline.setTotal(request.total());
        discipline.setOpen(request.open());

        repository.save(discipline);
        log.info("Discipline {} updated", discipline);
    }

    public void addStudentIntoDiscipline(String disciplineId, String studentId) {
        var discipline = this.findDisciplineOrThrow(disciplineId);
        var student = this.findStudentOrThrow(studentId);

        this.checkValidations(discipline, student);

        discipline.addStudent(student);
        discipline.setTotal(discipline.getStudents().size());
        repository.save(discipline);
    }

    private void checkValidations(Discipline discipline, Student student) {
        if (discipline.getStudents().contains(student)) {
            log.warn("Student {} already registered in discipline {}", student.getName(), discipline.getName());
            throw new StudentAlreadyRegisteredException("Student already registered");
        }

        if (discipline.isOpen()) {
            log.warn("Discipline {} is closed", discipline.getName());
            throw new DisciplineClosedException("Discipline is no longer available");
        }

        if (discipline.getStudents().size() == discipline.getCapacity()) {
            log.warn("Discipline full capacity of {}", discipline.getTotal());
            throw new FullCapaticyException("This discipline is already at maximum capacity");
        }
    }

    private Student findStudentOrThrow(String id) {
        var objId = new ObjectId(id);

        return studentRepository.findById(objId).orElseThrow( () -> {
            log.warn("Student {} not found", objId);
            throw new NotFoundException("Not Found");
        });
    }

    @Override
    public DisciplineJsonResponse findById(String id) {
       var discipline = this.findDisciplineOrThrow(id);
       log.info("Discipline found: {}", discipline);
       return mapper.from(discipline);
    }

    @Override
    public List<DisciplineJsonResponse> findAll() {
        var disciplines = repository.findAll();

        if (disciplines.isEmpty()) {
            log.warn("List of disciplines is empty");
        }

        log.info("List of disciplines return correctly");
        return mapper.from(disciplines);
    }

    @Override
    public Page<DisciplineJsonResponse> findAll(Pageable pageable) {
        Page<Discipline> disciplinesPage = repository.findAll(pageable);

        if (disciplinesPage.isEmpty()) {
            log.warn("Page of disciplines is empty");
        }

        log.info("Page of disciplines returned correctly");
        return disciplinesPage.map(mapper::from);
    }

    @Override
    public void delete(String id) {
        var discipline = this.findDisciplineOrThrow(id);
        repository.delete(discipline);
        log.info("Discipline {} deleted", discipline);
    }

    private Discipline findDisciplineOrThrow(String id) {
        var objId = new ObjectId(id);

        return repository.findById(objId).orElseThrow( () -> {
            log.warn("Discipline {} not found", id);
            throw new NotFoundException("Not Found");

        });
    }
}
