package io.skysail.service.um.repositories;

import io.skysail.service.um.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
