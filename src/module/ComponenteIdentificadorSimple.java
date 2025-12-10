/**
 * Clase ComponenteIdentificador
 * 
 * @author Quim Navarro Vaquero
 * @version 1.0
 */
package module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ComponenteIdentificadorSimple extends VBox {
	
	// Límite de caracteres
	private final IntegerProperty maxLength = new SimpleIntegerProperty(8);
	
	// Componentes visuales
	private Label lblTitulo;
	private TextField txtIdentificador;
	private Label lblValidacion;
	
	
	// Patrón de validación: 3 letras + 5 números
	private static final String PATRON_VALIDACION = "^[A-Za-z]{3}\\d{5}$";
	
	
	// Constructores
	public ComponenteIdentificadorSimple() {
		super();
		initialize();
	}
	
	/**
	 * Constructor con espaciado personalizado
	 * @param spacing espaciado entre componentes
	 */
	public ComponenteIdentificadorSimple(double spacing) {
		super(spacing);
		initialize();
	}
	
	
	/**
	 * Método que inicializa el componente
	 */
	private void initialize() {
		// Configurar el VBox
		this.setSpacing(5);
		this.setPadding(new Insets(10));
		
		// Crear label de título
		lblTitulo = new Label("Identificador");
		lblTitulo.setStyle("-fx-font-weight: bold;");
		
		// Crear TextField
		txtIdentificador = new TextField();
		txtIdentificador.setPromptText("Ej: ABC12345");
		txtIdentificador.setPrefColumnCount(getMaxLength());
		
		// Crear label de validación
		lblValidacion = new Label("");
		lblValidacion.setStyle("-fx-font-size: 11px;");
		
		// Añadir listener para validación en tiempo real
		txtIdentificador.textProperty().addListener((observable, oldValue, newValue) -> {
			// Limitar longitud máxima
			if (newValue.length() > getMaxLength()) {
				txtIdentificador.setText(oldValue);
				return;
			}
			
			txtIdentificador.setText(newValue);
			// Actualizar propiedad
//			identificador.set(newValue);
			
			if(newValue.isEmpty()) {
				lblValidacion.setText("");
				return;
			}
			
			if(newValue.matches(PATRON_VALIDACION)) {
				lblValidacion.setText("✓ Formato correcto");
				lblValidacion.setTextFill(Color.GREEN);
			} else {
				// Proporcionar mensaje específico según el error
				lblValidacion.setText("✗ formato no válido, recuerde 3 letras y 5 números");
				lblValidacion.setTextFill(Color.RED);
			}
			// Validar formato
//			validarFormato(newValue);
		});
		
		// Añadir componentes al VBox
		this.getChildren().addAll(lblTitulo, txtIdentificador, lblValidacion);
	}
	

	
	
	// Getters y Setters
	
	public int getMaxLength() {
		return maxLength.get();
	}
	
	public void setMaxLength(int length) {
		this.maxLength.set(length);
	}
	
	public IntegerProperty maxLengthProperty() {
		return maxLength;
	}
	


	
	/**
	 * Limpia el contenido del TextField
	 */
	public void limpiar() {
		txtIdentificador.clear();
	}
	
	/**
	 * Obtiene el TextField para configuraciones adicionales
	 * @return el TextField interno
	 */
	public TextField getTextField() {
		return txtIdentificador;
	}
}