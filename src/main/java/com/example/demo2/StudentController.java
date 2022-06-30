package com.example.demo2;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

  @Autowired
  private final StudentService studentService;

  @GetMapping
  public List<Student> fetchAllStudents(){
    return studentService.getAllStudents();
  }

  @GetMapping("/email/{email}")
  public Student fetchStudentByEmail(@PathVariable("email") String email){
    log.info("Inside fetchStudentByEmail::{}",email);
    return studentService.getStudentByEmail(email);
  }

  @PostMapping
  public Student addStudent(@RequestBody Student student){
    Student savedStudent = studentService.addStudent(student);
    return savedStudent;
  }

}
