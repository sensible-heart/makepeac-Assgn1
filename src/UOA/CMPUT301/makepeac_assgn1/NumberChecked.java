package UOA.CMPUT301.makepeac_assgn1;

import java.util.List;

public class NumberChecked {
	
	public NumberChecked(){
//initialize class
	}
	
	public int getNumberChecked(List<ToDoItem> CurrentToDos){
		int count = 0;
		for (int i=0;i<CurrentToDos.size();i++){
			if (CurrentToDos.get(i).isChecked() == true){
				count = count + 1 ; 
			}
		}
		return count;
	}
}
