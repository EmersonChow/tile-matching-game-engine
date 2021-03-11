package Bejeweled;

import TMGE.Tile;

import java.awt.Color;

public class BejeweledTile extends Tile {
	
	int row;
	int column;
	
	BejeweledTile(Color c,int r,int col) {
		super(c);
		row = r;
		column = col;
	}
}
