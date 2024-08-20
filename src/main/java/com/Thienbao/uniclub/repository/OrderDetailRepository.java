package com.Thienbao.uniclub.repository;

import com.Thienbao.uniclub.model.OrderDetail;
import com.Thienbao.uniclub.model.key.OrderDetailID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailID> {
   List<OrderDetail> findAllByOrderId(int idOrder);

}
