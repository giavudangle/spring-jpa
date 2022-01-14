package com.selftaught.selftaught.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService(IStudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return this.studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        Optional<Student> isHasStudentWithEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(isHasStudentWithEmail.isPresent()){
            throw new IllegalStateException("Email already exist");
        }
        studentRepository.save(student);
    }
}
