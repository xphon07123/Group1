import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.*; //here
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.lang.reflect.Array;

public class Dashboard extends JFrame {

    private JFrame window;
    private JPanel leftside, urlPanel, additionalPanel, totbuttons, enterPanel, outputDisplay, spacingPanel;
    private JPanel rightside;

    private JTextArea labels, Display, totals;
    private JScrollPane scroll;

    private JLabel githubUrlInput, additionalInput, character, word, line, commentLine, sourceLine, space;
    private JTextField urlinput, additionalText;
    private JCheckBox check1, check2, check3, check4, check5;
    private JButton enter, clear, exit;
    private String completeUrl;

    private boolean initialized = false;
    /*
    Creates the Gui and sets it to visible
    */
    public static void main(String args[]) {
        new Dashboard().setVisible(true);
    }

    /*
    starts to build the GUI by setting it to visible
    */
    public void setVisible(boolean visible) {
        initialize();
        // super.setVisible(visible);
    }

    /*
    Initializes the GUI then sets how the program is meant to close
    */
    private void initialize() {
        initializeGui();
        //initializeEvents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /*
    Creates the Gui.
    This does just about everything about establishing the gui and setting which buttons does what.
    it also adds the different panels and buttons together
    */
    private void initializeGui() {

        if (initialized) {
            return;
        }

    /*
    sets the layout and initial size of the window that the GUI will be displayed in
    For the purpose of having as much room as possible the initial size will be the entire screen size of the given
    computer
    */
        initialized = true;
        window = new JFrame("Metrics");
        window.setLayout(new FlowLayout(FlowLayout.LEADING));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize((int)screenSize.getWidth(),300);
     /*
     establishes all the panel that will be put together and establish their layout.
     see design document to see how they work together
     */
        leftside = new JPanel();
        leftside.setLayout(new BoxLayout(leftside, BoxLayout.Y_AXIS));
        leftside.setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight());

        rightside = new JPanel();
        rightside.setLayout(new FlowLayout(FlowLayout.LEADING));
        rightside.setSize((int)screenSize.getWidth()/2, (int)screenSize.getHeight());

        urlPanel = new JPanel();
        urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.Y_AXIS));

        additionalPanel = new JPanel();
        additionalPanel.setLayout(new BoxLayout(additionalPanel, BoxLayout.Y_AXIS));

        totbuttons = new JPanel();
        GridLayout grid = new GridLayout(2, 3);
        totbuttons.setLayout(grid);

        enterPanel = new JPanel();
        enterPanel.setLayout(new BoxLayout(enterPanel,BoxLayout.X_AXIS));

        spacingPanel = new JPanel();
        spacingPanel.setLayout(new BoxLayout(spacingPanel, BoxLayout.Y_AXIS));

        outputDisplay = new JPanel();
        GridLayout grid2 = new GridLayout(1, 2);
        outputDisplay.setLayout(grid2);

    /*
    puts together all of the buttons and check boxes
    establishes what each button should do and what the label is
    */
        githubUrlInput = new JLabel("Enter Github URLs separated by commas");
        urlPanel.add(githubUrlInput);
        urlinput = new JTextField(30);
        urlinput.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        completeUrl = urlinput.getText();
                    }
                });
        urlPanel.add(urlinput);
        leftside.add(urlPanel);

        check1 = new JCheckBox("Characters");
        check2 = new JCheckBox("Words");
        check3 = new JCheckBox("Lines");
        check4 = new JCheckBox("Source Lines");
        check5 = new JCheckBox("CommentLines");

    /*
    additional input panel is just meant to take in file types to be displayed delimited by a space
    */
        additionalInput = new JLabel("Enter file suffix to search (EX: .java .txt .c)");
        additionalText = new JTextField(30);
        additionalPanel.add(additionalInput);
        additionalPanel.add(additionalText);
        leftside.add(additionalPanel);
    /*
    combining buttons and panels together to create GUI layers
    */
        totbuttons.setLayout(new BoxLayout(totbuttons, BoxLayout.Y_AXIS));
        totbuttons.add(check1);
        totbuttons.add(check2);
        totbuttons.add(check3);
        totbuttons.add(check4);
        totbuttons.add(check5);
        leftside.add(totbuttons);

        //enter button runs the process to output the metrics
        enter = new JButton("Enter");
        enterPanel.add(enter);

        space = new JLabel("   ");
        enterPanel.add(space);

        //clear button clears all areas of text and resets buttons
        clear = new JButton("Clear");
        enterPanel.add(clear);

        space = new JLabel("   ");
        enterPanel.add(space);

        exit = new JButton("Exit");
        enterPanel.add(exit);

        leftside.add(enterPanel);
        window.add(leftside);

        labels = new JTextArea("");
        labels.setSize(50,200);
        labels.setEditable(false);
        Display = new JTextArea("");
        Display.setEditable(false);
        totals = new JTextArea("");
        totals.setEditable(false);
        totals.setSize(50,200);
        JScrollPane scroll = new JScrollPane(Display);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(600,195));
        rightside.add(labels);
        rightside.add(scroll);
        rightside.add(totals);
        window.add(rightside);
        window.setVisible(true);


        /*
        The below code will be moved to another method in order to use them properly

         */
