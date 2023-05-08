package com.example.sql_lite_crud_opration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1,v1,d1,u1;
    databse g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText) findViewById(R.id.name);
        e2=(EditText) findViewById(R.id.email);

        e3=(EditText) findViewById(R.id.password);
        b1=(Button) findViewById(R.id.button) ;
        v1=(Button)findViewById(R.id.v1);
        d1=(Button)findViewById(R.id.d1);
        u1=(Button)findViewById(R.id.u1);




        g=new databse(this);
//        SQLiteDatabase db= g.getReadableDatabase();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name= e1.getText().toString();
                String email=e2.getText().toString();
                String password=e3.getText().toString();
                if (name.equals("") || email.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean i = g.insert_data(name ,email,password);

                    if(i==true){
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();

                    }

                    else {
                        Toast.makeText(MainActivity.this, " Not Successful", Toast.LENGTH_SHORT).show();
                    }
                }

                e1.setText("");
                e2.setText("");
                e3.setText("");


            }
        });

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                Boolean i=g.delete_data(name);
                if (i==true)
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
            }
        });

        u1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String email=e2.getText().toString();
                String password=e3.getText().toString();

                Boolean i=g.update_data(name,email,password);
                if(i==true)
                    Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Not Successsful", Toast.LENGTH_SHORT).show();

            }
        });



        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t=g.getinfo();
                if(t.getCount()==0){
                    Toast.makeText(MainActivity.this, "NO data found", Toast.LENGTH_SHORT).show();
                }

                StringBuffer buffer= new StringBuffer();
                while (t.moveToNext()){
                    buffer.append("Name::"+t.getString(1)+"\n");
                    buffer.append("Email::"+t.getString(2)+"\n");
                    buffer.append("Password::"+t.getString(3)+"\n\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("SignUp User Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




    }
}