package com.example.room_datbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_datbase.Database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCLickListner{
    Button btnADD,btnDelete,btnUpdate, btnSearchByName, btnSearchById;
    EditText pl_firstName, pl_lastName, pl_searcName, pl_searchId;

    RecyclerView rcv_name;
    CustomAdapter adt;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnADD = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        pl_firstName =findViewById(R.id.pl_firstName);
        pl_lastName =findViewById(R.id.pl_lastName);
        pl_searcName =findViewById(R.id.pl_search);
        pl_searchId =findViewById(R.id.pl_search2);
        rcv_name= findViewById(R.id.rcv_name);
        btnSearchByName= findViewById(R.id.btn_searchByName);
        btnSearchById= findViewById(R.id.btn_searchById);

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);

        users = new ArrayList<>();
        adt = new CustomAdapter(users,this);
        rcv_name.setAdapter(adt);
        rcv_name.setHasFixedSize(true);
        rcv_name.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fN = pl_firstName.getText().toString();
                String lN= pl_lastName.getText().toString();
                UserDatabase.getInstance(MainActivity.this).userDao().addUser(new User(fN,lN));
                pl_firstName.setText("");
                pl_lastName.setText("");
                LoadUser();

            }
        });

        btnSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName  = pl_searcName.getText().toString();
                List<User> users = UserDatabase.getInstance(MainActivity.this).userDao().getUserBYfirstNameorLastName("%"+searchName+"%","%"+searchName+"%");
                adt.setListChange(users);
            }
        });

        btnSearchById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int searchId  = Integer.parseInt(pl_searchId.getText().toString());
                User user = UserDatabase.getInstance(MainActivity.this).userDao().getUserById(searchId);
                List<User>  list = new ArrayList<>();
                list.add(user);
                adt.setListChange(list);
            }
        });

    }

    @Override
    public void itemClick(User user) {

        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        pl_firstName.setText(user.getFirstName());
        pl_lastName.setText(user.getLastName());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase.getInstance(MainActivity.this).userDao().deleteuser(user);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
                LoadUser();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase.getInstance(MainActivity.this).userDao().updateUser(user);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);
                LoadUser();
            }
        });
    }

    public void LoadUser(){
        List<User> users = UserDatabase.getInstance(MainActivity.this).userDao().getAllUser();
        adt.setListChange(users);
    }
}