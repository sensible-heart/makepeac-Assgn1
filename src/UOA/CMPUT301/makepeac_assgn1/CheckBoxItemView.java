package UOA.CMPUT301.makepeac_assgn1;
import android.widget.CheckBox;
import android.widget.TextView;
/*This whole class code was adapted from the excellent custom view tutorial on:
 * http://windrealm.org/tutorials/android/listview-with-checkboxes-without-listactivity.php 
 * 09/17/14
 */

public class CheckBoxItemView {
	private CheckBox checkbox;
	private TextView itemtext;
	
	public CheckBoxItemView( TextView itemtext, CheckBox checkbox ) { //sets the class checkbox and textview to the passes checkbox and textview 
	      this.checkbox = checkbox ;  
	      this.itemtext = itemtext ;  
	    }
	public CheckBox getCheckBox() {  //retrieves the set checkbox
	      return checkbox;  
	    }  
	    public void setCheckBox(CheckBox checkbox) {  //sets the checkbox to something else
	      this.checkbox = checkbox;  
	    }  
	    public TextView getTextView() {  //retrieves the set textbox
	      return itemtext;  
	    }  
	    public void setTextView(TextView itemtext) {  //sets the new text to the passed itemtext
	      this.itemtext = itemtext;  
	    }
}

