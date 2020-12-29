
package model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Image {
    private int houseId;
    private String serverImageUrl;
    
    public Image(int houseId, String imageUrl) {
        this.houseId = houseId;
        this.serverImageUrl = imageUrl;
    }

    public Image() {
    
    }
   
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getImageUrl() {
        return serverImageUrl;
    }

    public void setImageUrl(String serverImageUrl) {
        this.serverImageUrl = serverImageUrl;
    }
    
    public String getImageFilename() {
        String filename = "";
        
        try {
            URL url = new URL(this.serverImageUrl);
           
            filename = url.getFile();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return filename;
    }
    
    public String getImagePath() {
        try {
            return new URL(this.serverImageUrl).getPath();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
