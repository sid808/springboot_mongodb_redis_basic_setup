package com.example.demo2;

import java.io.Serializable;
import lombok.Data;

@Data
public class Address implements Serializable {
  private static final long serialVersionUID = 1234567890L;

  private String country;
  private String city;
  private String postCode;

}
