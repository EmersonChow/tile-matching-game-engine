import java.awt.Color;

public class Tile {
	Color color;
	boolean active = false;
	
	Tile(Color c){
		color = c;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void reveal() {
		active = true;
	}
	
	public void hide() {
		active = false;
	}
	
	public Color getColor() {
		return color;
	}
	
}
