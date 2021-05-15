package com.cg.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.cg.pizza.entity.Coupan;


@Repository
public interface CoupanRepository extends JpaRepository<Coupan, Integer>{
     
}
