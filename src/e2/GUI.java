package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame {
    
    @Serial
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size, int mines) {
        this.logics = new LogicsImpl(size, mines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = this.buttons.get(bt);
            boolean aMineWasFound = this.logics.hasMine(pos); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                this.quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                bt.setEnabled(false);
                this.logics.showNumber(pos);
                this.drawBoard();
            }
            boolean isThereVictory = this.logics.isThereVictory(); // call the logic here to ask if there is victory
            if (isThereVictory) {
                this.quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = GUI.this.buttons.get(bt);
                    GUI.this.logics.flag(pos);
                }
                GUI.this.drawBoard();
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a mine, draw it "*"
            if (this.logics.hasMine(entry.getValue())) {
                entry.getKey().setText("*");
            }
            // disable the button
            entry.getKey().setEnabled(false);
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            String string = this.logics.hasNumber(entry.getValue()) ?
                    String.valueOf(this.logics.getNumber(entry.getValue())) :
                    this.logics.hasFlag(entry.getValue()) ? "F" : "";
            entry.getKey().setText(string);
    	}
    }
    
}
