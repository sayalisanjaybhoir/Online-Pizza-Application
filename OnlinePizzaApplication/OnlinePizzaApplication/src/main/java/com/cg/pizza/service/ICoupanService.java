package com.cg.pizza.service;

import java.util.*;
import com.cg.pizza.entity.Coupan;
import com.cg.pizza.exception.CoupanIdNotFoundException;
import com.cg.pizza.exception.InvalidCoupanOperationException;

public interface ICoupanService {
	public Coupan addCoupans(Coupan coupan);

	public Coupan editCoupans(Coupan coupan)throws InvalidCoupanOperationException;

	public Coupan deleteCoupans(int coupanId)throws CoupanIdNotFoundException;

	public List<Coupan> viewCoupans();
}
