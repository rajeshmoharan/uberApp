package org.spring.demo.uberapp.repositories;

import org.spring.demo.uberapp.entities.Rider;
import org.spring.demo.uberapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
    Optional<Rider> findByUser(User user);
}
