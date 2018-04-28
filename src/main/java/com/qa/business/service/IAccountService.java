package com.qa.business.service;

public interface IAccountService {
	
	String getAllAccounts();
	String getAccount(Long id);
	String createAccount(String accountJSON);
	String updateAccount(String accountUpdate);
	//String deleteAccount(Long id);

}
