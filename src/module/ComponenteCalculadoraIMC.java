/**
 * Clase CalculadoraIMC
 * 
 * @author Quim Navarro Vaquero
 * @version 1.0
 */
package module;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ComponenteCalculadoraIMC extends Application {

	private TextField pesoMostrar;
	private TextField alturaIn;
	private TextField porcentajeOut;
	private Slider sliderPeso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Configurar ventana principal
			primaryStage.setTitle("CALCULADORA DE ÍNDICE DE MASA CORPORAL");
			primaryStage.setResizable(false);
			
			// Intentar cargar el icono
			try {
				Image icon = new Image(getClass().getResourceAsStream("/images/peso.png"));
				primaryStage.getIcons().add(icon);
			} catch (Exception e) {
				// Si no se encuentra el icono, continuar sin él
			}

			// Crear el layout principal
			GridPane gridPane = new GridPane();
			gridPane.setPadding(new Insets(15, 15, 15, 15));
			gridPane.setHgap(10);
			gridPane.setVgap(15);

			// Fila 1: Peso (Label, Slider, TextField, Label)
			Label labelPeso = new Label("Peso");
			labelPeso.setFont(Font.font("System", FontWeight.NORMAL, 12));
			GridPane.setConstraints(labelPeso, 0, 0);
			gridPane.getChildren().add(labelPeso);

			// Slider de peso
			sliderPeso = new Slider(0, 200, 70);
			sliderPeso.setShowTickLabels(true);
			sliderPeso.setShowTickMarks(true);
			sliderPeso.setMajorTickUnit(10);
			sliderPeso.setMinorTickCount(4);
			sliderPeso.setPrefWidth(400);
			sliderPeso.setBlockIncrement(1);
			
			// Listener para actualizar el TextField cuando cambia el slider
			sliderPeso.valueProperty().addListener((observable, oldValue, newValue) -> {
				pesoMostrar.setText(String.valueOf(newValue.intValue()));
			});
			
			GridPane.setConstraints(sliderPeso, 1, 0);
			gridPane.getChildren().add(sliderPeso);

			// TextField para mostrar el peso
			pesoMostrar = new TextField("70");
			pesoMostrar.setEditable(false);
			pesoMostrar.setPrefWidth(60);
			pesoMostrar.setStyle("-fx-background-color: #FEFCBC;");
			GridPane.setConstraints(pesoMostrar, 2, 0);
			gridPane.getChildren().add(pesoMostrar);

			Label labelKg = new Label("Kg");
			GridPane.setConstraints(labelKg, 3, 0);
			gridPane.getChildren().add(labelKg);

			// Fila 2: Altura, Botón Calcular, Resultado
			HBox hboxAltura = new HBox(10);
			hboxAltura.setAlignment(Pos.CENTER_LEFT);
			
			Label labelAltura = new Label("Altura");
			labelAltura.setFont(Font.font("System", FontWeight.BOLD, 12));
			
			alturaIn = new TextField();
			alturaIn.setPrefWidth(80);
			alturaIn.setPromptText("Ej: 175");
			
			Label labelCm = new Label("en cm");
			
			hboxAltura.getChildren().addAll(labelAltura, alturaIn, labelCm);
			GridPane.setConstraints(hboxAltura, 0, 1, 2, 1);
			gridPane.getChildren().add(hboxAltura);

			// Botón Calcular
			Button botonCalcular = new Button("Calcular IMC");
			botonCalcular.setFont(Font.font("System", FontWeight.BOLD, 12));
			botonCalcular.setPrefWidth(120);
			botonCalcular.setStyle("-fx-background-color: #E0E0E0; -fx-cursor: hand;");
			
			// Evento del botón
			botonCalcular.setOnAction(e -> calcularIMC());
			
			GridPane.setConstraints(botonCalcular, 2, 1);
			gridPane.getChildren().add(botonCalcular);

			// TextField para resultado
			HBox hboxResultado = new HBox(5);
			hboxResultado.setAlignment(Pos.CENTER_LEFT);
			
			porcentajeOut = new TextField();
			porcentajeOut.setEditable(false);
			porcentajeOut.setPrefWidth(80);
			porcentajeOut.setStyle("-fx-text-fill: red;");
			
			Label labelPorcentaje = new Label("%");
			
			hboxResultado.getChildren().addAll(porcentajeOut, labelPorcentaje);
			GridPane.setConstraints(hboxResultado, 3, 1);
			gridPane.getChildren().add(hboxResultado);

			// Crear escena
			Scene scene = new Scene(gridPane, 700, 150);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para calcular el IMC
	 */
	private void calcularIMC() {
		try {
			// Obtener valores
			double altura = Double.parseDouble(alturaIn.getText()) / 100;
			int peso = Integer.parseInt(pesoMostrar.getText());

			// Validar altura
			if (altura <= 0) {
				mostrarError("Error, no se pueden usar valores negativos o cero", "Error de entrada");
				return;
			}

			// Calcular IMC
			double resultado = peso / (altura * altura);
			porcentajeOut.setText(String.format("%.2f", resultado));

			// Cambiar el color en función del IMC
			if (resultado < 18.5) {
				porcentajeOut.setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
			} else if (resultado >= 18.5 && resultado < 25) {
				porcentajeOut.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
			} else if (resultado >= 25) {
				porcentajeOut.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
			}

		} catch (NumberFormatException ex) {
			mostrarError("Error, introduzca valores válidos", "Error de entrada");
		}
	}

	/**
	 * Muestra un diálogo de error
	 */
	private void mostrarError(String mensaje, String titulo) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}