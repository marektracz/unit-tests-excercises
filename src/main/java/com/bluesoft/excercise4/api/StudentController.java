package com.bluesoft.excercise4.api;

import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<StudentDto> retrieveAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/students/{id}")
    public StudentDto retrieveStudent(@PathVariable long id) {
        return studentService.get(id);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody StudentDto student) {
        long studentId = studentService.create(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studentId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Void> updateStudent(@RequestBody StudentDto student, @PathVariable long id) {
        studentService.update(id, student);
        return ResponseEntity.ok().build();
    }
}
