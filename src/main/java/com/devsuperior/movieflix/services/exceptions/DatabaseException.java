
package com.devsuperior.movieflix.services.exceptions;

// exception : tem que ser obrigatoriamente tratada. 
// RuntimeException : tratado em tempo de execução
public class DatabaseException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public DatabaseException ( String msg )
	{
		super(msg);
	}

}
