package bubtjobs.com.sqliteassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Murtuza on 3/13/2016.
 */
public class AdapterForContact extends ArrayAdapter<Contact>{

    private ArrayList<Contact> contactList;
    private Context context;

    public AdapterForContact(Context context, ArrayList<Contact> contactList) {
        super(context, R.layout.custom_row,contactList);
        this.context=context;
        this.contactList=contactList;
    }

    static class ViewHolder{
        TextView nameTv;
        TextView idTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.custom_row,null);

            viewHolder=new ViewHolder();
            viewHolder.nameTv=(TextView)convertView.findViewById(R.id.nameTv);
            viewHolder.idTv=(TextView)convertView.findViewById(R.id.idTv);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.nameTv.setText(contactList.get(position).getName());
        viewHolder.idTv.setText(""+contactList.get(position).getId());


        return convertView;
    }
}
