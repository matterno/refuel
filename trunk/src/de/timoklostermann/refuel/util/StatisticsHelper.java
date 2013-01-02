package de.timoklostermann.refuel.util;

import java.util.Collections;
import java.util.List;

import de.timoklostermann.refuel.adapter.Filling;

public class StatisticsHelper {
	
	private List<Filling> fillings;
	
	public StatisticsHelper(List<Filling> fillings) {
		this.fillings = fillings;
		Collections.sort(fillings);
	}
	
	public double getAverageConsumption() {
		int firstTop = findFirstFillingToTop();
		int lastTop = findLastFillingToTop();
		if(firstTop == -1) {
			return 0;
		}
		
		double distance = fillings.get(lastTop).getDistance() - fillings.get(firstTop).getDistance();
		double quantity = 0;
		for(int i = firstTop + 1; i <= lastTop; i++) {
			quantity += fillings.get(i).getQuantitiy();
		}
		
		return quantity/distance*100;
	}

	public double getMaximumConsumption() {
		// TODO
		return 0;
	}
	
	public double getMininmalConsumption() {
		// TODO 
		return 0;
	}
	
	public double getConsumptionPerYear() {
		// TODO 
		return 0;
	}
	
	public double getConsumptionPerMonth() {
		// TODO 
		return 0;
	}
	
	public double getOverallAmount() {
		// TODO
		return 0;
	}
	
	public double getAverageDistance() {
		// TODO
		return 0;
	}
	
	public double getMinimalDistance() {
		// TODO 
		return 0;
	}
	
	public double getMaximumDistance() {
		// TODO
		return 0;
	}
	
	public double getDistancePerYear() {
		//TODO
		return 0;
	}
	
	public double getDistancePerMonth() {
		// TODO
		return 0;
	}
	
	public double getOverallDistance() {
		// TODO
		return 0;
	}
	
	public double getAverageCostPerFilling() {
		// TODO
		return 0;
	}
	
	public double getMaximumCostPerFilling() {
		// TODO
		return 0;
	}
	
	public double getMinimumCostPerFilling() {
		// TODO
		return 0;
	}
	
	public double getAverageCostPerUnit() {
		// TODO
		return 0;
	}
	
	public double getMaximumCostPerUnit() {
		// TODO
		return 0;
	}
	
	public double getMinimumCostPerUnit() {
		// TODO
		return 0;
	}
	
	public double getCostPerYear() {
		// TODO
		return 0;
	}
	
	public double getCostPerMonth() {
		// TODO
		return 0;
	}
	
	public double getOverallCost() {
		// TODO
		return 0;
	}
	
	/**
	 * Adds the information of the consumption to the previous filling that is filled to top.
	 * @return The updated list of Fillings.
	 */
	public List<Filling> generateConsumptionToPreviousList() {
		int firstFillingToTop = findFirstFillingToTop();
		
		// No filling to top found.
		if(firstFillingToTop == -1) {
			return fillings;
		}
		
		double distance = 0;
		double quantity = 0;
		
		// Searching for the next fillings to top.
		for(int nextFillingToTop = firstFillingToTop + 1; nextFillingToTop < fillings.size(); nextFillingToTop++) {
			if(fillings.get(nextFillingToTop).getFilledToTop()) {
				distance = fillings.get(nextFillingToTop).getDistance() - fillings.get(firstFillingToTop).getDistance();
				for(int i = firstFillingToTop + 1; i <= nextFillingToTop; i++) {
					quantity += fillings.get(i).getQuantitiy();
				}
				
				Filling nextToTopFilling = fillings.get(nextFillingToTop);
				nextToTopFilling.setConsumptionToPrevious(quantity/distance*100);
				
				fillings.set(nextFillingToTop, nextToTopFilling);
				quantity = 0;
				firstFillingToTop = nextFillingToTop;
			}
		}
		return fillings;
	}
	
	/**
	 * Finds the first filling that has been filled to top.
	 * @return -1 if no filling is found.
	 */
	private int findFirstFillingToTop() {
		for(int i = 0; i<fillings.size(); i++) {
			if(fillings.get(i).getFilledToTop()) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Finds the last filling that has been filled to top.
	 * @return -1 if no filling is found.
	 */
	private int findLastFillingToTop() {
		int top = -1;
		for(int i = 0; i<fillings.size(); i++) {
			if(fillings.get(i).getFilledToTop()) {
				top = i;
			}
		}
		return top;
	}
}
