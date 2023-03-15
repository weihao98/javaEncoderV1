import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class textForm extends JFrame implements ActionListener {
    private JTextField textField1;
    private JButton submitButton;

    private JLabel label1;

    private JLabel label2;

    private JTextField textField2;

    public textForm() {
        // Set up the frame
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Add a text field and a submit button to the frame
        label1 = new JLabel("Choose offset: ");
        textField1 = new JTextField(30);

        label2 = new JLabel("Enter text: ");
        textField2 = new JTextField(30);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(label1);
        add(textField1);
        add(label2);
        add(textField2);
        add(submitButton);

        setVisible(true);

    }

    public static void shiftArray(char[] arr, int n) {
        int len = arr.length;
        char[] rotated = new char[len];
        for (int i = 0; i < len; i++) {
            rotated[(i + n)%len] = arr[i];
        }
        System.arraycopy(rotated, 0, arr, 0, len);
    }

    public String encode(String plaintext, char [] befArr, char [] aftArr){
        String encoded = "";
        for(int i=0;i< plaintext.length();i++){
            char c = plaintext.charAt(i);
            if (c == ' ') {
                encoded += " "; // append a space character if current character is a space
            }
            else {
                int indexBefore = new String(befArr).indexOf(plaintext.charAt(i));
                //if(indexBefore == -1){
                //    indexBefore = plaintext.charAt(i);
                //}
                //System.out.println(indexBefore);

                encoded += String.valueOf(aftArr[indexBefore]);
            }
        }
        //System.out.println(encoded);
        //System.out.println(encoded);

        System.out.println(decode(encoded,befArr,aftArr));

        return "Encoded text: " + encoded;
    }


    public String decode(String encodedText, char [] befArr, char [] aftArr){
        String decoded = "";

        //System.out.println("THis is the encoded text" + encodedText);
        //System.out.println("Length of encoded text : " + encodedText.length());
        //System.out.println(befArr);
        //System.out.println(aftArr);
        for(int i=0;i< encodedText.length();i++){
            char c = encodedText.charAt(i);
            if (c == ' ') {
                decoded += " "; // append a space character if current character is a space
            }
            else {
                int indexAfter= new String(aftArr).indexOf(encodedText.charAt(i));
                //if(indexBefore == -1){
                //    indexBefore = plaintext.charAt(i);
                //}
                //System.out.println(indexAfter);

                decoded += String.valueOf(befArr[indexAfter]);
            }
        }
        return "Decoded text " + decoded;
    }

    // Implement the ActionListener interface
    public void actionPerformed(ActionEvent e) {

        char[] myArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '(', ')', '*', '+',
                ',','-','.','/'};

        char[] myArray1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '(', ')', '*', '+',
                ',','-','.','/'};

        // Check which button was clicked
        if (e.getSource() == submitButton) {
            // Get the text from the text field
            String text = textField1.getText();
            // Print the text to the console
            System.out.println("Input: " + text);

            //Get first char from text field
            char t = text.charAt(0);
            int n = 0;
            //To get the index from the array before shifting
            for (int i = 0; i < myArray.length; i++) {
                if (myArray[i] == t) {
                    n = i;
                }
            }
            System.out.println("Index to shift: " + n);
            //Shift myarray according to index of the char
            shiftArray(myArray, n);
            System.out.println("Before shifting array: " + Arrays.toString(myArray1));
            System.out.println("After shifting array: " + Arrays.toString(myArray));
            System.out.println(encode(textField2.getText(),myArray1,myArray));
        }
    }


    public static void main(String[] args) {
        new textForm();
    }
}