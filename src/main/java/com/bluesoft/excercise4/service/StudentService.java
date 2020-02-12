package com.bluesoft.excercise4.service;

import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.error.StudentNotFoundException;
import com.bluesoft.excercise4.mapper.StudentMapper;
import com.bluesoft.excercise4.model.Student;
import com.bluesoft.excercise4.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDto get(long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException("id-" + id));
    }

    @Transactional
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public long create(StudentDto studentDto) {
        Student student = studentRepository.save(StudentMapper.toDomain(studentDto));
        return student.getId();
    }

    @Transactional
    public void update(long id, StudentDto student) {
        Student oldStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("id-" + id));

        oldStudent.setName(student.getName());
        oldStudent.setPassportNumber(student.getPassportNumber());

        studentRepository.save(oldStudent);
    }
}
