package models;

import java.awt.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: laiq
 * Date: 5/28/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceFile extends Model implements Resource, Serializable {

	private static final long serialVersionUID = 2660584783146068707L;
	File file;
	
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
            File outDirectory = new File(Organizer.getInstance().getCurrentUser().getUserProfile().getPath());
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
            file = new File(Organizer.getInstance().getCurrentUser().getUserProfile().getPath()+"/"+file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ResourceFile(File file){
        this.file=file;
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
