package dtu.client.dal;

import java.util.List;

/*
 * Datalaget ønskes afkoblet med et interface IOperatoerDAO, der indeholder definition 
 * af de nødvendige metoder.
 */

public interface IOperatoerDAO {
	void createOperator(OperatoerDTO op);
	void deleteOperator(int index);
	void updateOperator(OperatoerDTO op, int index);
	List<OperatoerDTO> getOperators();
	int getSize();
}
