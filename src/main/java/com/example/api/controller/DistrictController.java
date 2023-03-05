package com.example.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.District;
import com.example.api.repository.DistrictRepository;

@RestController
public class DistrictController {
	
	
	@Autowired
	private DistrictRepository districtRepository;
		
	
	
	//save District
	@PostMapping("/district")
	public District createDistrict(@RequestBody District district)
	{
		return this.districtRepository.save(district);
	}
	
	
	
	//get District
	@GetMapping("/district")
	public List<District> selectDistrict()
	{
		return this.districtRepository.findAll();
	}
	
	
	
	//get District by Id
	@GetMapping("/district/{id}")
	public Optional<District> selectDistrictById(@PathVariable(value = "id") Long districtId)
	{
		return this.districtRepository.findById(districtId);
	}
	
	
	
	//get District by Name using Get
	@GetMapping("/districts/{name}")
	public List<District> selectDistrictByName(@PathVariable(value = "name") String districtName)
	{
		return this.districtRepository.findByName(districtName);
	}
	
	
	
	//get District by Name using POST
		@PostMapping("/districts")
		public List<District> selectDistrictName(@RequestBody District districtName)
		{
			
			String dis = districtName.getDistrictName();
			
			return this.districtRepository.findByName(dis);
		}
	
	
	
	//delete District
	@DeleteMapping("/district/{id}")
	public Map<String, Boolean> deleteDistrict(@PathVariable(value = "id") Long districtId)
	{
		Optional<District> district = districtRepository.findById(districtId);
		
		District _district = district.get();
		
		this.districtRepository.delete(_district);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}
	
	
	
	//update District
	@PutMapping("/district/{id}")
	public ResponseEntity<District> updateDistrict(@PathVariable(value = "id") Long districtId,@RequestBody District districtDetails)
	{
		Optional<District> district = districtRepository.findById(districtId);
		
		
			District _district = district.get();
			_district.setDistrictName(districtDetails.getDistrictName());
			return ResponseEntity.ok(this.districtRepository.save(_district));
				
	}
	
	

}
