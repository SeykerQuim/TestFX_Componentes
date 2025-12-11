package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import module.ComponenteCalculadoraIMC;
import module.ComponenteIdentificador;
import module.ComponenteIdentificadorSimple;
import module.LimitedTextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Creamos un objeto de LimitedTextField
//			LimitedTextField limitado = new LimitedTextField();
			ComponenteIdentificador componente = new ComponenteIdentificador();
			ComponenteIdentificadorSimple simple = new ComponenteIdentificadorSimple();
			ComponenteCalculadoraIMC calcu = new ComponenteCalculadoraIMC();
//			calcu.start(primaryStage);

			// Usamos VBox y agregamos componente
//			VBox root = new VBox(10, calcu);
//			root.setStyle("-fx-padding:20; -fx-alignment: center;");
//			Scene scene = new Scene(calcu);
			
			// Para cargar el de EjemploFX
			ejemploFX(primaryStage);
			
			
			// Configurar el escenario
			primaryStage.setTitle("Prueba Componente Contraseña Validada");
//			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ejemploFX(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Ejemplo.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Ejemplo Controles JavaFX");
		// Configurar escena y mostrar
		primaryStage.setScene(new Scene(root, 500, 800));
		//No se permite redimensionar si el valor es false
		primaryStage.setResizable(true);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
