package dtu.client.dal;

import java.util.ArrayList;
import java.util.List;

public class OperatoerDAO implements IOperatoerDAO {
	
	private List<OperatoerDTO> opList;

	public OperatoerDAO() {
		opList = new ArrayList<OperatoerDTO>();
		
		// Indsæt start data
		opList.add(new OperatoerDTO(2, "Jens Jensen", "JJ", "121212-1212", "operatoer2"));
		opList.add(new OperatoerDTO(3, "Mogens Andersen", "MA", "131313-1313", "operatoer3"));
		opList.add(new OperatoerDTO(4, "Pia Bendtsen", "PB", "141414-1414", "operatoer4"));
	}
	
	@Override
	public void createOperator(OperatoerDTO op) {
		opList.add(op);
	}

	@Override
	public void deleteOperator(int index) {
		opList.remove(index);
	}

	@Override
	public void updateOperator(OperatoerDTO op, int index) {
		opList.set(index, op);
	}

	@Override
	public List<OperatoerDTO> getOperators() {
		return opList;
	}

	@Override
	public int getSize() {
		return opList.size();
	}

}
