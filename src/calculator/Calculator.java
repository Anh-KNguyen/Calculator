package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculator implements ActionListener {
    
    //display
    private JLabel display;
    
    //buttons
    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JButton bdivide, bmult, bminus, bplus, bequal, bclear;
    
    //variables used to calculate
    private String operator = "";
    private int operand1 = 0, operand2 = 0, result = 0;
    
    //conversion, string to int
    private String num_string1 = "", num_string2 = "";
    
    //frame
    private JFrame frame;
    
    //used to separate states. Only C button can return state for (c)name
    private boolean name_State = false;


    public Calculator() {
        
        //JFrame container
        frame = new JFrame("Calculator");
        
        //Terminate program when user closes application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //user cannot resize
        frame.setResizable(false);
        
        //icon image for application icon
        ImageIcon icon = new ImageIcon("Calculator.png");
        frame.setIconImage(icon.getImage());
        
        //set initial value 0 and right justify value
        display = new JLabel("0");
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        
        //Courier font, 20-point font
        display.setFont(new Font("Courier", Font.PLAIN, 20));
        
        //no transparency
        display.setOpaque(true);
        
        //red border, 4-pixel thick
        display.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        
        //black background
        display.setBackground(Color.black);
        
        //red text
        display.setForeground(Color.red);
        
        //display size
        display.setPreferredSize(new Dimension(200,60));
            
        //width of image
        int width = new ImageIcon("1.png").getIconWidth() * 4;
        
        //panel using gridlayout
        JPanel panel = new JPanel(new GridLayout(4, 4));
        
        //preferred size of calculator on startup
        panel.setPreferredSize(new Dimension(width, width));
        
        //Buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bdivide = new JButton("/");
        bmult = new JButton("*");
        bminus = new JButton("-");
        bplus = new JButton("+");
        bclear = new JButton("C");
        bequal = new JButton("=");

        //Set Mnemonic for Alt + C key
        bclear.setMnemonic(KeyEvent.VK_C);

        //ignore, this is for Ctrl+C BUT KEY and not button
        /*Action doSomething = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("anh");
            }
        };
        bclear.registerKeyboardAction(doSomething, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        */

        //default button is "=" when pressing enter
        frame.getRootPane().setDefaultButton(bequal);
        
        //setting icons to buttons
        b0.setIcon(new ImageIcon("0.png"));
        b1.setIcon(new ImageIcon("1.png"));
        b2.setIcon(new ImageIcon("2.png"));
        b3.setIcon(new ImageIcon("3.png"));
        b4.setIcon(new ImageIcon("4.png"));
        b5.setIcon(new ImageIcon("5.png"));
        b6.setIcon(new ImageIcon("6.png"));
        b7.setIcon(new ImageIcon("7.png"));
        b8.setIcon(new ImageIcon("8.png"));
        b9.setIcon(new ImageIcon("9.png"));
        bdivide.setIcon(new ImageIcon("divide.png"));
        bmult.setIcon(new ImageIcon("multiply.png"));
        bminus.setIcon(new ImageIcon("minus.png"));
        bplus.setIcon(new ImageIcon("plus.png"));
        bclear.setIcon(new ImageIcon("clear.png"));
        bequal.setIcon(new ImageIcon("equal.png"));
         
        //adding actionlisteners to buttons
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        bdivide.addActionListener(this);
        bmult.addActionListener(this);
        bminus.addActionListener(this);
        bplus.addActionListener(this);
        bclear.addActionListener(this);
        bequal.addActionListener(this);
        
        //adding buttons (in order) to pannel
        panel.add(b7);       
        panel.add(b8);       
        panel.add(b9);
        panel.add(bdivide);
        panel.add(b4);       
        panel.add(b5);
        panel.add(b6);
        panel.add(bmult);
        panel.add(b1);       
        panel.add(b2);       
        panel.add(b3);
        panel.add(bminus);
        panel.add(b0);
        panel.add(bclear);
        panel.add(bequal);
        panel.add(bplus);
        
        //setting frame size
        frame.setSize(400, 400);
        
        //adding number-display
        frame.add(display, BorderLayout.NORTH);
        
        //adding buttons
        frame.add(panel);
        
        frame.pack();
        
        //center
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String command = ae.getActionCommand();
        
        //if buttons are clicked
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {

            //if no operator, user's number goes into operand1
            if (operator.equals("") && num_string1.length() < 8) {
                num_string1 += command.charAt(0) - 48; //ascii
                operand1 = Integer.parseInt(num_string1);                
                display.setText(Integer.toString(operand1));           
            }
            //if there is an operator, user's number goes into operand2
            else if (!operator.equals("") && num_string2.length() < 8) {
                num_string2 += command.charAt(0) - 48; //ascii
                operand2 = Integer.parseInt(num_string2);
                display.setText(Integer.toString(operand2));    
            }
 
        }
        
        //when Ctrl + C button
        else if((ae.getModifiers() & InputEvent.CTRL_MASK) != 0 & ae.getSource() == bclear) {
            display.setText("(c) anh-nguyen");
            
            //if Clear button has not been clicked, no restoration of display
            name_State = true;
            
            b0.setEnabled(false);
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b5.setEnabled(false);
            b6.setEnabled(false);
            b7.setEnabled(false);
            b8.setEnabled(false);
            b9.setEnabled(false);
            bdivide.setEnabled(false);
            bmult.setEnabled(false);
            bminus.setEnabled(false);
            bplus.setEnabled(false);
            bequal.setEnabled(false);
        }
        
        //restores when Clear button is clicked
        else if(name_State == true) {
            b0.setEnabled(true);
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);
            b5.setEnabled(true);
            b6.setEnabled(true);
            b7.setEnabled(true);
            b8.setEnabled(true);
            b9.setEnabled(true);
            bdivide.setEnabled(true);
            bmult.setEnabled(true);
            bminus.setEnabled(true);
            bplus.setEnabled(true);
            bequal.setEnabled(true);
            
            //restores calculator
            display.setText("0");
            name_State = false;

            
        }
       
        //clears calculator when activated
        else if (command.charAt(0) == 'C') {
            display.setText("0");
            operand1 = operand2 = result = 0;
            operator = "";
            num_string1 = num_string2 = "";
        }

        
        //converting characters to strings
        else if (command.charAt(0) == '/')
            operator = Character.toString('/');
        
        else if (command.charAt(0) == '*')
            operator = Character.toString('*');
        
        else if (command.charAt(0) == '-')
            operator = Character.toString('-');
        
        else if (command.charAt(0) == '+')
            operator = Character.toString('+');
       
        
        //when equal is pressed
        //overflows if > 99999999
        //underflows if < -99999999
        //divide has DIV BY 0 error
        else if (command.charAt(0) == '=') {
        
            if (operator.equals("+")) {
                result = operand1 + operand2;
                if(result > 99999999) {
                    display.setText("OVERFLOW");
                }
                else if(result < -99999999) {
                    display.setText("UNDERFLOW");
                }
                else {
                    display.setText(Integer.toString(result));
                    num_string2 = "";
                }
            } 
            else if (operator.equals("-")) {
                result = operand1 - operand2;
                if(result < -99999999){
                    display.setText("UNDERFLOW");
                }
                else if(result > 99999999) {
                    display.setText("OVERFLOW");
                }
                else {
                    display.setText(Integer.toString(result));
                    num_string2 = "";
                }
            } 
            else if (operator.equals("/")) {
                if (operand2 == 0) {
                    display.setText("DIV BY 0");
                }
                else {
                    result = operand1 / operand2;
                    display.setText(Integer.toString(result));
                    num_string2 = "";
                }
            } 
            else {
                result = operand1 * operand2;
                if(result > 99999999) {
                    display.setText("OVERFLOW");
                }
                else if(result < -99999999) {
                    display.setText("UNDERFLOW");
                }
                else {
                    display.setText(Integer.toString(result));
                    num_string2 = "";
                }
            }

            //reuse result as operand1 for:
            //      (operand1 operator1 operand2 = ) operator2 operand3 =
            operand1 = result;
            
        }
    }
    
    public static void main(String args[]) {
        //Create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });        

    }
    
} 