package com.hotel.booking.Repository;



import com.hotel.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
User save(User user);

}



/*
package com.hotel.booking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository {

}
 */