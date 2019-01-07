package edu.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/*")
public class RestApplication 
	extends Application
{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestApplication()
	{
		classes.add(SchoolResourceEJB.class);
	}

	@Override
	public Set<Class<?>> getClasses()
	{
		return classes;
	}

	@Override
	public Set<Object> getSingletons()
	{
		return singletons;
	}
}
