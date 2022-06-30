package com.example.demo2;

import java.util.List;
import java.util.MissingResourceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class StudentService {
  private static final Logger logger = LogManager.getLogger(StudentService.class);

  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student addStudent(Student student) {
    Student savedStudent = studentRepository.save(student);
    return savedStudent;
  }

  @Cacheable(value = "students", key = "#email")
  public Student getStudentByEmail(String email) {
    logger.info("Reading from DB");
    return studentRepository.findByEmail(email)
        .orElseThrow(() -> new MissingResourceException("getStudentByEmail", "findByEmail", email));

  }
}
