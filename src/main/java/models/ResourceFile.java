package models;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/** An object representing a file. This class implements Resource interface.
 * @see Resource
 */
public class ResourceFile extends Model implements Resource, Serializable {

	private static final long serialVersionUID = 2660584783146068707L;
	private File file;
	private String filesPath;
	private String appendedPath;
	
	/** Opens the file represented by this object.
	 */
    @Override
    public void open() {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** Copies the file represented by this object to resources directory.
     * <b>Important:</b> after successful copying this object represents
     * the copied file with changed path.
	 */
    public void copyToResourcesDirectory(){
        try {
            InputStream in = new FileInputStream(file);
            OutputStream out;
            File outDirectory = new File(filesPath + appendedPath);
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
            file = new File(filesPath+appendedPath+"/"+file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** Removes the file represented by this object from resources directory.
	 */
    public void removeFromResourcesDirectory() {
    	try {
			Files.deleteIfExists(Paths.get(filesPath+appendedPath+"/"+file.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /** Constructs a new ResourceFile representing a given file.
     * @param file original file.
     * @param path copy destination folder.
     */
    public ResourceFile(File file, String path){
        this.file=file;
        this.filesPath = path;
        this.appendedPath = "";
    }
    
    /** Constructs a new ResourceFile which is a copy of a given ResourceFile.
     * @param r the given ResourceFile.
     */
    public ResourceFile(ResourceFile r) {
    	this.file = new File(r.file.getAbsolutePath());
    	this.filesPath = new String(r.filesPath);
    	this.appendedPath = "";
    }

    /** Returns path to the file representing by this object.
     * @return path to the file representing by this object.
     */
    public String getPath() {
    	return file.getPath();
    }
    
    /** Appends newPath (random event seed) to path.
     * @param newPath string to be appended.
     */
    public void appendPath(String newPath) {
    	this.appendedPath = "/" + newPath;
    }
    
    
    /** Returns String with full path name.
     * @return String with full path name (data_folder/random_event_seed).
     */
    public String getFullPath() {
    	return filesPath + appendedPath;
    }
    
    /** Returns file name representing by this object.
     * @return file name representing by this object.
     */
    @Override
    public String toString() {
        return file.getName();
    }
}
