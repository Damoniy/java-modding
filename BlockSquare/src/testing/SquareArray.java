package testing;

import java.util.Random;

public class SquareArray {

	public static enum type{
		SURFACE(0),
		DIRT(1),
		ROCK(2),
		ORE(3);
		
		Random rand = new Random();
		
		int blockId;
		
		type(int id){
			blockId = id;
		}
		
		public int getBlockId() {
			return this.blockId;
		}
		
		public int generateRandomId() {
			return getBlockId() + rand.nextInt(2);
		}
		
		public String genRocks() {
			return "S"; 
		}
		
		public String genOres() {
			return "O";
		}
		
		public int getRandomHeight() {
			return ROCK.getBlockId() + this.rand.nextInt(15);
		}
	}
	
	public static void main(String[] args) {
		String[][] blocks = new String[9][20];
				
		System.out.println();
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 20; j++) {
				
				blocks[i][j] = type.ROCK.genRocks();
				
				if(i == type.SURFACE.getBlockId()) {
					blocks[i][j] = "G";
				} else if(i == type.DIRT.generateRandomId()) {
					blocks[i][j] = "D";
				} else if(i == type.DIRT.getBlockId()){
					if(blocks[i][j] == "S") {
						blocks[i][j] = "D";
					}
				} else if(i >= type.ROCK.getRandomHeight()) {
					if(blocks[i][j] == "S") {
						blocks[i][j] = type.ORE.genOres();
					}
				}
												
				System.out.printf("[%s]", blocks[i][j]);
				if(j == blocks[0].length - 1)
					System.out.println();
			}
		}
	}
}
