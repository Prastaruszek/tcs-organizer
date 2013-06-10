package models;

import java.awt.*;
import java.io.Serializable;
import java.net.URI;

/** An object representing a link. This class implements Resource interface.
 *
 * @author laiqu
 *
 * @see Resource
 */
public class ResourceLink extends Model implements Resource, Serializable {

	private static final long serialVersionUID = 305787132332484558L;
	private String uri;
	
	/** Opens the link represented by this object in a default browser.
	 */
    @Override
    public void open() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(uri));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /** Constructs a new link with a given uri.
     * @param uri string representing the uri.
     */
    public ResourceLink(String uri) {
        this.uri = uri.startsWith("http://") || uri.startsWith("https://") ? uri : "http://" + uri;
    }

    /** Returns string representing the uri.
     * @return string representing the uri.
     */
    @Override
    public String toString() {
        return uri;
    }
}
