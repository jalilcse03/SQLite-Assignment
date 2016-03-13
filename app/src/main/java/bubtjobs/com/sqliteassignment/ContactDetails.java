package bubtjobs.com.sqliteassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {
    TextView nameTv, phoneNoTv;
    String retriveId = "";
    Contact_Manager manager;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        retriveId = getIntent().getStringExtra("Id");

        manager = new Contact_Manager(this);
        contact = manager.getContact(retriveId);


        nameTv = (TextView) findViewById(R.id.nameTv);
        phoneNoTv = (TextView) findViewById(R.id.phoneTv);

        if (contact != null) {
            nameTv.setText(contact.getName());
            phoneNoTv.setText(contact.getPhoneNo());
        }

    }

    public void deleteOperation(View view) {
        Boolean isDeleteContact = manager.deleteContact(retriveId);
        if (isDeleteContact)
            startActivity(new Intent(ContactDetails.this, AllContact.class));
    }

    public void updateOperation(View view) {

        Intent intent = new Intent(ContactDetails.this, MainActivity.class);
        String[] data = new String[3];
        data[0] = retriveId;
        data[1] = nameTv.getText().toString();
        data[2] = phoneNoTv.getText().toString();

        intent.putExtra("data", data);
        startActivity(intent);

    }
}
