package com.rabaouithamer.a4study;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabBaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="appstudy";
    private static final String TABLE_Note ="notes";
    private static final String TABLE_CONTACT ="contacts";
    private static final String COLUMN_NAME ="name";
    private static final String COLUMN_PHONE ="phoneNumber";

    private static final String COLUMN_ID ="id";
    public  static final String COLONNE_NAME= "nom";
    public  static final String COLONNE_CONTENU= "contenu";

    public DatabBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createnotes = "CREATE TABLE "+TABLE_Note+" ("+COLUMN_ID+" INTEGER  , "+COLONNE_NAME+" TEXT PRIMARY KEY , "+COLONNE_CONTENU+" TEXT  "+");";

        String createContact = "CREATE TABLE " + TABLE_CONTACT + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PHONE + " INTEGER " +
                ");";
        db.execSQL(createnotes);
        db.execSQL(createContact);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_Note);
        db.execSQL("DROP TABLE IF EXIST "+TABLE_CONTACT);
        onCreate(db);

    }




    //ajouter un contact
    void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,contact.get_name());
        values.put(COLUMN_PHONE,contact.get_phoneNumber());

        db.insert(TABLE_CONTACT,null,values);
        db.close();

    }

    //supprimer un contact
    void deleteContact(String nom){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACT + "WHERE " + COLUMN_NAME + "=\"" +nom+ "\";");
    }


    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();

        String select_query = "select * from "+TABLE_CONTACT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query,null);
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phoneNumber(cursor.getInt(2));

                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }



    //methode pour les notes :
    public void addNotes(Notes n){
        ContentValues values = new ContentValues();
        values.put(COLONNE_NAME, n.get_nom());

        values.put(COLONNE_CONTENU, n.get_contenu());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_Note, null, values);
        db.close();


    }


    public void deletNote(String name){

        SQLiteDatabase db =  getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_Note+" WHERE "+COLONNE_CONTENU+"= '"+name+ "' ;");
    }


    public ArrayList<String> extraireDonne(){

        SQLiteDatabase db =  getWritableDatabase();
        String requette = "SELECT * FROM "+TABLE_Note+" ;";
        Cursor c = db.rawQuery(requette, null);
        c.moveToFirst();
        ArrayList<String> list = new ArrayList<String>();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("contenu"))!=null){
                list.add(c.getString(c.getColumnIndex("contenu")));



            }

            c.moveToNext();

        }

        db.close();
        return(list);


    }



    public ArrayList<String> extraireDonneContact(){

        SQLiteDatabase db =  getWritableDatabase();
        String requette = "SELECT * FROM "+TABLE_CONTACT+" ;";
        Cursor c = db.rawQuery(requette, null);
        c.moveToFirst();
        ArrayList<String> list = new ArrayList<String>();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("name"))!=null){
                list.add(c.getString(c.getColumnIndex("name")));



            }

            c.moveToNext();

        }

        db.close();
        return(list);


    }


    public String extraireNom(String id){
        String nom ="";
        SQLiteDatabase db =  getWritableDatabase();
        Cursor c = db.rawQuery("SELECT "+COLONNE_CONTENU+ " FROM "+TABLE_Note+" WHERE "+COLONNE_CONTENU+"='"+id+"'", null);
        if (c.moveToFirst()) {
            nom=c.getString(c.getColumnIndex("contenu"));
            c.close();
        }


        return(nom);
    }


    public String afficher(String id){
        String note = "";
        SQLiteDatabase db =  getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_Note+" WHERE "+COLONNE_CONTENU+"='"+id+"'", null);
        if (c.moveToFirst()) {
            note=c.getString(c.getColumnIndex("nom"));
            c.close();
        }

        return note;
    }
}
