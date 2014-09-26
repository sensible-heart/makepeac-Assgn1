package UOA.CMPUT301.makepeac_assgn1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class UpdateToDoLists {
	private static final String FILENAME = "file.sav";//name of file we will save list items to for CurrentToDo items
	private static final String FILENAME2 = "file2.sav";//file used to save archive items so they can be passed between activities and classes
	
	public UpdateToDoLists(){//initializes
		super();
	}
	//The save and load file methods were adapted from code given to us in lab 3 on 09/23/14
    public void saveInFile(List <ToDoItem> CurrentToDos, Context context) {
		try {//used to handle exceptions
			FileOutputStream fos = context.openFileOutput(FILENAME,0);//creates a new file stream using our CurrentToDos save file
			Gson gson = new Gson();
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(CurrentToDos,osw);
			osw.flush();//makes sure to flush data from writer so writing can complete
			fos.close();	//closes file once changes are done
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public List<ToDoItem> loadFromFile(List <ToDoItem> CurrentToDos, Context context){
		try {
			FileInputStream fis =  context.openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			//learned in Lab 3 09/23/14
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
			CurrentToDos = gson.fromJson(in, listType);
			//
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CurrentToDos;//returns newlyloaded CurrentToDos list
	}
    //end of lab altered code
    //The next two methods are just adapted from the ones above, nothing really changes except they are adapted to deal only with archive items
    public void saveInFile2(List <ToDoItem> ArchiveToDos, Context context) {
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME2,0);
			Gson gson = new Gson();
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(ArchiveToDos,osw);
			osw.flush();
			fos.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public List<ToDoItem> loadFromFile2(List <ToDoItem> ArchiveToDos, Context context){
		try {
			FileInputStream fis =  context.openFileInput(FILENAME2);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			//learned in Lab 3 09/23/14
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
			ArchiveToDos = gson.fromJson(in, listType);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ArchiveToDos;
	}

}

