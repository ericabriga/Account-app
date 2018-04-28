package com.qa.business.repository;

public interface IAccountRepository {
	
	String getAllAccounts();
	String getAccount(Long id);
	String createAccount(String accountJSON);
	String updateAccount(String accountUpdate);
	//String deleteAccount(Long id);
}
