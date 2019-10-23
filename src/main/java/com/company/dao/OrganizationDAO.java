package com.company.dao;

import java.util.List;

import com.company.model.Organization;

public interface OrganizationDAO {
	public boolean deleteOrganization(int organizationID);
	public boolean addOrganization(Organization organization);
	public List<Organization> displayOrganizations();
	public boolean doesOrganizationExist(int organizationId);
	public boolean updateOrganization(Organization organization);
	public boolean isOrganizationNameDuplicate(Organization org);
}
