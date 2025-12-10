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

public class ComponenteIdentificador extends VBox {
	
	// Límite de caracteres
	private final IntegerProperty maxLength = new SimpleIntegerProperty(8);
	
	// Componentes visuales
	private Label lblTitulo;
	private TextField txtIdentificador;
	private Label lblValidacion;
	
	// Propiedad para el texto del identificador
	private final StringProperty identificador = new SimpleStringProperty("");
	
	// Patrón de validación: 3 letras + 5 números
	private static final String PATRON_VALIDACION = "^[A-Za-z]{3}\\d{5}$";
	
	
	// Constructores
	public ComponenteIdentificador() {
		super();
		initialize();
	}
	
	/**
	 * Constructor con espaciado personalizado
	 * @param spacing espaciado entre componentes
	 */
	public ComponenteIdentificador(double spacing) {
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
			
			// Actualizar propiedad
			identificador.set(newValue);
			
			// Validar formato
			validarFormato(newValue);
		});
		
		// Añadir componentes al VBox
		this.getChildren().addAll(lblTitulo, txtIdentificador, lblValidacion);
	}
	
	
	/**
	 * Valida el formato del identificador y actualiza el label de validación
	 * @param texto texto a validar
	 */
	private void validarFormato(String texto) {
		if (texto.isEmpty()) {
			lblValidacion.setText("");
			return;
		}
		
		if (texto.matches(PATRON_VALIDACION)) {
			lblValidacion.setText("✓ Formato correcto");
			lblValidacion.setTextFill(Color.GREEN);
		} else {
			// Proporcionar mensaje específico según el error
			String mensaje = obtenerMensajeError(texto);
			lblValidacion.setText("✗ " + mensaje);
			lblValidacion.setTextFill(Color.RED);
		}
	}
	
	
	/**
	 * Obtiene un mensaje de error específico según el formato incorrecto
	 * @param texto texto a analizar
	 * @return mensaje de error descriptivo
	 */
	private String obtenerMensajeError(String texto) {
		if (texto.length() < 8) {
			return "Debe tener 8 caracteres (3 letras + 5 números)";
		}
		
		String primerosTres = texto.substring(0, Math.min(3, texto.length()));
		if (!primerosTres.matches("[A-Za-z]{3}")) {
			return "Los primeros 3 caracteres deben ser letras";
		}
		
		if (texto.length() >= 4) {
			String ultimos = texto.substring(3);
			if (!ultimos.matches("\\d+")) {
				return "Los últimos 5 caracteres deben ser números";
			}
		}
		
		return "Formato incorrecto";
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
	
	public String getIdentificador() {
		return identificador.get();
	}
	
	public void setIdentificador(String id) {
		txtIdentificador.setText(id);
	}
	
	public StringProperty identificadorProperty() {
		return identificador;
	}
	
	/**
	 * Verifica si el identificador actual es válido
	 * @return true si es válido, false en caso contrario
	 */
	public boolean isValido() {
		return getIdentificador().matches(PATRON_VALIDACION);
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