package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.IAccountService;

@Path("/account")
public class AccountEndPoint {
		//instance variable
		@Inject
		private IAccountService service;
		
		@GET
		@Path("/json")
		@Produces ("application/json")
		public String getAllAccounts() {
			return service.getAllAccounts();
		}
		
		@GET
		@Path("/json/{id}")
		@Produces({ "application/json" })
		public String getAccounts(@PathParam("id") Long id) {
			return service.getAccount(id);
		}
		
		@POST
		@Path("/json")
		@Produces({ "application/json" })
		public String createAccounts(String jsonAccount) {
			return service.createAccount(jsonAccount);
		}
		
}