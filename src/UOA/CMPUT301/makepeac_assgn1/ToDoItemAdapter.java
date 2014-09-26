package UOA.CMPUT301.makepeac_assgn1;
/* This custom adapter was not created by me, 
I found it on an excellent tutorial here:
 * http://windrealm.org/tutorials/android/listview-with-checkboxes-without-listactivity.php
 * on 09/17/14. My only hand at this was to adapt the code to fit my variable names and 
 * custom class ToDoItem.
 */
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

	private LayoutInflater inflater;
	
	public ToDoItemAdapter(Context context, List<ToDoItem> currentToDos ){
		super( context, R.layout.one_row, R.id.ItemText, currentToDos );
		inflater = LayoutInflater.from(context) ;  
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ToDoItem item = (ToDoItem) this.getItem(position);
		
		CheckBox checkBox ;   
	    TextView textView ;
	    
	 // Create a new row view  
	      if ( convertView == null ) {  
	        convertView = inflater.inflate(R.layout.one_row, null);  
	          
	        // Find the child views.  
	        textView = (TextView) convertView.findViewById( R.id.ItemText );  
	        checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox );  
	          
	        // Optimization: Tag the row with it's child views, so we don't have to   
	        // call findViewById() later when we reuse the row.  
	        convertView.setTag( new CheckBoxItemView(textView,checkBox) );  
	        
	     // If CheckBox is toggled, update the item it is tagged with.  
	        checkBox.setOnClickListener( new View.OnClickListener() {  
	          public void onClick(View v) {  
	            CheckBox cb = (CheckBox) v ;  
	            ToDoItem item = (ToDoItem) cb.getTag();  
	            item.setChecked( cb.isChecked() );  
	          }  
	        });          
	}// Reuse existing row view  
	      else {  
	          // Because we use a ViewHolder, we avoid having to call findViewById().  
	          CheckBoxItemView viewHolder = (CheckBoxItemView) convertView.getTag();  
	          checkBox = viewHolder.getCheckBox() ;  
	          textView = viewHolder.getTextView() ;  
	        }
	   // Tag the CheckBox with the item it is displaying, so that we can  
	      // access the item in onClick() when the CheckBox is toggled.  
	      checkBox.setTag( item);   
	        
	      // Display data  
	      checkBox.setChecked( item.isChecked() );  
	      textView.setText( item.GetName() );        
	        
	      return convertView;
	}
}
