package de.timoklostermann.refuel.fragments;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import de.timoklostermann.refuel.R;
import de.timoklostermann.refuel.adapter.Filling;
import de.timoklostermann.refuel.util.StatisticsHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatisticsFragment extends Fragment {

    private ArrayList<Filling> fillings;

    private TextView tv_consumpion_average;
    private TextView tv_consumpion_maximum;
    private TextView tv_consumpion_minimum;
    private TextView tv_consumpion_perYear;
    private TextView tv_consumpion_perMonth;
    private TextView tv_consumpion_overall;
    
    private TextView tv_distance_average;
    private TextView tv_distance_maximum;
    private TextView tv_distance_minimum;
    private TextView tv_distance_perYear;
    private TextView tv_distance_perMonth;
    private TextView tv_distance_overall;
    
    private TextView tv_cost_average;
    private TextView tv_cost_maximum;
    private TextView tv_cost_minimum;
    private TextView tv_cost_averagePerUnit;
    private TextView tv_cost_maximumPerUnit;
    private TextView tv_cost_minimumPerUnit;
    private TextView tv_cost_perYear;
    private TextView tv_cost_perMonth;
    private TextView tv_cost_overall;
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
		View view = inflater.inflate(R.layout.fragment_statistics, container, false);
		
		tv_consumpion_average = (TextView) view.findViewById(R.id.tv_statistics_fuel_average);
		tv_consumpion_maximum = (TextView) view.findViewById(R.id.tv_statistics_fuel_maximum);
		tv_consumpion_minimum = (TextView) view.findViewById(R.id.tv_statistics_fuel_minimum);
		tv_consumpion_perYear = (TextView) view.findViewById(R.id.tv_statistics_fuel_perYear);
		tv_consumpion_perMonth = (TextView) view.findViewById(R.id.tv_statistics_fuel_perMonth);
		tv_consumpion_overall = (TextView) view.findViewById(R.id.tv_statistics_fuel_overall);
		
		tv_distance_average = (TextView) view.findViewById(R.id.tv_statistics_distance_average);
		tv_distance_maximum = (TextView) view.findViewById(R.id.tv_statistics_distance_maximum);
		tv_distance_minimum = (TextView) view.findViewById(R.id.tv_statistics_distance_minimum);
		tv_distance_perYear = (TextView) view.findViewById(R.id.tv_statistics_distance_perYear);
		tv_distance_perMonth = (TextView) view.findViewById(R.id.tv_statistics_distance_perMonth);
		tv_distance_overall = (TextView) view.findViewById(R.id.tv_statistics_distance_overall);
		
		tv_cost_average = (TextView) view.findViewById(R.id.tv_statistics_cost_average);
		tv_cost_maximum = (TextView) view.findViewById(R.id.tv_statistics_cost_maximum);
		tv_cost_minimum = (TextView) view.findViewById(R.id.tv_statistics_cost_minimum);
		tv_cost_averagePerUnit = (TextView) view.findViewById(R.id.tv_statistics_cost_averagePerUnit);
		tv_cost_maximumPerUnit = (TextView) view.findViewById(R.id.tv_statistics_cost_maximumPerUnit);
		tv_cost_minimumPerUnit = (TextView) view.findViewById(R.id.tv_statistics_cost_minimumPerUnit);
		tv_cost_perYear = (TextView) view.findViewById(R.id.tv_statistics_cost_perYear);
		tv_cost_perMonth = (TextView) view.findViewById(R.id.tv_statistics_cost_perMonth);
		tv_cost_overall = (TextView) view.findViewById(R.id.tv_statistics_cost_overall);
		
        return view;
    }
	
    @Override
    public void onResume() {
    	// TODO Calculate statistics data from fillings and show them
		getFillingsFromSharedPreferences();
		StatisticsHelper stats = new StatisticsHelper(fillings);
		
		NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
		format.setMaximumFractionDigits(1);
		
		//TODO Correct Units!
		
		tv_consumpion_average.setText(format.format(stats.getAverageConsumption()) + "l/100km");
		tv_consumpion_maximum.setText(stats.getMaximumConsumption() + "");
		tv_consumpion_minimum.setText(stats.getMininmalConsumption() + "");
		tv_consumpion_perYear.setText(stats.getConsumptionPerYear() + "");
		tv_consumpion_perMonth.setText(stats.getConsumptionPerMonth() + "");
		tv_consumpion_overall.setText(stats.getOverallAmount() + "");
		
		tv_distance_average.setText(stats.getAverageDistance() + "");
		tv_distance_maximum.setText(stats.getMaximumDistance() + "");
		tv_distance_minimum.setText(stats.getMinimalDistance() + "");
		tv_distance_perYear.setText(stats.getDistancePerYear() + "");
		tv_distance_perMonth.setText(stats.getDistancePerMonth() + "");
		tv_distance_overall.setText(stats.getOverallDistance() + "");
		
		tv_cost_average.setText(stats.getAverageCostPerFilling() + "");
		tv_cost_maximum.setText(stats.getMaximumCostPerFilling() + "");
		tv_cost_minimum.setText(stats.getMinimumCostPerFilling() + "");
		tv_cost_averagePerUnit.setText(stats.getAverageCostPerUnit() + "");
		tv_cost_maximumPerUnit.setText(stats.getMaximumCostPerUnit() + "");
		tv_cost_minimumPerUnit.setText(stats.getMinimumCostPerUnit() + "");
		tv_cost_perYear.setText(stats.getCostPerYear() + "");
		tv_cost_perMonth.setText(stats.getCostPerMonth() + "");
		tv_cost_overall.setText(stats.getOverallCost() + "");
		
    	super.onResume();
    }
    
    private void getFillingsFromSharedPreferences() {
    	// TODO Hardcoded for testing
    	fillings = new ArrayList<Filling>();
    	fillings.add(new Filling(new Date(112,10,11),149500,1.59,13, true));
    	fillings.add(new Filling(new Date(112,11,01),150137,1.63,37, true));
    }
}
