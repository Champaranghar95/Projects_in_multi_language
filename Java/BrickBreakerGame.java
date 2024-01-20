import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BrickBreakerGame extends JFrame implements KeyListener, ActionListener {
    private Timer timer;
    private int ballX = 120;
    private int ballY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private int playerX = 310;
    private boolean play = false;

    private int totalBricks = 21;

    public BrickBreakerGame() {
        setTitle("Brick Breaker Game");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 592, 392);

        // Bricks
        g.setColor(Color.yellow);
        g.fillRect(10, 50, 80, 20);
        g.fillRect(100, 50, 80, 20);
        g.fillRect(190, 50, 80, 20);
        g.fillRect(280, 50, 80, 20);
        g.fillRect(370, 50, 80, 20);
        g.fillRect(460, 50, 80, 20);

        // Paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 330, 100, 8);

        // Ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        if (totalBricks <= 0) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won", 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230, 350);
        }

        if (ballY > 350) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: " + totalBricks, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230, 350);
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 330, 100, 8))) {
                ballYDir = -ballYDir;
            }

            A: for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (bricks[i][j] > 0) {
                        int brickX = j * 80 + 10;
                        int brickY = i * 20 + 50;
                        int brickWidth = 80;
                        int brickHeight = 20;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);

                        if (ballRect.intersects(brickRect)) {
                            bricks[i][j] = 0;
                            totalBricks--;
                            if (ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballX += ballXDir;
            ballY += ballYDir;
            if (ballX < 0) {
                ballXDir = -ballXDir;
            }
            if (ballY < 0) {
                ballYDir = -ballYDir;
            }
            if (ballX > 570) {
                ballXDir = -ballXDir;
            }
        }

        repaint();
    }

    private int bricks[][] = new int[6][6];

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600 - 100) {
                playerX = 600 - 100;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballX = 120;
                ballY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                totalBricks = 21;

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        bricks[i][j] = 1;
                    }
                }

                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    public static void main(String[] args) {
        new BrickBreakerGame().setVisible(true);
    }
}

