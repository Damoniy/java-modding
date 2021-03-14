package test;

public class main {

	public static void main(String[] args) {
        int idade[] = new int[50];
        for (int j = 0; j < 8; j+=2){
                idade[j] += j;
                System.out.println(idade[j]);
        }
	}

}
