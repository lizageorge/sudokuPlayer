import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{

    private JFrame frame;
    private GridBagConstraints constraints = new GridBagConstraints();

    public GUI() {

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
//        panel.setLayout(new GridBagLayout(constraints));
        panel.setLayout(new GridLayout(9, 9));

        Content content = new Content(1);
        JTextField[][] fieldMatrix = new JTextField[3][3];

        for (int i = 0; i < content.matrix.length; i++) {
            for (int j = 0; j < content.matrix[0].length; j++) {
                final int r = i;
                final int c = j;
                JTextField t;
                if (content.matrix[r][c].inked == false) { //this is a blank cell to be filled in
                    t = new JTextField();
                    t.getDocument().addDocumentListener(new DocumentListener() {
                        public void insertUpdate(DocumentEvent e) {
                            copy();
                        }
                        public void removeUpdate(DocumentEvent e) {
                            copy();
                        }
                        public void changedUpdate(DocumentEvent e) {
                            copy();
                        }

                        public void copy() {
                            if(t.getText().equals("") == false){
                                content.matrix[r][c].value = Integer.parseInt(t.getText());
                            }
//                            printMatrix(content.matrix);
                        }
                    });
                } else{ //this is a set/inked cell that cannot be changed
                    t = new JTextField(Integer.toString(content.matrix[r][c].value));
                    t.setEditable(false);
                }
                t.setHorizontalAlignment(JTextField.CENTER);
                Font font1 = new Font("SansSerif", Font.BOLD, 40);
//                t.setBackground(Color.cyan);
                t.setFont(font1);

//                fieldMatrix[i][j] = t;
                panel.add(t);
            }
        }


        frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku Viewer");
        frame.setSize(600, 600);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new GUI();
    }

    public static void printMatrix(Cell[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j].value);
            }
            System.out.println();
        }
    }

}
