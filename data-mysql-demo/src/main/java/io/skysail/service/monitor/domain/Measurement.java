package io.skysail.service.monitor.domain;


import io.skysail.service.monitor.hello.ConnectionResult;
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

    @ManyToOne
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;

    private String name;
	private Long duration;
	private Long timestamp;
	private ConnectionResult result;

}
