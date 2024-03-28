import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.util.Scanner;

public class Proyecto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField IngresarTextoNombre;
    private String nombre;
    private char[][] laberinto;
    private int filaActual;
    private int columnaActual;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Proyecto frame = new Proyecto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Proyecto() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 232);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton ButtonEnter = new JButton("Enter");
        ButtonEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nombre = IngresarTextoNombre.getText();
                IngresarTextoNombre.setText("");
                ocultarInterfazUsuario();
                ejecutarLaberinto(nombre);
            }
        });
        ButtonEnter.setBounds(168, 106, 149, 31);
        contentPane.add(ButtonEnter);

        IngresarTextoNombre = new JTextField();
        IngresarTextoNombre.setBounds(125, 41, 263, 31);
        contentPane.add(IngresarTextoNombre);
        IngresarTextoNombre.setColumns(10);

        JLabel TextoNombre = new JLabel("Nombre");
        TextoNombre.setBounds(21, 45, 62, 23);
        contentPane.add(TextoNombre);

        laberinto = new char[][] {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', 'X', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '☆', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        filaActual = 1;
        columnaActual = 1;
    }

    public void mostrarLaberinto() {
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                System.out.print(laberinto[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void moverArriba() {
        if (laberinto[filaActual - 1][columnaActual] != '#') {
            laberinto[filaActual][columnaActual] = ' ';
            filaActual--;
            laberinto[filaActual][columnaActual] = 'X';
        } else {
            System.out.println("Llegaste al tope");
        }
    }

    public void moverAbajo() {
        if (laberinto[filaActual + 1][columnaActual] != '#') {
            laberinto[filaActual][columnaActual] = ' ';
            filaActual++;
            laberinto[filaActual][columnaActual] = 'X';
        } else {
            System.out.println("Llegaste al tope");
        }
    }

    public void moverIzquierda() {
        if (laberinto[filaActual][columnaActual - 1] != '#') {
            laberinto[filaActual][columnaActual] = ' ';
            columnaActual--;
            laberinto[filaActual][columnaActual] = 'X';
        } else {
            System.out.println("Llegaste al tope");
        }
    }

    public void moverDerecha() {
        if (laberinto[filaActual][columnaActual + 1] != '#') {
            laberinto[filaActual][columnaActual] = ' ';
            columnaActual++;
            laberinto[filaActual][columnaActual] = 'X';
        } else {
            System.out.println("Llegaste al tope");
        }
    }

    public boolean haGanado() {
        return filaActual == 7 && columnaActual == 8;
    }

    private void ocultarInterfazUsuario() {
        setVisible(false);
    }

    private void ejecutarLaberinto(String nombreUsuario) {
        Scanner scanner = new Scanner(System.in);
        char movimiento;

        do {
            mostrarLaberinto();
            System.out.println("¿Dónde quieres moverte? (W - arriba, S - abajo, A - izquierda, D - derecha)");
            movimiento = scanner.next().charAt(0);

            switch (movimiento) {
                case 'W':
                case 'w':
                    moverArriba();
                    break;
                case 'S':
                case 's':
                    moverAbajo();
                    break;
                case 'A':
                case 'a':
                    moverIzquierda();
                    break;
                case 'D':
                case 'd':
                    moverDerecha();
                    break;
                default:
                    System.out.println("Movimiento inválido.");
            }
        } while (!haGanado());

        System.out.println("¡Felicidades, " + nombreUsuario + ", has escapado del laberinto!");
        
        scanner.close();
        
        private void guardarNombreEnArchivo(String nombreUsuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("nombres.txt", true))) {
            writer.write(nombreUsuario + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}