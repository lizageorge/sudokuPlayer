import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.DeflaterInputStream;

public class GUI{
    public GUI() {

        Content content = new Content(1);
        JTextField[][] fieldMatrix = new JTextField[9][9];

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

                fieldMatrix[i][j] = t;
//                gridPanel.add(t);
            }
        }

        JPanel gridPanel = new JPanel();
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 30, 30));
        gridPanel.setLayout(new GridLayout(3, 3));
        gridPanel.setPreferredSize(new Dimension(600, 600));
        gridPanel.setMaximumSize(gridPanel.getPreferredSize());
        gridPanel.setMinimumSize(gridPanel.getPreferredSize());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel innerPanel = new JPanel();
                innerPanel.setLayout(new GridLayout(3, 3));

                for (int k = i*3; k < i*3 + 3; k++) {
                    for (int l = j*3; l < j*3 + 3; l++) {
                        innerPanel.add(fieldMatrix[k][l]);
                    }
                }

                innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(innerPanel);
            }
        }

        //Title
        JPanel header = new JPanel();
        JLabel title = new JLabel("Sudoku Engine!");
        Font font2 = new Font("SansSerif", Font.BOLD, 50);
        title.setFont(font2);
        header.add(title);
        JLabel description = new JLabel("<html>Enter numbers into the blank spaces so that each row, <br> column and 3x3 box contains the numbers 1 to 9 without repeats.</html>");
        Font font1 = new Font("SansSerif", Font.ITALIC, 15);
        description.setFont(font1);
        header.add(description);
        header.setBorder(BorderFactory.createEmptyBorder(15, 80, 0, 80));

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.add(header);
        outerPanel.add(gridPanel);

        JFrame frame = new JFrame();
        frame.add(outerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku Viewer");
        frame.setSize(660, 800);
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
