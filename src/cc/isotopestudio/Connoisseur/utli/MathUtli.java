package cc.isotopestudio.Connoisseur.utli;

public class MathUtli {
	
	public static int random(int min, int max) {
		double ran = Math.random() * (max - min + 1) + min;
		return (int) ran;
	}

}
