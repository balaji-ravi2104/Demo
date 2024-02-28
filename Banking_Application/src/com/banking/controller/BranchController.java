package com.banking.controller;

import com.banking.dao.BranchDao;
import com.banking.utils.CustomException;

public class BranchController {

	private final BranchDao branchDao;

	public BranchController(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	

	public boolean isBranchExists(int branchId) throws CustomException {
		boolean isBranchExists = false;
		try {
			isBranchExists = branchDao.checkBranchIdExists(branchId);
		}catch (Exception e) {
			throw new CustomException("Error while Checking Branch Details!!",e);
		}
		return isBranchExists;
		
	}

}
