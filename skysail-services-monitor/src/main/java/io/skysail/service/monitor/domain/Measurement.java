package io.skysail.service.monitor.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Measurement {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "id")
    private Long monitor;

    private String name;
	private Long duration;
	private Long timestamp;
	private ConnectionResult result;

}
