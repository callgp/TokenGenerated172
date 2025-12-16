package com.telusko.springsecdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;


@Data
@Table(name = "users")
@Entity
public class User {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	private String username;
	private String password;
	
}
