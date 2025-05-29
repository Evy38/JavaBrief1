import java.util.Scanner;

public class TableMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //objet pour lire ce que l'utilisateur tape dans la console

        // 1. Afficher un message pour demander un nombre
        System.out.print("Entrez un nombre : ");

        // 2. Lire le nombre saisi par l'utilisateur
        int nombre = scanner.nextInt();

        // 3. Faire une boucle de 1 à 10 pour afficher la table de multiplication
        for (int i = 1; i <= 10; i++) {
            // 4. Afficher la multiplication : nombre * i
            System.out.println(nombre + " x " + i + " = " + (nombre * i));
        }

        // 5. Fermer le scanner (bonne pratique)
        scanner.close();
    }
}
