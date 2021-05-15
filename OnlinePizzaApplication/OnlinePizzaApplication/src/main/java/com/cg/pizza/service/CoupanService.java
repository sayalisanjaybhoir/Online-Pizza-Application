package com.cg.pizza.service;

import com.cg.pizza.entity.Coupan;
import com.cg.pizza.repository.CoupanRepository;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoupanService implements ICoupanService{
	@Autowired
	private CoupanRepository coupanRepository;
	
	public List<Coupan> viewCoupan()
	{
		List<Coupan> coupan = coupanRepository.findAll();
		return coupan;
	}
	
	public Coupan viewCoupan(int id) {
		Optional<Coupan> coupan = coupanRepository.findById(id);
		return (coupan.isPresent()) ? coupan.get() : null;
	}
	
	public Coupan deleteCoupan(int id) {
		Coupan coupan = viewCoupan(id);
		if (coupan!= null)
			coupanRepository.deleteById(id);
		return coupan;
	}
	
	public Coupan editCoupan(Coupan coupan) {
	    Coupan coupanexist = viewCoupan(coupan.getCoupanId());
		if (coupanexist != null) {
			coupan = coupanRepository.save(coupan);
		}
		return coupan;
	}


	public Coupan insertCoupan(Coupan coupan) {
		Coupan coupanexist = viewCoupan(coupan.getCoupanId());
		if (coupanexist == null) {
			coupan = coupanRepository.save(coupan);
		}
		return coupan;
	}
}
