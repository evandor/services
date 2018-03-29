package io.skysail.service.monitor.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URL;

@Entity
@Getter
@Setter
public class Monitor {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long monitor_id;

    private String name;

	private URL url;

}
