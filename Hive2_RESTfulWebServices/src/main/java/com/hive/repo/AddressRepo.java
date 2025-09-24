package com.hive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hive.models.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
