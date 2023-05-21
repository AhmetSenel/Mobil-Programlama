package com.example.loginpage;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.material.textfield.TextInputEditText;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonFragment extends Fragment {

    private TextInputEditText editTextName;
    private TextInputEditText editTextSurname;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextPhoneNumber;
    private TextInputEditText editTextBirthDate;
    private Button updateButton;
    private DatabaseHelper databaseHelper;

    public PersonFragment() {
        // Gerekli boş yapıcı metod
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Fragment'ın layoutunu yükler
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        // DatabaseHelper'ı oluştur
        databaseHelper = new DatabaseHelper(getContext());

        // Gerekli view elemanlarını tanımla
        editTextName = view.findViewById(R.id.ad);
        editTextSurname = view.findViewById(R.id.soyad);
        editTextUsername = view.findViewById(R.id.kullaniciAdi);
        editTextPassword = view.findViewById(R.id.sifre);
        editTextPhoneNumber = view.findViewById(R.id.telefon);
        editTextBirthDate = view.findViewById(R.id.dogumTarihi);
        updateButton = view.findViewById(R.id.guncelle);

        // Güncelleme butonuna tıklama olayını ekle
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kullanıcı adını al
                String username = editTextUsername.getText().toString();

                // Diğer bilgileri al
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();
                String password = editTextPassword.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String birthDate = editTextBirthDate.getText().toString();

                // Güncelleme işlemlerini gerçekleştir
                updatePersonInformation(username, name, surname, password, phoneNumber, birthDate);
            }
        });

        return view;
    }

    private void updatePersonInformation(String username, String name, String surname, String password, String phoneNumber, String birthDate) {
        // Veritabanında güncelleme işlemlerini gerçekleştir
        databaseHelper.updatePerson(username, name, surname, password, phoneNumber, birthDate);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "person.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_PERSON = "person";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_SURNAME = "surname";
        private static final String COLUMN_PASSWORD = "password";
        private static final String COLUMN_PHONE_NUMBER = "phone_number";
        private static final String COLUMN_BIRTH_DATE = "birth_date";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_PERSON +
                    "(" +
                    COLUMN_USERNAME + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_SURNAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_PHONE_NUMBER + " TEXT," +
                    COLUMN_BIRTH_DATE + " TEXT" +
                    ")";
            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
            onCreate(db);
        }

        public void updatePerson(String username, String name, String surname, String password, String phoneNumber, String birthDate) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_SURNAME, surname);
            values.put(COLUMN_PASSWORD, password);
            values.put(COLUMN_PHONE_NUMBER, phoneNumber);
            values.put(COLUMN_BIRTH_DATE, birthDate);
            db.update(TABLE_PERSON, values, COLUMN_USERNAME + " = ?", new String[]{username});
            db.close();
        }
    }
}

