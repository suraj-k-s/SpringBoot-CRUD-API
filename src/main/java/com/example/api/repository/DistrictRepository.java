package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
	
	
	@Query(value = "SELECT district_id, district_name FROM public.tbl_district where district_name = :name",nativeQuery = true)       // using @query
    List<District> findByName(String name);

}
