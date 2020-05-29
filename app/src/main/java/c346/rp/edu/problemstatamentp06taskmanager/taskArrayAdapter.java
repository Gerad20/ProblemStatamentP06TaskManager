package c346.rp.edu.problemstatamentp06taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class taskArrayAdapter extends ArrayAdapter<TaskClass> {
    private ArrayList<TaskClass> task;
    private Context context;
    private TextView tvDisplayid;
    private TextView tvDisplaytitle;
    private TextView tvDisplayDesc;

    public taskArrayAdapter(Context context, int resource, ArrayList<TaskClass> objects){
        super(context, resource, objects);
        task = objects;
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_list_view, parent, false);
        tvDisplayid = (TextView)rowView.findViewById(R.id.id);
        tvDisplaytitle = (TextView)rowView.findViewById(R.id.taskName);
        tvDisplayDesc = (TextView) rowView.findViewById(R.id.taskDesc);

        TaskClass tasks = task.get(position);

        tvDisplayid.setText(tasks.get_id() + "");
        tvDisplaytitle.setText(tasks.getTitle());
        tvDisplayDesc.setText(tasks.getDescription() + "");

        return rowView;

    }
}
