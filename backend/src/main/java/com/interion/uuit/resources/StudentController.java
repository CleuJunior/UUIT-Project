package com.interion.uuit.resources;

import com.interion.uuit.dto.StudentRequest;
import com.interion.uuit.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentRequest>> listOfStudents() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<Page<StudentRequest>> listOfStudentsPage(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        var pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentRequest> getStudentById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentRequest> saveStudent(@Valid @RequestBody StudentRequest request) {
        var response = this.service.insert(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable String id, @Valid @RequestBody StudentRequest request) {
        this.service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
