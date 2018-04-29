package com.qa.business.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {
		
		@InjectMocks 
		private AccountDBRepository repo;
		
		@Mock
		private EntityManager manager;
		
		@Mock 
		Query query;
		
		@Mock
		Account account = new Account("Eri", "Bri", "123");
		
		private JSONUtil jsonUtil;
		
		private static final String ACCOUNT_AS_JSON="{\"firstName\":\"Eri\",\"secondName\":\"Bri\",\"accountNumber\":123}";
		private static final String ACCOUNT_AS_ARRAY = "[{\"firstName\":\"Eri\",\"secondName\":\"Bri\",\"accountNumber\":\"1234\"}]";
		
		@Before
		public void initialise() {
			jsonUtil = new JSONUtil();
			repo.setEntityManager(manager);
			repo.setUtil(jsonUtil);
		}
		
		@Test
		public void testGetAllAccounts() {
			Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
			List<Account> accounts = new ArrayList<Account>();
			accounts.add(jsonUtil.getObjectForJSON("{\"firstName\":\"Eri\",\"secondName\":\"Bri\",\"accountNumber\":\"1234\"}", Account.class));
			Mockito.when(query.getResultList()).thenReturn(accounts);
			Assert.assertEquals(ACCOUNT_AS_ARRAY, repo.getAllAccounts());
		}
		
		@Test
		public void testCreateAccount() {
			String expectedResult=repo.createAccount(ACCOUNT_AS_JSON);
			assertEquals(expectedResult,"{\"message\":\"Account created\"}");		
		}
		
		@Test
		public void testUpdateAccount() {
			String expectedResult=repo.updateAccount(ACCOUNT_AS_JSON);
			assertEquals(expectedResult,"{\"message\":\"Account not found, cannot be updated\"}");
		}
		
		@Test
		public void testDeleteAccount() {
			String expectedResult=repo.deleteAccount(1L);
			assertEquals(expectedResult,"{\"message\":\"Account not found, cannot be deleted\"}");
		}
		
}