package dejavu.appzonegroup.com.dejavuandroid.DataBases;

import android.content.Context;

import com.orm.androrm.BooleanField;
import com.orm.androrm.CharField;
import com.orm.androrm.Filter;
import com.orm.androrm.Model;
import com.orm.androrm.QuerySet;

import java.util.List;

/**
 * Created by emacodos on 2/19/2015.
 */
public class Contact extends Model {

    protected CharField name;
    protected CharField number;
    protected CharField uri;
    protected BooleanField onZone;

    public Contact() {
        super();

        name = new CharField();
        number = new CharField();
        uri = new CharField();
        onZone = new BooleanField();
    }

    public static final QuerySet<Contact> objects(Context context) {
        return objects(context, Contact.class);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String contactName) {
        name.set(contactName);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String phoneNo) {
        number.set(phoneNo);
    }

    public String getUri() {
        return uri.get();
    }

    public void setUri(String uriString) {
        uri.set(uriString);
    }

    public boolean isOnZone() {
        return onZone.get();
    }

    public void setOnZone(boolean state) {
        onZone.set(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Contact contact = (Contact) o;

        if (number != null ? !number.equals(contact.number) : contact.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    public static Contact getContactByPhone(Context context, String phone) {
        Filter filter = new Filter();
        filter.is("number", phone);
        List<Contact> contacts = Contact.objects(context).filter(filter).orderBy("name").toList();
        for (int i=0; i<contacts.size(); i++){
            return contacts.get(0);
        }
        return null;
    }

    public static List<Contact> getAllContact(Context context) {
        return Contact.objects(context).all().orderBy("name").toList();
    }

    public static List<Contact> getZoneContact(Context context) {
        Filter f = new Filter();
        f.is("onZone", 1);
        return Contact.objects(context).filter(f).orderBy("name").toList();
    }

    public static List<Contact> getNotZoneContact(Context context) {
        Filter f = new Filter();
        f.is("onZone", 0);
        return Contact.objects(context).filter(f).orderBy("name").toList();
    }
}
