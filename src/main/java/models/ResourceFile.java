package models;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: laiq
 * Date: 5/28/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceFile extends Model implements Resource, Serializable {

	private static final long serialVersionUID = 2660584783146068707L;
	private File file;
	private String filesPath;
	private boolean appended;
	
    @Override
    public void open() {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void copyToResourcesDirectory(){
        try {
            InputStream in = new FileInputStream(file);
            OutputStream out;
            File outDirectory = new File(filesPath);
            if(!outDirectory.exists())
                outDirectory.mkdirs();
            out = new FileOutputStream(outDirectory.getPath()+"/"+file.getName());


            // Copy the bits from input stream to output stream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            file = new File(filesPath+"/"+file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void removeFromResourcesDirectory() {
    	try {
			Files.deleteIfExists(Paths.get(filesPath+"/"+file.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public ResourceFile(File file, String path){
        this.file=file;
        this.filesPath = path;
        this.appended = false;
    }

    public String getPath() {
    	return file.getPath();
    }
    
    /** Appends newPath (random event seed) to path, does it only once.
     * @param newPath
     */
    public void appendPath(String newPath) {
    	if(!appended) {
    		this.filesPath += newPath;
    		appended = true;
    	}
    }
    
    
    /**
     * @return String with full path name (data_folder/random_event_seed)
     */
    public String getFullPath() {
    	return filesPath;
    }
    
    @Override
    public String toString() {
        return file.getName();
    }
}
