import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // Constantes pour la taille de la fenêtre et des cases
    static final int SCREEN_WIDTH = 600;        // largeur du jeu  
    static final int SCREEN_HEIGHT = 600;        // hauteur du jeu
    static final int UNIT_SIZE = 25;              // taille d'une case (pixel)

    // Nombre total d'unités (cases) dans le jeu
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    // Délai entre chaque mouvements du serpent
    static final int DELAY = 75;                 

    // Tableaux pour stocker les coordonnées x et y de chaque partie du serpent
    final int x[] = new int[GAME_UNITS];         
    final int y[] = new int[GAME_UNITS];         

    int bodyParts = 6;          // Taille initiale du serpent (nombre de segments)
    int applesEaten;            // Score, nombre de pommes mangées
    int appleX;                 // Position x de la pomme
    int appleY;                 // Position y de la pomme
    char direction = 'R';       // Direction initiale du serpent ('R' droite, 'L' gauche, 'U' haut, 'D' bas)
    boolean running = false;    // Indique si le jeu est en cours ou terminé
    Timer timer;                // Timer Swing qui contrôle le rythme du jeu
    Random random;              // Générateur de nombres aléatoires pour la pomme

    // Constructeur : initialise le panel
    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // taille du panel
        this.setBackground(Color.pink);    // fond noir
        this.setFocusable(true);             // pour capter le clavier
        this.addKeyListener(new MyKeyAdapter()); // écouteur clavier
        startGame();                        // démarre le jeu
    }

    // Méthode pour démarrer le jeu : place la pomme, lance le timer, etc.
    public void startGame() {
        newApple();               // place une pomme au hasard
        running = true;           // indique que le jeu est actif
        timer = new Timer(DELAY, this);  // crée un timer qui déclenche actionPerformed toutes les DELAY ms
        timer.start();            // démarre le timer
    }

    // Dessine le panel, appelé automatiquement par Swing
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // nettoie l'écran avant de dessiner
        draw(g);                  // appelle notre méthode de dessin personnalisée
    }

    // Dessine serpent, pomme, score ou écran de fin
    public void draw(Graphics g) {
        if (running) {
            // Dessine la pomme en rouge
            g.setColor(Color.blue);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Dessine chaque segment du serpent
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.white); // tête du serpent en vert clair
                } else {
                    g.setColor(new Color(45, 180, 0)); // corps en vert foncé
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // carré représentant le segment
            }

            // Affiche le score en blanc en haut à gauche
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + applesEaten, 10, 20);

        } else {
            // Si le jeu est terminé, affiche "Game Over"
            gameOver(g);
        }
    }

    // Place une nouvelle pomme à une position aléatoire sur la grille
    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    // Déplace le serpent en décalant chaque segment à la position du précédent
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Déplace la tête selon la direction
        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    // Vérifie si la tête du serpent est sur la pomme
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;          // augmente la taille du serpent
            applesEaten++;        // augmente le score
            newApple();           // place une nouvelle pomme
        }
    }

    // Vérifie les collisions avec le corps ou les bords
    public void checkCollision() {
        // Collision tête-corps
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;  // fin du jeu
            }
        }

        // Collision avec les murs
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;      // fin du jeu
        }

        // Si le jeu est terminé, arrêter le timer
        if (!running) {
            timer.stop();
        }
    }

    // Affiche le message "Game Over" et le score final
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten)) / 2, SCREEN_HEIGHT / 2 + 50);
    }

    // Méthode appelée par le Timer à chaque tick
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollision();
        }
        repaint(); // demande à Swing de redessiner le panel
    }

    // Classe interne pour gérer les événements clavier
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') { // empêche de faire demi-tour direct
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}
