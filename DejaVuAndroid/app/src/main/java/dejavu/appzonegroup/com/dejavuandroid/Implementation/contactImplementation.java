package dejavu.appzonegroup.com.dejavuandroid.Implementation;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.InputStream;
import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Models.contact_model;

/**
 * Created by CrowdStar on 5/18/2015.
 */
public class contactImplementation {
    public ArrayList getInfo(Context context) {
        ArrayList checkerList = new ArrayList();
        ArrayList<contact_model> contact_models = new ArrayList();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, "upper(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + ") ASC");

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            if (!checkerList.contains(name)) {
                checkerList.add(name);

                contact_model userModel = new contact_model();
                userModel.setPhone(phone);
                userModel.setName(name);
                userModel.setContactImage(loadContactPhoto(context.getContentResolver(),cursor.getColumnIndex(ContactsContract.Contacts._ID)));
                contact_models.add(userModel);

            }
        }

        return contact_models;
    }

    public static Bitmap loadContactPhoto(ContentResolver cr, long  id) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            return null;
        }
        return BitmapFactory.decodeStream(input);
    }
}
