package Trilingo;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TrilingoFrame extends JFrame implements TrilingoConstants{

    private Trilingo trilingo;
    private JTextField fromTextField;
    private JTextField toTextField;

    public TrilingoFrame(Trilingo trilingo) throws IOException {
        this.trilingo = trilingo;

        initNorthCanvas();
        initEastCanvas();
        initTable();


        setMinimumSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initTable() {
//        System.out.println("sizeee: " + trilingo.model.getRowCount() );
//        trilingo.model.fireTableDataChanged();
        String[] names = {"From word", "Word to guess"};
        JTable table = new JTable(trilingo.model);
        JScrollPane pane = new JScrollPane(table);
        add(pane);
    }

    private void initNorthCanvas(){
        JPanel northPanel = new JPanel();
        initFromWord(northPanel);
        initToWord(northPanel);
        initAddWordButton(northPanel);
        add(northPanel, BorderLayout.NORTH);
    }

    private void initEastCanvas() throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0,150)));

        initNextWordButton(panel);
        initShowAnswerButton(panel);
        panel.add(Box.createRigidArea(new Dimension(0,150)));
        initReverseLanguageButton(panel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        initFinishButton(panel);

        add(panel, BorderLayout.EAST);
    }

    private void initNextWordButton(JPanel panel) {
        JButton button = new JButton("Ask me next word");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trilingo.askNextWord();
            }
        });
        panel.add(button);
    }

    private void initShowAnswerButton(JPanel panel) {
        JButton button = new JButton("Show Answer");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("show answer");
                trilingo.showAnswer();
            }
        });
        panel.add(button);
    }

    private void initReverseLanguageButton(JPanel panel) {
        JButton button = new JButton("Reverse Languages");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("language reversed");
            }
        });
        panel.add(button);
    }

    private void initFinishButton(JPanel panel) throws IOException {
        JButton button = new JButton("Finish");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trilingo.finish();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(button);
    }



    private void initFromWord(JPanel panel){
        panel.add(new JLabel(FROM_WORD_NAME));
        fromTextField = new JTextField(TEXT_FIELD_SIZE);
        panel.add(fromTextField);
        panel.add(new JLabel(EMPTY_LABEL));
    }

    private void initToWord(JPanel panel){
        panel.add(new JLabel(TO_WORD_NAME));
        toTextField = new JTextField(TEXT_FIELD_SIZE);
        panel.add(toTextField);
        panel.add(new JLabel(EMPTY_LABEL));
    }

    private void initAddWordButton(JPanel panel){
        JButton addButton = new JButton("Add Word To Lexicon");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromWord = fromTextField.getText();
                fromTextField.setText(DEFAULT_TEXT);
                String toWord = toTextField.getText();
                toTextField.setText(DEFAULT_TEXT);
                try {
                    trilingo.addWordToLexicon(fromWord, toWord);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(addButton);
    }

}
