import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI
{
    Content content;

    private String version;
    private int[][] versionKEY;

    private JButton easyButton, mediumButton, hardButton, checkForCompletion;
    private JFrame frame;
    private JPanel header;

    //TODO create dark mode

    // COLOR PALETTE:
    final Color blank = new Color(255, 255, 255);
    final Color inked = new Color(240, 244, 245);
    final Color correct = new Color(219, 240, 204);
    final Color incorrect = new Color(255, 182, 173);
    final Color background = new Color(239, 240, 235);
    final Color black = new Color(3, 42, 48);
    final Color buttons = new Color(202, 206, 204);

    public GUI() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku Viewer");
        frame.setSize(660, 300);
        frame.setResizable(false);

        //Header
        header = new JPanel();
        JLabel title = new JLabel("Sudoku Engine!");
        Font font2 = new Font("SansSerif", Font.BOLD, 50);
        title.setFont(font2);
        title.setForeground(black);
        header.add(title);
        JLabel description = new JLabel("<html>Enter numbers into the blank spaces so that each row, <br> column and 3x3 box contains the numbers 1 to 9 without repeats.</html>");
        Font font1 = new Font("SansSerif", Font.ITALIC, 15);
        description.setFont(font1);
        description.setForeground(black);
        header.add(description);
        header.setBorder(BorderFactory.createEmptyBorder(15, 80, 0, 80));
        header.setBackground(background);

        //user picking a version;
        JPanel versionPicker = new JPanel();
        frame.add(versionPicker);
        JLabel pickVersion = new JLabel("Please pick a difficulty: ");
        versionPicker.add(pickVersion);
        easyButton = new JButton("Easy");
        easyButton.addActionListener(new ButtonListener());
        mediumButton = new JButton("Intermediate");
        mediumButton.addActionListener(new ButtonListener());
        hardButton = new JButton("Hard");
        hardButton.addActionListener(new ButtonListener());
        versionPicker.add(easyButton);
        versionPicker.add(mediumButton);
        versionPicker.add(hardButton);

        header.add(versionPicker);
        frame.add(header);
        frame.setVisible(true);

        //TODO set resizing off


        System.out.println(version);

    }

    private void play() {
        System.out.println("play method reached");

        JTextField[][] fieldMatrix = new JTextField[9][9];

        //creating the grid
        for (int i = 0; i < content.matrix.length; i++) {
            for (int j = 0; j < content.matrix[0].length; j++) {
                final int r = i;
                final int c = j;
                JTextField t;
                if (content.matrix[r][c].inked == false) { //this is a blank cell to be filled in
                    t = new JTextField();
                    t.setBackground(blank);
                } else { //this is a set/inked cell that cannot be changed
                    t = new JTextField(Integer.toString(content.matrix[r][c].value));
                    t.setEditable(false);
                    t.setBackground(inked);
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
                        if (t.getText().equals("") == false) { //if the 'character' wasn't just a temporary blank after a delete
                            content.matrix[r][c].value = Integer.parseInt(t.getText());
//                            printMatrix(content.matrix);
                        }

                        //check for completed box
                        if(content.checkCompleteBox(versionKEY, r, c)){
                            t.setBackground(Color.cyan);
                            int rowStart = r - r%3;
                            int columnStart = c - c%3;
                            for (int i = rowStart; i < rowStart+3; i++) {
                                for (int j = columnStart; j < columnStart+3; j++) {
                                    fieldMatrix[i][j].setBackground(correct);
                                }
                            }
                        }else{
                            int rowStart = r - r%3;
                            int columnStart = c - c%3;
                            for (int i = rowStart; i < rowStart+3; i++) {
                                for (int j = columnStart; j < columnStart+3; j++) {
                                    if(content.checkCompletedRow(versionKEY, r) == false && content.checkCompletedRow(versionKEY, r) == false ){
                                        if (content.matrix[i][j].inked == false) {
                                            fieldMatrix[i][j].setBackground(blank);
                                        } else {
                                            fieldMatrix[i][j].setBackground(inked);
                                        }
                                    }
                                }
                            }
                        }

                        //TODO make the grid return to normal if char deleted.
                        //check for completed row
                        if (content.checkCompletedRow(versionKEY, r)) {
                            for (int k = 0; k < fieldMatrix[0].length; k++) {
                                fieldMatrix[r][k].setBackground(correct);
                            }
                        } else {
                            for (int k = 0; k < fieldMatrix[0].length; k++) {
                                if(!content.checkCompleteBox(versionKEY, r, c)){
                                    if (content.matrix[r][k].inked == false) {
                                        fieldMatrix[r][k].setBackground(blank);
                                    } else {
                                        fieldMatrix[r][k].setBackground(inked);
                                    }
                                }

                            }
                        }
//
                        //check for completed column
                        if (content.checkCompletedColumn(versionKEY, c)) {
                            for (int l = 0; l < fieldMatrix.length; l++) {
                                fieldMatrix[l][c].setBackground(correct);
                            }
                        } else {
                            for (int l = 0; l < fieldMatrix[0].length; l++) {
                                if (content.checkCompletedRow(versionKEY, r) == false && !content.checkCompleteBox(versionKEY, r, c)) {
                                    if (content.matrix[l][c].inked == false) {
                                        fieldMatrix[l][c].setBackground(blank);
                                    } else {
                                        fieldMatrix[l][c].setBackground(inked);
                                    }

                                }
                            }
                        }
                    }
                });


                t.setHorizontalAlignment(JTextField.CENTER);
                Font font3 = new Font("SansSerif", Font.BOLD, 40);
                t.setFont(font3);

                fieldMatrix[i][j] = t;
            }
        }

        JPanel gridPanel = new JPanel();
        gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 30, 30, 30));
        gridPanel.setLayout(new GridLayout(3, 3));
        gridPanel.setPreferredSize(new Dimension(600, 600));
        gridPanel.setMaximumSize(gridPanel.getPreferredSize());
        gridPanel.setMinimumSize(gridPanel.getPreferredSize());
        gridPanel.setBackground(background);
        gridPanel.setForeground(black);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel innerPanel = new JPanel();
                innerPanel.setLayout(new GridLayout(3, 3));

                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        innerPanel.add(fieldMatrix[k][l]);
                    }
                }

                innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(innerPanel);
            }
        }

        //Footer
        JPanel footer = new JPanel();
        checkForCompletion = new JButton("Check if I'm done!");
        checkForCompletion.addActionListener(new ButtonListener());
        footer.add(checkForCompletion);
        footer.setBackground(background);

        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.PAGE_AXIS));
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        outerPanel.add(header);
        outerPanel.add(gridPanel);
        outerPanel.add(footer);
        outerPanel.setBackground(background);

        frame.setSize(660, 1000);
        frame.add(outerPanel, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        new GUI();
    }

    public static void printMatrix(Cell[][] m) {
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

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == checkForCompletion){
                System.out.println("completion button listener reached");
                boolean correct = content.matches(versionKEY);

                if (correct) {
                    JOptionPane.showMessageDialog(null, "You got it~");
                } else {
                    JOptionPane.showMessageDialog(null, "Not quite.");
                }
            }else if(e.getSource() == easyButton){
//                System.out.println("easy button listener reached");
                version = "easy";
                content = new Content(version);
                versionKEY = content.easyKEY;
                play();
            }else if(e.getSource() == mediumButton){
//                System.out.println("medium button listener reached");
                double rand = Math.random();
                if(rand < 0.5){
                    version = "medium1";
                    content = new Content(version);
                    versionKEY = content.medium1KEY;
                    System.out.println("medium 1");
                }else{
                    version = "medium2";
                    content = new Content(version);
                    versionKEY = content.medium2KEY;
                    System.out.println("medium 2");
                }

                play();
            }else if(e.getSource() == hardButton){
//                System.out.println("hard button listener reached");

                version = "hard";
                content = new Content(version);
                versionKEY = content.hard1KEY;
                play();
            }
        }
    }
}

