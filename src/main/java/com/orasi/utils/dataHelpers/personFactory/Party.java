package com.orasi.utils.dataHelpers.personFactory;

import java.util.ArrayList;

import com.orasi.utils.TestReporter;

public class Party {

	private ArrayList<Person> party = new ArrayList<Person>();
	public Party(int numberOfPeople) {
		TestReporter.logDebug("Entering Party#init with number of People");
		TestReporter.logInfo("Creating party with ["+numberOfPeople+"] party");
		
		for (int x = 0; x < numberOfPeople; x++) {
			TestReporter.logDebug("Generating Person [" + (x+1) + "]");	
			addPerson(new Person());

			TestReporter.logDebug("Setting Person [" +(x+1)+"] Address, Phone and Email to primary Person");
			party.get(x).getAllAddresses().get(0).setPrimary(true);
			party.get(x).getAllAddresses().get(0).setStreetName(party.get(0).primaryAddress().getStreetName());
			party.get(x).getAllAddresses().get(0).setStreetNumber(party.get(0).primaryAddress().getStreetNumber());
			party.get(x).getAllAddresses().get(0).setCity(party.get(0).primaryAddress().getCity());
			party.get(x).getAllAddresses().get(0).setState(party.get(0).primaryAddress().getState());
			party.get(x).getAllAddresses().get(0).setStateAbbv(party.get(0).primaryAddress().getStateAbbv());
			party.get(x).getAllAddresses().get(0).setZipCode(party.get(0).primaryAddress().getZipCode());
			party.get(x).getAllAddresses().get(0).setOptIn(true);
			party.get(x).getAllPhones().get(0).setPrimary(true);
			party.get(x).getAllEmails().get(0).setPrimary(true);
			TestReporter.logInfo("\n"+party.get(x).toString().replace("<br/>", "\n"));
		}

		TestReporter.logDebug("Set first Person as Primary Person");
		party.get(0).setPrimary(true);
		TestReporter.logDebug("Ensure first Person is older than 18");
		if (Integer.parseInt(party.get(0).getAge()) <= 18) {
			party.get(0).setAge("45");
			party.get(0).setChild(false);
			party.get(0).setBirthDate("1970-01-14");
		}
		TestReporter.logDebug("Entering Party#init with number of Persons");
	}


	/**
	 *  Associate a new Person to the person using preset data
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 */
	public void addPerson(Person person) {
		party.add(person);
	}

	/**
	 *  Return the person marked as Primary
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the Party primary Person
	 */
	public Person primaryPerson() {
		Person primaryPerson= null;

		for (Person person : party) {
			if (person.isPrimary())
				primaryPerson = person;
		}

		return primaryPerson;
	}

	/**
	 *  Return all People associated to the Party
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return all People as an ArrayList
	 */
	public ArrayList<Person> getAllPersons() {
		return party;
	}

	/**
	 *  Return the number of Children currently in Party
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the number of children in Party
	 */
	public String numberOfChildren() {
		int numberOfChildren = 0;
		for (Person person : party) {
			if (person.isChild() && Integer.valueOf(person.getAge()) > 2
					&& Integer.valueOf(person.getAge()) < 18)
				numberOfChildren++;
		}
		return String.valueOf(numberOfChildren);
	}

	public String numberOfInfants() {
		int numberOfInfant = 0;
		for (Person person : party) {
			if (person.isChild() && Integer.valueOf(person.getAge()) <= 2)
				numberOfInfant++;
		}
		return String.valueOf(numberOfInfant);
	}

	/**
	 *  Return the number of adults currently in Party
	 * @author Justin Phlegar
	 * @version 11/28/2015 Justin Phlegar - original
	 * @return the number of adults in Party
	 */
	public String numberOfAdults() {
		int numberOfAdults = 0;
		for (Person person : party) {
			if (!person.isChild())
				numberOfAdults++;
		}
		return String.valueOf(numberOfAdults);
	}

}
