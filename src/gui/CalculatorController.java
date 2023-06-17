package gui;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import utils.EvaluateString;

public class CalculatorController {

	@FXML
	private Label expression;

	@FXML
	private Label result;

	private ArrayList<String> calculation_history = new ArrayList<>();

	public void insertNumber(String number) {
		expression.setText(expression.getText() + number);
	}

	public void insertOperator(String operator) {
		expression.setText(expression.getText() + " " + operator + " ");
	}

	public void insertAnswer(String number) {
		expression.setText(expression.getText() + number);
	}

	public void deleteLast() {
		if (!getExpression().getText().isEmpty()) {
			StringBuilder text = new StringBuilder(expression.getText());
			text.deleteCharAt(text.length() - 1);
			getExpression().setText(text.toString());
		}
	}

	public void clearExpression() {
		expression.setText("");
	}

	public Label getExpression() {
		return expression;
	}

	public Label getResult() {
		return result;
	}

	public void setResult(String newResult) {
		this.result.setText("= " + newResult);
	}

	public void addCalculation(String expression, String result) {
		calculation_history.add(expression + " = " + result);
	}

	public void openHistoryWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/History.fxml"));
			Parent root = loader.load();

			Main.getHistoryStage().setScene(new Scene(root));

			HistoryController historyController = loader.getController();
			historyController.initializeCalculations(calculation_history);

			Main.getHistoryStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onMouseClick(MouseEvent event) {
		Button button = (Button) event.getSource();
		String buttonText = button.getText();

		switch (buttonText) {
		case "9":
		case "8":
		case "7":
		case "6":
		case "5":
		case "4":
		case "3":
		case "2":
		case "1":
		case "0":
			insertNumber(buttonText);
			break;
		case "/":
		case "*":
		case "+":
		case "-":
			insertOperator(buttonText);
			break;
		case "=":
			int output = EvaluateString.evaluate(expression.getText());
			setResult(String.valueOf(output));
			addCalculation(expression.getText(), result.getText());
			break;
		case "ANS":
			insertAnswer(result.getText().substring(2));
			break;
		case "CLEAR":
			clearExpression();
			break;
		case "DELETE":
			deleteLast();
			break;
		case "HISTORY":
			openHistoryWindow();
		}
	}
}
