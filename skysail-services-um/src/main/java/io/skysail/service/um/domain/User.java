package io.skysail.service.um.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;

@Entity
@Getter
@Setter
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;

    private String username; // email

    private String password;

}
