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
	
	/**
	 * Gets the average consumption.
	 * @return
	 */
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

	/**
	 * Gets the maximum consumption.
	 * @return
	 */
	public double getMaximumConsumption() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the minimal consumption.
	 * @return
	 */
	public double getMininmalConsumption() {
		// TODO 
		return 0;
	}
	
	/**
	 * Gets the consumption per year.
	 * @return
	 */
	public double getConsumptionPerYear() {
		// TODO 
		return 0;
	}
	
	/**
	 * Gets the consumption per month.
	 * @return
	 */
	public double getConsumptionPerMonth() {
		// TODO 
		return 0;
	}
	
	/**
	 * Gets the amount of used fuel.
	 * @return
	 */
	public double getOverallAmount() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the average distance.
	 * @return
	 */
	public double getAverageDistance() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the minimal distance.
	 * @return
	 */
	public double getMinimalDistance() {
		// TODO 
		return 0;
	}
	
	/**
	 * Gets the maximum distance.
	 * @return
	 */
	public double getMaximumDistance() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the distance per year.
	 * @return
	 */
	public double getDistancePerYear() {
		//TODO
		return 0;
	}
	
	/**
	 * Gets the distance per month.
	 * @return
	 */
	public double getDistancePerMonth() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the overall distance.
	 * @return
	 */
	public double getOverallDistance() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the average cost per filling.
	 * @return
	 */
	public double getAverageCostPerFilling() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the maximum cost per filling.
	 * @return
	 */
	public double getMaximumCostPerFilling() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the minimum cost per filling.
	 * @return
	 */
	public double getMinimumCostPerFilling() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the average cost per unit.
	 * @return
	 */
	public double getAverageCostPerUnit() {
		// TODO
		return 0;
	}
	
	/**
	 * Gest the maximum cost per unit.
	 * @return
	 */
	public double getMaximumCostPerUnit() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the minimum cost per unit.
	 * @return
	 */
	public double getMinimumCostPerUnit() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the cost per year.
	 * @return
	 */
	public double getCostPerYear() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the cost per month.
	 * @return
	 */
	public double getCostPerMonth() {
		// TODO
		return 0;
	}
	
	/**
	 * Gets the overall cost.
	 * @return
	 */
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
				// Calculate the distance between last toTop-filling and this toTopfilling
				distance = fillings.get(nextFillingToTop).getDistance() - fillings.get(firstFillingToTop).getDistance();
				// Calculate the amount of fuel between the fillings
				for(int i = firstFillingToTop + 1; i <= nextFillingToTop; i++) {
					quantity += fillings.get(i).getQuantitiy();
				}
				
				// Get the last toTop-filling
				Filling lastToTopFilling = fillings.get(nextFillingToTop);
				// Set the consumption to last filling
				lastToTopFilling.setConsumptionToPrevious(quantity/distance*100);
				
				// Replace the filling with updated consumption
				fillings.set(nextFillingToTop, lastToTopFilling);
				
				// Prepare for next loop
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
