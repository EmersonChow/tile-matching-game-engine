package TMGE;

import java.awt.Color;

abstract class Tile {
	Color color;
	
	public Tile(Color c){
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
}
