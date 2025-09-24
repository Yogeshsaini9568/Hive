package com.hive.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hive.models.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, String>  {

}
