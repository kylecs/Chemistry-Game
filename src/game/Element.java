package game;

public class Element {
	String name;
	String symbol;
	double mass;
	int anumber;
	String eConfig;

	public Element(int anumber, String symbol, double mass, String name,
			String electronconfig) {
		this.name = name;
		this.symbol = symbol;
		this.mass = mass;
		this.anumber = anumber;
		this.eConfig = electronconfig;
	}

	public boolean compare(Element e) {
		return e.symbol.equals(getSymbol());
	}

	public String getElectronConfiguration() {
		return eConfig.replaceAll("_", " ");
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getMass() {
		return mass;
	}

	public int getAtomicNumber() {
		return anumber;
	}

	public String getMassString() {
		return getMass() + " g/mol";
	}

	public String getDisplayString(int mode) {
		switch (mode) {
		case Game.MOLAR_MASS_MODE:
			return getName() + " (" + getSymbol() + ")";
		case Game.SYMBOL_MODE:
			return getName();
		case Game.ATOMIC_MODE:
			return getName();
		case Game.ELECTRON_CONFIG_MODE:
			return getElectronConfiguration();
		}
		return "ERROR";
	}

	public String getTargetDisplayString(int mode) {
		switch (mode) {
		case Game.MOLAR_MASS_MODE:
			return getMassString();
		case Game.SYMBOL_MODE:
			return getSymbol();
		case Game.ATOMIC_MODE:
			return "" + getAtomicNumber();
		case Game.ELECTRON_CONFIG_MODE:
			return getName();
		}
		return "ERROR";
	}
}
