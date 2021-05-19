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
	
	public List<Coupan> viewCoupans()
	{
		List<Coupan> coupan = coupanRepository.findAll();
		return coupan;
	}
	
	public Coupan viewCoupans(int id) {
		Optional<Coupan> coupan = coupanRepository.findById(id);
		return (coupan.isPresent()) ? coupan.get() : null;
	}
	
	public Coupan deleteCoupans(int id) {
		Coupan coupan = viewCoupans(id);
		if (coupan!= null)
			coupanRepository.deleteById(id);
		return coupan;
	}
	
	public Coupan editCoupans(Coupan coupan) {
	    Coupan coupanexist = viewCoupans(coupan.getCoupanId());
		if (coupanexist != null) {
			coupan = coupanRepository.save(coupan);
		}
		return coupan;
	}


	public Coupan addCoupans(Coupan coupan) {
		Coupan coupanexist = viewCoupans(coupan.getCoupanId());
		if (coupanexist == null) {
			coupan = coupanRepository.save(coupan);
		}
		return coupan;
	}
}
