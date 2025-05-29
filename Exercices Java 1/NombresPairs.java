public class NombresPairs {
    public static void main(String[] args) {
        // On va parcourir les nombres de 1 à 20
        for (int i = 1; i <= 20; i++) {
            // Condition : si i est pair (divisible par 2 sans reste)
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
