package com.example.cookabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {
	 
    private static LayoutInflater inflater=null;
 
    int[] drawables;
    
    public MyListAdapter(Context c) {
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //inflatern kommer vi anv�nda f�r att skapa list-elementen
        drawables = new int[]{R.drawable.klubbhuset}; //l�gger in alla bilders id:n i en array
    }
 
    public int getCount() {
        return 8; //kan s�ttas till vad som helst i det h�r exemplet
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) { //denna metod kallas n�r appen vill visa ett visst item i listan
        View vi=convertView;
        if(convertView==null) vi = inflater.inflate(R.layout.list_item, null); //om itemet aldrig skapats eller f�rsvunnit s� skapas det h�r
 
        TextView title = (TextView)vi.findViewById(R.id.list_item_title); // title
        TextView desc = (TextView)vi.findViewById(R.id.list_item_description); // description
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_item_image); // thumb image
 
        title.setText("Detta �r en titel");	//s�tter titeln
        desc.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed purus id nunc auctor molestie vitae eget augue."); //s�tter description
        thumb_image.setImageResource(drawables[position%drawables.length]); //s�tter bilden
        return vi;
    }
}