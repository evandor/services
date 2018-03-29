package hello;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Measurement {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    private String name;
	private Long duration;
	private Long timestamp;

}
