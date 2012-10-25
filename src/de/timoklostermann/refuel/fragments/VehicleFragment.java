package de.timoklostermann.refuel.fragments;

import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.SwipeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class VehicleFragment extends Fragment {

	Button btn_save;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

    	View view = inflater.inflate(R.layout.fragment_vehicle, container, false);
    	
    	btn_save = (Button) view.findViewById(R.id.btn_fragment_vehicle_save);
    	
    	btn_save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Save
				
				Intent intent = new Intent(getActivity().getBaseContext(),SwipeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
    	
        return view;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    }
}
