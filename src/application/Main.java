package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import module.LimitedTextField;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Creamos un objeto de LimitedTextField
			LimitedTextField limitado = new LimitedTextField();

			// Usamos VBox y agregamos componente
			VBox root = new VBox();
			root.getChildren().addAll(limitado);
			Scene scene = new Scene(root,450,150);
			
			// Configurar el escenario
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Prueba Componente Contraseña Validada");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
