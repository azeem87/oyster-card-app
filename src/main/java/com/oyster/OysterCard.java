package com.oyster;

public class OysterCard {

	private double amount = 0;
	private Station startStation;
	private Station endStation;

	public OysterCard(double creditAmount) {
		this.amount = creditAmount;
	}

	void amountToDebit(double charge) throws InSufficientFundException {
		if (this.amount >= charge) {
			this.amount = this.amount - charge;
			return;
		}
		throw new InSufficientFundException();
	}

	void amountToCredit(double charge) {
		this.amount += charge;
	}

	public double getCurrentAmount() {
		return this.amount;
	}

	public Station getStartStation() {
		return startStation;
	}

	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}

	public Station getEndStation() {
		return endStation;
	}

	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}

}
