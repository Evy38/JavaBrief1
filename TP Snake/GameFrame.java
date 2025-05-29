import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame() {
        this.add(new GamePanel()); // ajoute le panneau de jeu
        this.setTitle("SNAKE"); // titre de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermer l’appli si on ferme la fenêtre
        this.setResizable(false); // taille fixe
        this.pack(); // ajuste la taille de la fenêtre selon son contenu
        this.setVisible(true); // rend la fenêtre visible
        this.setLocationRelativeTo(null); // centre la fenêtre à l’écran
    }
}
