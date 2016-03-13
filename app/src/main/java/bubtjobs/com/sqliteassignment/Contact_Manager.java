package bubtjobs.com.sqliteassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Murtuza on 3/13/2016.
 */
public class Contact_Manager {
    private DataBaseHelper helper;
    private Contact contact;
    private SQLiteDatabase database;

    public Contact_Manager(Context context){
        helper=new DataBaseHelper(context);
    }

    private void open(){
        database=helper.getWritableDatabase();
    }

    private void close(){
        helper.close();
    }

    // add
    public boolean addContact(Contact contact){
        this.open();

        ContentValues contentValues=new ContentValues();

        contentValues.put(DataBaseHelper.COL_NAME,contact.getName());
        contentValues.put(DataBaseHelper.COL_PHONENO,contact.getPhoneNo());

        long inserted=database.insert(DataBaseHelper.TABLE_CONTACT,null,contentValues);

        database.close();

        if(inserted>0)
            return true;
        else
        return false;
    }
    // get data
    public Contact getContact(String id){
        this.open();
        // this is normal sql format
        String query="select  * FROM "+DataBaseHelper.TABLE_CONTACT+" WHERE "+DataBaseHelper.COL_ID+" = "+id;

        Cursor cursor=database.rawQuery(query,null);

        cursor.moveToFirst();

        int mId=cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String name=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        String phoneNo=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONENO));

        contact=new Contact(mId,name,phoneNo);
        this.close();
        return contact;
    }

    // get all data
    public ArrayList<Contact> getAllContacts(){
        this.open();
        ArrayList<Contact> contactList=new ArrayList<>();
        String query="SELECT * FROM "+DataBaseHelper.TABLE_CONTACT;
        Cursor cursor=database.rawQuery(query, null);
        cursor.moveToFirst();

        if(cursor!=null && cursor.getCount()>0){
            for(int i=0;i<cursor.getCount();i++)
            {
                int mId=cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
                String name=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                String phoneNo=cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONENO));

                contact=new Contact(mId,name,phoneNo);
                contactList.add(contact);
                cursor.moveToNext();
            }
        }
        this.close();

        return contactList;
    }

    public boolean deleteContact(String id) {
        this.open();
        int deleted = database.delete(DataBaseHelper.TABLE_CONTACT, DataBaseHelper.COL_ID + "= " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }
    public boolean updateContact(String id, Contact contact) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_NAME, contact.getName());
        contentValues.put(DataBaseHelper.COL_PHONENO, contact.getPhoneNo());

        int updated = database.update(DataBaseHelper.TABLE_CONTACT, contentValues, DataBaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;

    }

}
