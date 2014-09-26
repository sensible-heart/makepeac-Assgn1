package UOA.CMPUT301.makepeac_assgn1;


public class ToDoItem {
	
	private String ItemName;
	private Boolean Checked = false;//initialized to an unchecked state
	
	public ToDoItem(String name){//class constructor
		super();
		ItemName = name;
	}
	
	public String GetName(){//retrieves string name of Item
		return ItemName;
	}

	public Boolean isChecked(){//checks to see whether an object is checked
		return Checked;
	}
	
	public void changeChecked(){//toggles the checked state of an item
		this.Checked = !Checked;
	}
	
	public void setChecked(boolean Checked) {  //sets the state to whatever is passed to the method
	      this.Checked = Checked;  
	}
	
	@Override
	public String toString(){//overrides object class toString() method to return name of ToDoItem
		return ItemName;
	}
}

