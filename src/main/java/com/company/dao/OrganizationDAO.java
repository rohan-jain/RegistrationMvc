package com.company.dao;

import java.util.List;

import com.company.model.Organization;

public interface OrganizationDAO {
	public boolean addOrganization(Organization organization);
	public List<Organization> displayOrganizations();
	
}
