package models;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: laiq
 * Date: 5/28/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceFile extends Model implements Resource,Serializable {
    File file;
    @Override
    public void open() {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ResourceFile(File file){
        this.file=file;
    }
}
