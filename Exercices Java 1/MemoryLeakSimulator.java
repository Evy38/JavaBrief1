import java.util.ArrayList;  // Import de la classe ArrayList pour gérer une liste dynamique
import java.util.List;       // Import de l'interface List

public class MemoryLeakSimulator {
    // Liste statique qui conserve toutes les références aux blocs de mémoire alloués
    // Comme cette liste est statique, elle vit pendant toute la durée du programme
    private static List<byte[]> memoryLeakList = new ArrayList<>();

    public static void main(String[] args) {
        simulateMemoryLeak();  // Appel de la méthode qui simule la fuite mémoire
    }

    public static void simulateMemoryLeak() {
        // Boucle qui va créer 1000 blocs de mémoire de 1 MB chacun
        for (int i = 0; i < 1000; i++) {
            // Création d'un tableau de bytes de taille 1 million, soit environ 1MB
            byte[] block = new byte[1024 * 1024];

            // Ajout du bloc dans la liste statique
            // Cette référence empêche le garbage collector de libérer la mémoire de ce bloc
            memoryLeakList.add(block);

            // Affiche le nombre de MB alloués jusqu'ici
            System.out.println("Allocated " + (i + 1) + " MB");

            try {
                // Pause de 100 millisecondes pour ralentir la boucle et observer la progression
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // En cas d'interruption de la pause, affiche la trace de l'erreur
                e.printStackTrace();
            }
        }
    }
}
