package com.mydomain.OrderDetails.dao;

import com.mydomain.OrderDetails.entity.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Integer> {

}
