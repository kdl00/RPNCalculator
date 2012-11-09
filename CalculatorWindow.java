import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorWindow extends JFrame implements ActionListener {
	private JTextField _txtDisplay;
	private JButton _btns[];
	private RPNCalculator _calc;

	public CalculatorWindow() {
		setTitle("Calculator");
		setSize(240, 240);
		setMinimumSize(new Dimension(240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_txtDisplay = new JTextField();
		_txtDisplay.addActionListener(this);
		_txtDisplay.setFont(new Font("Arial", 1, 16));

		// jpanel containing the calculator display
		JPanel pnlDisplay = new JPanel();
		pnlDisplay.setLayout(new GridLayout());
		pnlDisplay.add(_txtDisplay);
		add("North", pnlDisplay);

		// jpanel containing the numbers and decimal point
		JPanel pnlNumControls = new JPanel();
		pnlNumControls.setLayout(new GridLayout(4, 3));

		// jpanel containing the operators
		JPanel pnlFuncControls = new JPanel();
		pnlFuncControls.setLayout(new GridLayout(6, 1));

		/*
		 * In order to set sizes on jpanels that use gridlayout place them into
		 * a jpanel that uses flowlayout
		 */
		JPanel pnlNumFlow = new JPanel();
		pnlNumFlow.setLayout(new FlowLayout());

		JPanel pnlFuncFlow = new JPanel();
		pnlFuncFlow.setLayout(new FlowLayout());

		String btnNames[] = new String[] { "Enter", "Clear", "+", "-", "*",
				"/", "." };
		_btns = new JButton[10 + btnNames.length];
		for (int i = 9; i >= 0; i--) {
			_btns[i] = new JButton(Integer.toString(i));
			_btns[i].addActionListener(this);
			pnlNumControls.add(_btns[i]);

		}

		for (String btnName : btnNames) {
			JButton btn = new JButton(btnName);
			btn.addActionListener(this);
			if (btnName.equals("."))
				pnlNumControls.add(btn);
			else
				pnlFuncControls.add(btn);
		}

		pnlNumFlow.add(pnlNumControls);
		pnlNumFlow.setSize(new Dimension(200, 200));

		pnlFuncFlow.add(pnlFuncControls);
		pnlFuncFlow.setSize(new Dimension(100, 200));

		add("Center", pnlNumFlow);
		add("East", pnlFuncFlow);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent event) {
		switch (event.getActionCommand()) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "+":
		case "-":
		case "*":
		case "/":
		case ".":
			_txtDisplay.setText(_txtDisplay.getText()
					+ event.getActionCommand());
			break;
		case "Enter":
			_calc = new RPNCalculator(_txtDisplay.getText());
			_txtDisplay.setText(_calc.getAnswer());
			System.out.printf("Answer: %s\n", _calc.getAnswer());
			break;
		case "Clear":
			_txtDisplay.setText("");
			break;
		}
	}

	public static void main(String[] args) {
		CalculatorWindow cw = new CalculatorWindow();

	}

}