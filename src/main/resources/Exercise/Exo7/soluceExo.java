package Exo7;

public class soluceExo {
	private final int[] array;

	public soluceExo(int[] Array) {
		this.array = Array ;
	}

	public float array_moy(){
		if(this.array.length == 0){
			return 0;
		}
		float sum = 0;
		for (int i : this.array) {
			sum += i;
		}
		return sum/this.array.length;
	}
}