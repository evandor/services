package io.skysail.service.monitor.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Monitor {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    private String name;

	private URL url;

	@OneToMany(mappedBy = "monitor", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private List<Measurement> reviews = new ArrayList<>();

}
