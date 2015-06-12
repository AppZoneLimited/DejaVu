package dejavu.appzonegroup.com.dejavuandroid.Models;

import android.graphics.Bitmap;

/**
 * Created by CrowdStar on 5/18/2015.
 */
public class contact_model {
    private String name;
    private String phone;
    private Bitmap contactImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getContactImage() {
        return contactImage;
    }

    public void setContactImage(Bitmap contactImage) {
        this.contactImage = contactImage;
    }
}
