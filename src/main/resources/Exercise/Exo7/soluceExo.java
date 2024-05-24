package Exo7;

public class soluceExo{
	public float array_moy(int[] array, int size){
		if(size == 0){
			return 0;
		}
		float sum = 0;
		for (int i : array) {
			sum += i;
		}
		return sum/size;
	}
}
