package com.example.demo2;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {

  private static final long serialVersionUID = 1234567L;
  @Id
  private String id;
  private String firstName;
  private String lastName;
  @Indexed(unique = true)
  private String email;
  private Gender gender;
  private Address address;
  private List<String> favouriteSubjects;
  private BigDecimal totalSpentInBooks;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss[.SSS]")
  private LocalDateTime createdAt = LocalDateTime.now();

}
