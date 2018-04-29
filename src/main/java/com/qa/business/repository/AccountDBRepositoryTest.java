package com.qa.business.repository;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

		
		@Before
		public void initialise() {
			jsonUtil = new JSONUtil();
			repo.setEntityManager(manager);
			repo.setUtil(jsonUtil);
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