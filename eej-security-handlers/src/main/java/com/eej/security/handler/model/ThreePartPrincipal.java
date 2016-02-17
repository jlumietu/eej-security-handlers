package com.eej.security.handler.model;

import java.security.Principal;


public interface ThreePartPrincipal extends Principal{

	public abstract String getMail();

	public abstract int getId();

}