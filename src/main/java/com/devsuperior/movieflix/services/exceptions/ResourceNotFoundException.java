package com.devsuperior.movieflix.services.exceptions;


//exception : tem que ser obrigatoriamente tratada. 
//RuntimeException : tratado em tempo de execução
public class ResourceNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException ( String msg )
	{
		super(msg);
	}

}
