package com.example.avaliacao.modules.project.exceptions;

public class EmployeeCanNotBeInMoreThanTwoProjectsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "O Funcionário não pode estar em mais de 2 projetos ao mesmo tempo";
	}

}
