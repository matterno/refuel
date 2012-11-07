package de.timoklostermann.refuel.adapter;

import java.util.List;

import de.timoklostermann.refuel.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FillingAdapter extends BaseAdapter {

	private Filling[] items; // Nicht String, sondern Filling-Objekte.
	private LayoutInflater inflater;

	public FillingAdapter(Context context, Filling[] items) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.items = items;
	}
	
	public int getCount() {
		return items.length;
	}

	public Object getItem(int position) {
		return items[position];
	}

	public long getItemId(int position) {
		return position + 1;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.list_item_filling, null);
		}
		
		TextView tv_date = (TextView) convertView.findViewById(R.id.tv_item_filling_date);
		TextView tv_consumption = (TextView) convertView.findViewById(R.id.tv_item_filling_consumption);
		TextView tv_price = (TextView) convertView.findViewById(R.id.tv_item_filling_price);
		TextView tv_quantitiy = (TextView) convertView.findViewById(R.id.tv_item_filling_quantity);
		
		tv_date.setText(items[position].getDate());
		tv_consumption.setText(items[position].getConsupmtion());
		tv_price.setText(items[position].getPrice());
		tv_quantitiy.setText(items[position].getQuantitiy());
		
		return convertView;
	}

}
