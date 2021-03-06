package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IAccountRepository;

public class AccountService implements IAccountService {

		@Inject
		private IAccountRepository repo;

		@Override
		public String getAllAccounts() {
			return repo.getAllAccounts();
		}
		
		@Override
		public String getAccount(Long id) {
			return repo.getAccount(id);
		}

		@Override
		public String createAccount(String accountJSON) {
			return repo.createAccount(accountJSON);
		}
		
		@Override
		public String updateAccount(String accountUpdate) {
			return repo.updateAccount(accountUpdate);
		}
		
		@Override
		public String deleteAccount(Long id) {
			return repo.deleteAccount(id);
		}
}
