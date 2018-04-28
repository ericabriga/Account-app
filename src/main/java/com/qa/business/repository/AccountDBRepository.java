package com.qa.business.repository;

import java.util.Collection;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;


import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

	@Transactional(SUPPORTS)
	public class AccountDBRepository implements IAccountRepository {

		private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);
		
		@PersistenceContext(unitName = "primary")
		private EntityManager manager;
		
		@Inject
		private JSONUtil util;
		
		@Override
		public String getAllAccounts() {
			LOGGER.info("AccountDBRepository getAllAccounts");
			Query query = manager.createQuery("Select a FROM Account a");
			Collection<Account> accounts = (Collection<Account>) query.getResultList();
			return util.getJSONForObject(accounts);
		}
		
		private Account findAccount(Long id) {
			return manager.find(Account.class, id);
		}
		
		@Override
		public String getAccount (Long id) {
		Account account = findAccount(id);
			if (account != null) 
			{
				return util.getJSONForObject(account); 
			}
			else {
				return "{\"message\":\"Account not found\"}";
			}
		}
	
		@Override
		@Transactional(REQUIRED)
		public String createAccount(String accountJSON) {
			Account account = util.getObjectForJSON(accountJSON, Account.class);
			manager.persist(account);
			return "{\"message\":\"Account created\"}";
		}

		@Override
		@Transactional(REQUIRED)
		public String updateAccount(String accountUpdate) {
			Account updatedAccount = util.getObjectForJSON(accountUpdate, Account.class);
			Account originalAccount = findAccount(updatedAccount.getId());
			if(originalAccount != null) {
				manager.merge(updatedAccount);
				return "{\"message\":\"Account updated\"}";
			}
			else {
				return "{\"message\":\"Account not found, cannot be updated\"}";
			}
		
		}
		
		@Override
		@Transactional(REQUIRED)
		public String deleteAccount(Long id) {
			Account accountToDelete = findAccount(id);
			if(accountToDelete != null) {
				manager.remove(accountToDelete);
				return "{\"message\":\"Account deleted\"}";
			}
			else {
				return "{\"message\":\"Account not found, cannot be deleted\"}";
			}
		}

}