public class NoLeakSimulator {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            // Crée un bloc de 1MB
            byte[] block = new byte[1024 * 1024];
            // Utilisation de la variable block pour éviter l'avertissement
            block[0] = 1;

            // Pas de stockage dans une liste, la référence disparaît après chaque tour
            System.out.println("Allocated " + (i + 1) + " MB");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
