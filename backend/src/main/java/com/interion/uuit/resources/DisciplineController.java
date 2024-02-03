package com.interion.uuit.resources;

import com.interion.uuit.dto.DisciplineJson;
import com.interion.uuit.services.DisciplineService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService service;

    @GetMapping
    public ResponseEntity<List<DisciplineJson>> listOfDisciplines() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping(params = {"page", "size", "sort"})
    public ResponseEntity<Page<DisciplineJson>> listOfDisciplinesPage(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        var pageable = PageRequest.of(page, size, Sort.by(sort));
        return ResponseEntity.ok(this.service.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DisciplineJson> getDisciplineById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DisciplineJson> saveDiscipline(@Valid @RequestBody DisciplineJson request) {
        var response = this.service.insert(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateDiscipline(@PathVariable String id, @Valid @RequestBody DisciplineJson request) {
        this.service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}/student/{studentId}")
    public ResponseEntity<?> addStudentOnDicipline(@PathVariable String id, @PathVariable String studentId) {
        this.service.addStudentIntoDiscipline(id, studentId);
        var message = Map.of("message", "student add successfully");

        return ResponseEntity.ok(message);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
