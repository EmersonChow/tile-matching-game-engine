package Memory;
import java.awt.Color;

import TMGE.Tile;

class MemoryTile extends Tile {
	
	boolean active;
	
	MemoryTile(Color c, boolean act) {
		super(c);
		active = act;
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

}
