package com.project.VehicleServiceManagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VehicleServiceManagement.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByEmail(String email);

}
