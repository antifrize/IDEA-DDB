package ru.umc806.vmakarenko.dao.mock;

import java.util.Random;

import ru.umc806.vmakarenko.domain.Person;
import ru.umc806.vmakarenko.domain.Plane;

public class NamingFactory {
	private static int id = 0;
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

	private static String getRandomString(int a) {
		Random r = new Random();
		StringBuilder string = new StringBuilder();
		string.append(UPPER.charAt(r.nextInt(UPPER.length())));
		for (int i = 1; i < a; i++) {
			string.append(LOWER.charAt(r.nextInt(LOWER.length())));
		}
		return string.toString();
	}

	private static String getRandomPassport() {
		return getRandomString(8);
	}

	public static Person getPerson() {
		Person person = new Person();
		person.setId(id++);
		person.setPassport(getRandomPassport());
		person.setName(getRandomString(4));
		person.setSurname(getRandomString(4));
		person.setLastname(getRandomString(4));
		return person;
	}

    public static Plane getPlane(){
        Plane plane = new Plane();
        plane.setId(id++);
        plane.setManufacturer(getRandomString(10));
        plane.setModel(getRandomString(10));
        plane.setSn(getRandomString(6));
        return plane;
    }
}
