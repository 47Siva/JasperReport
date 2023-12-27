package com.sampleproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sampleproject.entity.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query(value = "select * from address a where a.id =:key or a.firstname=:key or a.lastname=:key or a.street =:key or a.city =:key",nativeQuery = true)
	List<Address> findBykey(Object key);

}
