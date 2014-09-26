package UOA.CMPUT301.makepeac_assgn1;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ArchiveActivity extends Activity {
	
	private ListView archiveList;//creates a variable for listview
	private UpdateToDoLists FileUpdater= new UpdateToDoLists();//creates an instance of UpDateToDoLists, used to load and save data
	private List <ToDoItem> ArchiveToDos = new ArrayList<ToDoItem>();//creates a list of ToDoItem that is used to store Archive items
	private ToDoItemAdapter adapter;//creates a custom adapter for making the right view occur in listview
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {//basic onCreate methods here
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_archive);//sets view to activity_archive.xml
		ListView archiveList = (ListView) findViewById(R.id.ArchiveList);//grabs the id of listview used to create a context menu
		registerForContextMenu(archiveList);//single line from http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/ on 09/23/14
	}
	
	@Override
	protected void onStart(){
    	//TODO Auto-generated method stub
    	super.onStart();
    	FileUpdater = new UpdateToDoLists();
    	archiveList = (ListView)findViewById(R.id.ArchiveList);
    	if (ArchiveToDos != null)//checks to see basically if there are any items in currentToDos
        	ArchiveToDos = FileUpdater.loadFromFile2(ArchiveToDos,this);//then loads from file to populate
    	adapter = new ToDoItemAdapter(this,ArchiveToDos);
    	archiveList.setAdapter(adapter);//sets current adapter to the custom one
	}
	
    //Adapted from a tutorial http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/ on 09/23/14
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.ArchiveList) { 
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(ArchiveToDos.get(info.position).GetName());//gets the name of the menu based upon the position in ArchiveToDos, getting the string name of the item
        String[] menuItems = getResources().getStringArray(R.array.Archive);//retrieves names that appear in the menu from the predefined array holding said names
        for (int i = 0; i<menuItems.length; i++) {//iterates through menuItems obtaining the string names that are supposed to appear in the menu
          menu.add(Menu.NONE, i, i, menuItems[i]);//adds these found string names to the generated menu
        }//end of cited code
      }
    }
	
	//Adapted from a tutorial http://www.mikeplate.com/2010/01/21/show-a-context-menu-for-long-clicks-in-an-android-listview/ on 09/23/14
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      int menuItemIndex = item.getItemId();//retrieves index of selected item from list
      //end of cited code
      if (menuItemIndex == 0){//compares whether or not to delete an item based on the chosen index and the index of delete
    	  ArchiveToDos.remove(info.position);//removes item from ArchiveToDos based on the selected item from the listview
      }
      if (menuItemIndex == 1){
    	  try{
      	  	Intent intent = new Intent(Intent.ACTION_SEND);//creates a new intent used to send the item
      	  	intent.setType("message/rfc822");//allows the user to select any email app they have installed
			intent.putExtra(Intent.EXTRA_TEXT, "This is your current to do: " + ArchiveToDos.get(info.position).GetName());//makes sure the slected to do is included in the subject text
			startActivity(intent);//initializes the email activity
      	  } catch (android.content.ActivityNotFoundException ex){//incase no email clients installed
      		  Toast toast = Toast.makeText(this,"No email installed.",Toast.LENGTH_SHORT);
      		  toast.show();
      	  }
      }
      FileUpdater.saveInFile2(ArchiveToDos, this);//updates the current list of ArchiveToDos
      adapter.notifyDataSetChanged();//makes sure the adapter changes accordingly
      return true;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
