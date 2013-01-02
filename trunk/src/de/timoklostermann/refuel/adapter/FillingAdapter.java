package de.timoklostermann.refuel.adapter;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.timoklostermann.refuel.R;
import android.annotation.SuppressLint;
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
		
		DateFormat sdf = SimpleDateFormat.getDateInstance();
		NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
		format.setMaximumFractionDigits(1);
		
		//TODO Show correct Units!
		tv_date.setText(sdf.format(items.get(position).getDate()));
		
		double consumption = items.get(position).getConsumptionToPrevious();
		if(consumption != 0) {
			tv_consumption.setText(format.format(consumption) + "l/100km");
		} else {
			tv_consumption.setText("--l/100km");
		}
		tv_quantitiy.setText(format.format(items.get(position).getQuantitiy()) + "l");
		
		format.setMaximumFractionDigits(2);
		tv_price.setText(format.format(items.get(position).getPrice()) + "€");
		
		return convertView;
	}

}
