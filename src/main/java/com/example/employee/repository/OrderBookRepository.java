package com.example.employee.repository;

import com.example.employee.model.OrderBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBuy, Long> {
//    @Query(value = " select * from order_buy b where b.")
    List<OrderBuy> findByUserBuyId(Long id);
}
