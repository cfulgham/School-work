
import javax.swing.*;
import java.awt.event.*;

/**
 * Simple calculater implement with GUI
 * uses JButton with anonymous class ActionListeners to
 * implement calculator with 0-9 numbers and functions:
 * +, -, *, /, =, square root, square, and 1/x.
 * @author Christopher Fulgham
 * @version 1.0
 */

class JavaCalculator{
	JFrame f;
	JTextField t;

	static double a=0,b=0,result=0;
	static int operator=0;

	JavaCalculator(){
		f=new JFrame("Calculator");

		t=new JTextField();
		t.setBounds(30,40,280,30);
		f.add(t);

		// Square Root button
		JButton bsqrt=new JButton("sqrt(x)");
		bsqrt.setBounds(40,100,80,40);
		f.add(bsqrt);
		bsqrt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				a=Double.parseDouble(t.getText());
				result=Math.sqrt(a);
				t.setText(""+result);
			}
		});

		// Square button
		JButton bsq=new JButton("x^2");
		bsq.setBounds(130,100,80,40);
		f.add(bsq);
		bsq.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				a=Double.parseDouble(t.getText());
				result= a * a;
				t.setText(""+result);
			}
		});

		// 1/x button
		JButton bfrac=new JButton("1/x");
		bfrac.setBounds(220,100,80,40);
		f.add(bfrac);
		bfrac.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				a=Double.parseDouble(t.getText());
				if ((a > 0) || (a < 0)) {
					result=1/a;
					t.setText(""+result);
				}
				else{
					t.setText("Error");
				}

			}
		});

		// 7 button
		JButton b7=new JButton("7");
		b7.setBounds(40,170,50,40);
		f.add(b7);
		b7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("7"));
			}
		});

		// 8 button
		JButton b8=new JButton("8");
		b8.setBounds(110,170,50,40);
		f.add(b8);
		b8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("8"));
			}
		});

		// 9 button
		JButton b9=new JButton("9");
		b9.setBounds(180,170,50,40);
		f.add(b9);
		b9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("9"));
			}
		});

		// Divide button
		JButton bdiv=new JButton("/");
		bdiv.setBounds(250,170,50,40);
		f.add(bdiv);
		bdiv.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				a=Double.parseDouble(t.getText());
				operator=4;
				t.setText("");
			}
		});

		// 4 button
		JButton b4=new JButton("4");
		b4.setBounds(40,240,50,40);
		f.add(b4);
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("4"));
			}
		});

		// 5 button
		JButton b5=new JButton("5");
		b5.setBounds(110,240,50,40);
		f.add(b5);
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("5"));
			}
		});

		//6 button
		JButton b6=new JButton("6");
		b6.setBounds(180,240,50,40);
		f.add(b6);
		b6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("6"));
			}
		});

		// Multiply button
		JButton bmul=new JButton("*");
		bmul.setBounds(250,240,50,40);
		f.add(bmul);
		bmul.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				a=Double.parseDouble(t.getText());
				operator=3;
				t.setText("");
			}
		});

		// 1 button
		JButton b1=new JButton("1");
		b1.setBounds(40,310,50,40);
		f.add(b1);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("1"));
			}
		});

		// 2 button
		JButton b2=new JButton("2");
		b2.setBounds(110,310,50,40);
		f.add(b2);
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("2"));
			}
		});

		// 3 button
		JButton b3=new JButton("3");
		b3.setBounds(180,310,50,40);
		f.add(b3);
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("3"));
			}
		});

		// Subtract button
		JButton bsub=new JButton("-");
		bsub.setBounds(250,310,50,40);
		f.add(bsub);
		bsub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				a=Double.parseDouble(t.getText());
				operator=2;
				t.setText("");
			}
		});

		// Decimal button
		JButton bdec=new JButton(".");
		bdec.setBounds(40,380,50,40);
		f.add(bdec);
		bdec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("."));
			}
		});

		// 0 button
		JButton b0=new JButton("0");
		b0.setBounds(110,380,50,40);
		f.add(b0);
		b0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText(t.getText().concat("0"));
			}
		});

		// Equals button
		JButton beq=new JButton("=");
		beq.setBounds(180,380,50,40);
		f.add(beq);
		beq.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				b=Double.parseDouble(t.getText());

				switch(operator)
				{
					case 1: result=a+b;
						break;

					case 2: result=a-b;
						break;

					case 3: result=a*b;
						break;

					case 4: result=a/b;
						break;


					default: result=0;
				}

				t.setText(""+result);
			}
		});

		// Addition button
		JButton badd=new JButton("+");
		badd.setBounds(250,380,50,40);
		f.add(badd);
		badd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				a=Double.parseDouble(t.getText());
				operator=1;
				t.setText("");
			}
		});

		// Delete button
		JButton bdel=new JButton("Delete");
		bdel.setBounds(60,450,100,40);
		f.add(bdel);
		bdel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String s=t.getText();
				t.setText("");
				for(int i=0;i<s.length()-1;i++)
					t.setText(t.getText()+s.charAt(i));
			}
		});

		// Clear button
		JButton bclr=new JButton("Clear");
		bclr.setBounds(180,450,100,40);
		f.add(bclr);
		bclr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.setText("");
			}
		});

		f.setLayout(null);
		f.setVisible(true);
		f.setSize(350,550);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);

	}

	/**
	 * Main function to run calculator.
	 * @param s
	 */
 
	public static void main(String...s)
	{
		new JavaCalculator();
	}
}