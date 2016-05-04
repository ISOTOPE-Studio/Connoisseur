package cc.isotopestudio.Connoisseur.utli;

public class MathUtli {

	public static int random(int min, int max) {
		double ran = Math.random() * (max - min + 1) + min;
		return (int) ran;
	}

	public static double random(double min, double max) {
		double ran = Math.random() * (max - min) + min;
		return ran;
	}

	public static boolean random(double percent) {
		double ran = random(0.0, 1.0);
		return percent >= ran;
	}

}
