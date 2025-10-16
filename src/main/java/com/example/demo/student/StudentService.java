package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists");
        }
        studentRepository.save(student);
    }

    public void DeleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalArgumentException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void UpdateStudent(Long studentId, String email, String name) {
        boolean exists = studentRepository.existsById(studentId);
        if (exists) {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            if (studentOptional.get().getName().equals(name)) {
                throw new IllegalArgumentException("Student name is the same");
            } else {
                if (name != null && !name.isEmpty()) {
                    studentOptional.get().setName(name);
                }
            }
            if (studentOptional.get().getEmail().equals(email)) {
                throw new IllegalArgumentException("Student email is the same");
            } else if (email != null && !email.isEmpty()) {
                Optional<Student> studentOptional2 = studentRepository.findStudentByEmail(email);
                if (studentOptional2.isPresent()) {
                    throw new IllegalArgumentException("Student with email " + studentOptional2.get().getEmail() + " already exists");
                }
                studentOptional.get().setEmail(email);
            }
        }
    }

}
