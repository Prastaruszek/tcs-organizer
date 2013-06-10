package models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;

/** Singleton representing current application state.
 */
public class Organizer extends Observable {
    private static Organizer instance;
    private static UserManager users;
    private static String savePath = "savefile";

    private Organizer() {
    	if(!loadFromFile(savePath))
    		users = new UserManager();
    }
    
    /** Saves application state to file.
     */
    public void saveToFile(){
    	try {
    		Files.deleteIfExists(Paths.get("save/" + savePath));
    		new File("save/").mkdirs();
    		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save/" + savePath));
    		oos.writeObject(users);
    		oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** Saves application state to file with a given path.
     * @param savePath the given path.
     */
    public void saveToFile(String savePath){
    	try {
    		Files.deleteIfExists(Paths.get("save/" + savePath));
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save/" + savePath));
			oos.writeObject(users);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** Loads application state from file with a given path.
     * @param loadPath the given path.
     * @return <code>true</code> if loading succeeded, <code>false</code> otherwise.
     */
    public boolean loadFromFile(String loadPath){
    	try{
    		if(Files.exists(Paths.get("save/" + loadPath))){
    			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save/" + loadPath));
    			users = (UserManager)ois.readObject();
    			ois.close();
    			return true;
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }

    /** Returns current user.
     * @return current user.
     */
    public User getCurrentUser() {
        return users.getCurrentUser();
    }

    /** Returns user manager.
     * @return user manager.
     */
    public UserManager getUsers() {
        return users;
    }

    /** Returns singleton instance.
     * @return singleton instance.
     */
    public static Organizer getInstance() {
        if ( instance == null )
            instance = new Organizer();
        return instance;
    }

    /** Marks this object as having been changed
     * @see Observable
     */
    public void update() {
    	saveToFile(savePath);
        setChanged();
    }
}
