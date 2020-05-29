import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    Content content;

    private int version;
    private int[] versionKEY;

    public GUI() {
        //TODO turn choosing this and version key  into buttons
        version = 2;

        content = new Content(version);
        JTextField[][] fieldMatrix = new JTextField[9][9];

        versionKEY = content.test2KEY;

        for (int i = 0; i < content.matrix.length; i++) {
            for (int j = 0; j < content.matrix[0].length; j++) {
                final int r = i;
                final int c = j;
                JTextField t;
                if (content.matrix[r][c].inked == false) { //this is a blank cell to be filled in
                    t = new JTextField();
                } else{ //this is a set/inked cell that cannot be changed
                    t = new JTextField(Integer.toString(content.matrix[r][c].value));
                    t.setEditable(false);
                }
                t.getDocument().addDocumentListener(new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        onChange();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        onChange();
                    }
                    public void changedUpdate(DocumentEvent e) {
                        onChange();
                    }

                    public void onChange() {
                        if(t.getText().equals("") == false){ //if the character wasn't just deleter
                            content.matrix[r][c].value = Integer.parseInt(t.getText());

                            //TODO make all these colours prettier
//                            check for completed row
                            if(content.checkCompletedRow(versionKEY, r)){
//                                t.setBackground(Color.cyan);
                                for (int k = 0; k < fieldMatrix[0].length; k++) {
                                    fieldMatrix[r][k].setBackground(Color.cyan);
                                }
                            }else{
                                for (int k = 0; k < fieldMatrix[0].length; k++) {
                                    if( content.matrix[r][k].inked == false) {
                                        fieldMatrix[r][k].setBackground(Color.white);
                                    }else{
                                        //TODO fix this gray!!
                                        fieldMatrix[r][k].setBackground(Color.LIGHT_GRAY);
                                    }
                                }
                            }

                            //check for completed column
                            if(content.checkCompletedColumn(versionKEY, c)){
//                                t.setBackground(Color.cyan);
                                for (int l = 0; l < fieldMatrix.length; l++) {
                                    fieldMatrix[l][c].setBackground(Color.cyan);
                                }
                            }else{
                                for (int l = 0; l < fieldMatrix[0].length; l++) {
                                    if( content.matrix[l][c].inked == false) {
                                        fieldMatrix[l][c].setBackground(Color.white);
                                    }else{
                                        //TODO fix this gray!!
                                        fieldMatrix[l][c].setBackground(Color.LIGHT_GRAY);
                                    }
                                }
                            }
                            //check for completed box
                        }
                            printMatrix(content.matrix);
                    }
                });


                t.setHorizontalAlignment(JTextField.CENTER);
                Font font1 = new Font("SansSerif", Font.BOLD, 40);
                t.setFont(font1);

                fieldMatrix[i][j] = t;
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

        //Header
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

        //Footer
        JPanel footer = new JPanel();
        JButton checkForCompletion = new JButton("Check if I'm done!");
        checkForCompletion.addActionListener(this);
        footer.add(checkForCompletion);

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        outerPanel.add(header);
        outerPanel.add(gridPanel);
        outerPanel.add(footer);

        JFrame frame = new JFrame();
        frame.add(outerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku Viewer");
        frame.setSize(660, 900);
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
        System.out.println();
        System.out.println("-------------------------");
        System.out.println();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean correct = content.matches(versionKEY);

        if(correct){
            JOptionPane.showMessageDialog(null, "You got it~");
        }else{
            JOptionPane.showMessageDialog(null, "Not quite.");
        }
    }
}
