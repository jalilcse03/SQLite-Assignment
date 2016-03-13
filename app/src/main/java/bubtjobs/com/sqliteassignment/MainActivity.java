package bubtjobs.com.sqliteassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEt,phoneNoEt;
    Button saveBt;

    private Contact contact;
    private Contact_Manager manager;

    String retriveId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new Contact_Manager(this);


        nameEt=(EditText)findViewById(R.id.nameEt);
        phoneNoEt=(EditText)findViewById(R.id.phoneEt);
        saveBt=(Button)findViewById(R.id.saveBt);

        String data[]=getIntent().getStringArrayExtra("data");
        if(data!=null)
        {
            retriveId=data[0];
            nameEt.setText(data[1]);
            phoneNoEt.setText(data[2]);
            saveBt.setText("Update");
        }

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveBt.getText().toString().equals("Save"))
                saveButtonOperation(v);
                else
                    updataButtonOperation(v);
            }
        });
    }
    public void saveButtonOperation(View v){
        String name=nameEt.getText().toString();
        String phone=phoneNoEt.getText().toString();

        contact=new Contact(name,phone);

        boolean inserted=manager.addContact(contact);

        if(inserted)
        {
            startActivity(new Intent(MainActivity.this,AllContact.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Insert failed", Toast.LENGTH_LONG).show();
        }

    }

    public void updataButtonOperation(View view)
    {
        boolean isUpdate=manager.updateContact(retriveId,new Contact(nameEt.getText().toString(),phoneNoEt.getText().toString()));

        if(isUpdate)
            startActivity(new Intent(MainActivity.this,AllContact.class));
        else
            Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_LONG).show();
    }


    public void showAllContact(View view){
        startActivity(new Intent(MainActivity.this,AllContact.class));
    }
}
