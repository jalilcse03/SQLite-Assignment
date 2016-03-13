package bubtjobs.com.sqliteassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllContact extends AppCompatActivity {
    ListView sampleLV;
    ArrayList<Contact> contactArrayList;
    private Contact_Manager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contact);
        manager=new Contact_Manager(this);

        sampleLV = (ListView) findViewById(R.id.sampleListView);

        contactArrayList=new ArrayList<>();

        contactArrayList=manager.getAllContacts();
        if(contactArrayList!=null)
        {
          AdapterForContact adapter=new AdapterForContact(getApplicationContext(),contactArrayList);
            sampleLV.setAdapter(adapter);
        }

        sampleLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id=""+contactArrayList.get(position).getId();

                Intent intent=new Intent(AllContact.this,ContactDetails.class);
                intent.putExtra("Id", Id);
                startActivity(intent);
            }
        });

    }
    public void addNewContact(View view)
    {
        startActivity(new Intent(AllContact.this,MainActivity.class));
    }
}
