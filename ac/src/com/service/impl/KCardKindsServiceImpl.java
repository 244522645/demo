package com.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.KCardKinds;
import com.service.KCardKindsService;
@Service
public class KCardKindsServiceImpl extends BaseService implements KCardKindsService {

	@Override
	public List<KCardKinds> findall() {
		return super.getkCardKindsMapper().findall();
	}

}
