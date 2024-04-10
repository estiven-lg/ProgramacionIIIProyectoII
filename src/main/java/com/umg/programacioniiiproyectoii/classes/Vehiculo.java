package com.umg.programacioniiiproyectoii.classes;

public class Vehiculo {
	private String plate;
	private String color;
	private String line;
	private String model;
	private String owner;

	public Vehiculo(String plate, String color, String line, String model, String owner) {
		this.plate = plate;
		this.color = color;
		this.line = line;
		this.model = model;
		this.owner = owner;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public static boolean validarPlacas(String placa) {
		String[] placaArray = placa.split("");
		if (placa.length() != 7) {
			return false;
		}

		if (placa.toLowerCase().charAt(0) != 'm' && placa.toLowerCase().charAt(0) != 'c'
				&& placa.toLowerCase().charAt(0) != 'p') {
			return false;
		}
		for (int i = 1; i != 4; i++) {
			if (!"0123456789".contains(placaArray[i])) {
				return false;
			}
		}
		for (int i = 4; i != 6; i++) {
			if (!"qwrtypsdfghjklzxcvbnm".contains(placaArray[i].toLowerCase())) {
				return false;
			}
		}
		return true;
	}

}