//        character = new JLabel("Characters");
//        outputDisplay.add(character);
//        character.setVisible(false);
//        leftside.add(outputDisplay);
//
//        word = new JLabel("Words");
//        outputDisplay.add(word);
//        word.setVisible(false);
//        leftside.add(outputDisplay);
//
//        line = new JLabel("Lines");
//        outputDisplay.add(line);
//        line.setVisible(false);
//        leftside.add(outputDisplay);
//
//        commentLine = new JLabel("Comment lines");
//        outputDisplay.add(commentLine);
//        commentLine.setVisible(false);
//        leftside.add(outputDisplay);
//
//        sourceLine = new JLabel("Source lines");
//        outputDisplay.add(sourceLine);
//        sourceLine.setVisible(false);
//        leftside.add(outputDisplay);

        enter.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        if(check1.isSelected() == false){
//                            character.setForeground(Color.gray);
//                        }
//
//                        if(check2.isSelected() == false){
//                            word.setForeground(Color.gray);
//                        }
//
//                        if(check3.isSelected() == false){
//                            line.setForeground(Color.gray);
//                        }
//
//                        if(check4.isSelected() == false){
//                            commentLine.setForeground(Color.gray);
//                        }
//
//                        if(check5.isSelected() == false){
//                            sourceLine.setForeground(Color.gray);
//                        }

//                        character.setVisible(true);
//                        word.setVisible(true);
//                        line.setVisible(true);
//                        commentLine.setVisible(true);
//                        sourceLine.setVisible(true);

                        displayData();

                        check1.setSelected(false);
                        check2.setSelected(false);
                        check3.setSelected(false);
                        check4.setSelected(false);
                        check5.setSelected(false);
                        urlinput.setText(null);
                        additionalText.setText(null);
                    }
                });


        clear.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Display.setText(null);
                        totals.setText(null);
                        labels.setText(null);
                        check1.setSelected(false);
                        check2.setSelected(false);
                        check3.setSelected(false);
                        check4.setSelected(false);
                        check5.setSelected(false);
                        urlinput.setText(null);
                        additionalText.setText(null);

                        //character.setForeground(Color.black);
                        //word.setForeground(Color.black);
                        //line.setForeground(Color.black);
                        //commentLine.setForeground(Color.black);
                        //sourceLine.setForeground(Color.black);

                        //character.setVisible(false);
                        //word.setVisible(false);
                        //line.setVisible(false);
                        //commentLine.setVisible(false);
                        //sourceLine.setVisible(false);

                    }
                });

        exit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      dispose();
                    }
                });
    }

    /*
    This should be a way to close the program that can be called should we want to add a close button, but it might go
    unused for the purpose of this project
    */
    public void dispose() {
        super.dispose();
        System.exit(0);
    }

    /*
    this method will call the GitParser and pass in the github url as a parameter.
    it will then get back the necessary metrics from GitParser and display it in the scrollable text area and the totals
    text area.
    */
    private void displayData() {
        String[][] current = new String[0][0];
        try{
            FileManager manager = new FileManager(current,getDisplaySettings(),getDisplayAll());
            labels.setText(manager.getRowLabels());
            Display.setText(manager.getFileDisplay());
            totals.setText(String.valueOf(manager.getTotals()));
        }catch(Exception e){
            Display.setText(" An error has occurred. \n Make sure that you entered a valid github URL and/or search criteria ");
        }
    }


    /*
    This returns the booleans of all dispaly items for the output.
    The isSelected is used to go around an action listener and use the functionality of the JCheckBox
    */
    private boolean[] getDisplaySettings(){
        boolean [] displaySettings = new boolean [5];
        boolean displayAll = getDisplayAll();
        displaySettings [0] = (check1.isSelected() || displayAll);
        displaySettings [1] = (check2.isSelected() || displayAll);
        displaySettings [2] = (check3.isSelected() || displayAll);
        displaySettings [3] = (check4.isSelected() || displayAll);
        displaySettings [4] = (check5.isSelected() || displayAll);
        return displaySettings;
    }

    /*
    checks to see if all of the checkboxes are unselected to diplay the proper information
     */
    private boolean getDisplayAll(){
        boolean displayAll = false;
        if(!check1.isSelected() && !check2.isSelected() && !check3.isSelected() && !check4.isSelected() && !check5.isSelected()){
            displayAll = true;
        }

        return displayAll;
    }

}
