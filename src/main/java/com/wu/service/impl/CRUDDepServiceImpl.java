package com.wu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.Dao.CRUDDep;
import com.wu.domain.Department;
import com.wu.service.inter.CRUDDepService;
@Service("cruddepServiceImpl")
public class CRUDDepServiceImpl implements CRUDDepService{
	@Autowired
    private CRUDDep cruddep;
	private Department dep;
	
	@Override
	public List<Department> FindAllDep() {
		// TODO Auto-generated method stub
		return cruddep.FindAllDep();
	}

}
