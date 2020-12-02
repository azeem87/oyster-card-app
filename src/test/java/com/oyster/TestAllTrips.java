package com.oyster;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAllTrips {

    @Test
    public void shouldCoverZone1AndOutsideZone1() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HOLBRON), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.EARLS_COURT), oysterCard);
    	
    	tripCharger.commuteByBus(oysterCard);
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.EARLS_COURT), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HAMMERSMITH), oysterCard);
    	
    	assertEquals(23.7, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldCoverMaxCharge() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HOLBRON), oysterCard);
    	
    	assertEquals(26.8, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldCoverAnywhereInZone1() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HOLBRON), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.ALDGATE), oysterCard);
    	
    	assertEquals(27.5, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldCoverAnyZoneOutsideZone1() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.ARSENAL), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HAMMERSMITH), oysterCard);
    	
    	assertEquals(28, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldCoverAny2ZonesIncludingZone1() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HAMMERSMITH), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HOLBRON), oysterCard);
    	
    	assertEquals(27, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldCoverAny2ZonesExcludingZone1() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.ARSENAL), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.WIMBLEDON), oysterCard);
    	
    	assertEquals(27.75, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldCoverMoreThan2Zones() throws InSufficientFundException {
       
    	OysterCard oysterCard = new OysterCard(30.0);
    	
    	TripCharger tripCharger = new TripCharger();
    	
    	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.WIMBLEDON), oysterCard);
    	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.ALDGATE), oysterCard);
    	
    	assertEquals(26.8, oysterCard.getCurrentAmount());
    }
    
    @Test
    public void shouldThrowInsufficientFundException() throws InSufficientFundException {
       
    	Assertions.assertThrows(InSufficientFundException.class, () -> {
    		OysterCard oysterCard = new OysterCard(0.0);
        	
        	TripCharger tripCharger = new TripCharger();
        	
        	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HOLBRON), oysterCard);
        	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.EARLS_COURT), oysterCard);
       });
    	
    }
    
    @Test
    public void shouldThrowInsufficientFundExceptionAfterFirstTransaction() throws InSufficientFundException {
       
    	Assertions.assertThrows(InSufficientFundException.class, () -> {
    		OysterCard oysterCard = new OysterCard(3.0);
        	
        	TripCharger tripCharger = new TripCharger();
        	
        	tripCharger.beginTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.HOLBRON), oysterCard);
        	tripCharger.exitTubeJourney(StationZoneLookUp.getStationByName(StationZoneLookUp.EARLS_COURT), oysterCard);
        	
        	tripCharger.commuteByBus(oysterCard);
       });
    	
    }
}
