package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElementDatabase {

	List<Element> elements;
	Random rand;

	public ElementDatabase() {
		rand = new Random();
		elements = new ArrayList<Element>();
		fillDatabase();
		printDatabase();
	}

	void printDatabase() {
		for (Element e : elements) {
			System.out.println(e.getAtomicNumber() + " : " + e.getSymbol()
					+ " : " + e.getMassString() + " : " + e.getName() + " : "
					+ e.getElectronConfiguration());
		}
	}

	public Element randomElement() {
		return elements.get(rand.nextInt(elements.size() * 25) / 25);
	}

	public Element randomNot(Element e) {
		Element tar;
		do {
			tar = randomElement();
		} while (tar == e);
		return tar;
	}

	void fillDatabase() {
		InputStream in = ElementDatabase.class
				.getResourceAsStream("/elements.txt");
		BufferedReader read = new BufferedReader(new InputStreamReader(in));

		String buffer = "";
		try {
			while ((buffer = read.readLine()) != null) {
				if(buffer.startsWith("//")) continue;
				String[] args = buffer.split(" ");
				// 5 B 10.81 Boron
				elements.add(new Element(Integer.parseInt(args[0]), args[1],
						Double.parseDouble(args[2]), args[3], args[4]));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
