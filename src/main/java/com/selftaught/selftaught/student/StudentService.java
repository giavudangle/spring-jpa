package com.selftaught.selftaught.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        Optional<Student> isHasStudentWithEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (isHasStudentWithEmail.isPresent()) {
            throw new IllegalStateException("Email already exist");
        }
        studentRepository.save(student);
    }

    public void removeStudentById(Long id) {
        boolean isStudentExist = this.studentRepository.existsById(id);
        if (!isStudentExist) {
            throw new IllegalStateException("Student with id " + id + " does not exists");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student with id " + studentId + " does not exists")
        );
        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email already exist");
            }
            student.setEmail(email);
        }

    }
}
