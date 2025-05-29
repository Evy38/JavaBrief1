import java.util.Scanner;

public class Calculatrice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Demander le premier entier
        System.out.print("Entrez le premier entier : ");
        int a = scanner.nextInt();

        // 2. Demander le deuxième entier
        System.out.print("Entrez le deuxième entier : ");
        int b = scanner.nextInt();

        // 3. Calculer et afficher la somme
        System.out.println("Somme : " + (a + b));

        // 4. Calculer et afficher la différence
        System.out.println("Différence : " + (a - b));

        // 5. Calculer et afficher le produit
        System.out.println("Produit : " + (a * b));

        // 6. Calculer et afficher le quotient (en évitant la division par zéro)
        if (b != 0) {
            System.out.println("Quotient : " + (a / b));
        } else {
            System.out.println("Erreur : division par zéro impossible !");
        }

        scanner.close();
    }
}
