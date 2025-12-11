package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import module.ComponenteCalculadoraIMC;
import module.ComponenteIdentificador;
import module.ComponenteIdentificadorSimple;
import module.LimitedTextField;
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
			calcu.start(primaryStage);

			// Usamos VBox y agregamos componente
//			VBox root = new VBox(10, calcu);
//			root.setStyle("-fx-padding:20; -fx-alignment: center;");
//			Scene scene = new Scene(calcu);
			
			// Configurar el escenario
			primaryStage.setTitle("Prueba Componente Contraseña Validada");
//			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
