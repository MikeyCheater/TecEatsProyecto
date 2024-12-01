package com.teceats.backend.repository;

import com.teceats.backend.model.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Long> {
    Optional<RestaurantOwner> findByEmail(String email);
}
