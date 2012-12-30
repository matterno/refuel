package de.timoklostermann.refuel.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import de.timoklostermann.refuel.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FillingAdapter extends BaseAdapter {

	private List<Filling> items;
	private LayoutInflater inflater;

	public FillingAdapter(Context context, List<Filling> items) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.items = items;
	}
	
	public int getCount() {
		return items.size();
	}

	public Object getItem(int position) {
		return items.get(position);
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		tv_date.setText(sdf.format(items.get(position).getDate()));
		tv_consumption.setText(items.get(position).getConsupmtion());
		tv_price.setText(items.get(position).getPrice());
		tv_quantitiy.setText(items.get(position).getQuantitiy());
		
		return convertView;
	}

}
