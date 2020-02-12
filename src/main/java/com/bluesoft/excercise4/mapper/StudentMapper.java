package com.bluesoft.excercise4.mapper;

import com.bluesoft.excercise4.api.model.StudentDto;
import com.bluesoft.excercise4.model.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentMapper {

    public static Student toDomain(StudentDto studentDto) {
        Student result = new Student();
        result.setName(studentDto.getName());
        result.setPassportNumber(studentDto.getPassportNumber());
        return result;
    }

    public static StudentDto toDto(Student student) {
        StudentDto result = new StudentDto();
        result.setName(student.getName());
        result.setPassportNumber(student.getPassportNumber());
        return result;
    }

}
