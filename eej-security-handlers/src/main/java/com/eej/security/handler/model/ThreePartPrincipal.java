package com.eej.security.handler.model;

import java.security.Principal;


public interface ThreePartPrincipal extends Principal, UserRepositorySerializableId{

	public abstract String getMail();

}