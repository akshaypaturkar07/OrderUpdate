package com.mydomain.OrderDetails.dao;

import com.mydomain.OrderDetails.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {


}

