import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIFrame {

	private JFrame frame;
	private JTextField txtTypeA;
	private JTextField txtTypeAWord;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame window = new GUIFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Engine engine = new Engine();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone Number Converter");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(108, 11, 218, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number to Word");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(20, 52, 108, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Word to Number");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(20, 88, 108, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Add to Dictionary");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setBounds(20, 127, 108, 14);
		panel.add(lblNewLabel_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setForeground(new Color(255, 0, 0));
		textArea.setBackground(new Color(0, 0, 0));
		
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setBounds(20, 166, 400, 74);
		panel.add(scroller);
		//panel.add(textArea);
		
		
		txtTypeA = new JTextField();
		txtTypeA.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtTypeA.setText("Type a 7 digit phone number");
		txtTypeA.setBounds(138, 52, 182, 20);
		txtTypeA.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                txtTypeA.setText("");
            }
        });
		panel.add(txtTypeA);
		txtTypeA.setColumns(10);
		
		txtTypeAWord = new JTextField();
		txtTypeAWord.setText("Type a word");
		txtTypeAWord.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtTypeAWord.setColumns(10);
		txtTypeAWord.setBounds(138, 87, 182, 20);
		txtTypeAWord.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                txtTypeAWord.setText("");
            }
        });
		panel.add(txtTypeAWord);
		
		textField = new JTextField();
		textField.setText("Type a word with 7 or less characters");
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		textField.setColumns(10);
		textField.setBounds(138, 126, 182, 20);
		textField.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                textField.setText("");
            }
        });
		panel.add(textField);
		
		JButton btnNewButton = new JButton("Convert");
		//button press convert a given number into words listed in Dictionary.txt
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number = 0;
				try 
				{
					number = Integer.parseInt(txtTypeA.getText());
				}
				catch(NumberFormatException f)
				{		
					textArea.setText("");
					textArea.setText("Please enter a valid 7 digit phone number.");
				}
				
				try
				{
					textArea.setText("");
					engine.dictionaryComparison(number, textArea);
					//JScrollPane scroller = new JScrollPane(textArea);
					//panel.add(scroller);
				}
				catch(IOException g)
				{
					textArea.setText("");
					textArea.setText("Error: Dictionary.txt could not be opened.");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(330, 49, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Convert");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(330, 85, 89, 23);
		panel.add(btnNewButton_1);
		//button press converts the given word to a number
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = txtTypeAWord.getText();
				int num = engine.wordToNumber(word);
				if(num == -1)
				{
					textArea.setText("");
					textArea.setText("Error: character not recognized");
				}
				else
				{
					textArea.setText("");
					textArea.setText(word + " converts to " + num);
				}
			}		
		});
		
		JButton btnConvert = new JButton("Add");
		btnConvert.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnConvert.setBounds(330, 124, 89, 23);
		panel.add(btnConvert);
		//button press adds a word to Dictionary.txt
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String word = textField.getText();
				word = word.trim();
				word = word.toLowerCase();		
				word = word + "\n";
				boolean successfulWrite = false;
				try
				{
					engine.writeToDictionary(word);
					successfulWrite = true;
				}
				catch(IOException f)
				{
					textArea.setText("");
					textArea.setText("Error: write to Dictionary.txt unsuccessful");
					successfulWrite = false;
				}
				if(successfulWrite == true)
				{
					textArea.setText("");
					textArea.setText("Dictionary was successfully updated.");
				}
			}
		});
		
		
	}
}
