/**
 * Clase {file_name}
 * 
 * @author Quim Navarro Vaquero
 * @version 1.0
 */

package module;


import java.util.regex.Pattern;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class PasswordFieldValidado extends PasswordField{
	
	// Límite máximo de caracteres
    private final IntegerProperty numColumns = new SimpleIntegerProperty(20);
    // Longitud mínima requerida
    private final IntegerProperty numMinColumns = new SimpleIntegerProperty(8);
	
	// Esta propiedad marca el color al superar el límite
	private final ObjectProperty<Color> limitColor = new SimpleObjectProperty<>(javafx.scene.paint.Color.RED);
	
	// Propiedad que indica el color del texto al superar el limite
	private final ObjectProperty<Color> limitTextColor = new SimpleObjectProperty<>(Color.WHITE); // Por defecto, blanco
	// Propiedad para indicar si la contraseña es válida
    private final BooleanProperty valid = new SimpleBooleanProperty(false);
	
	//Constructores
	
	public PasswordFieldValidado() {
		super();
		initialize();
	}
	
	/**
	 * Método constructor accesible por otros métodos
	 * @param numColumns número límite de carácteres del texto
	 * @param limitColor color de fondo al superar el límite
	 * @param limitTextColor color del texto al superar el límite
	 */
	public PasswordFieldValidado(int maxLength, int minLength, Color limitColor, Color limitTextColor) {
        super();
        setMaxLength(maxLength);
        setMinLength(minLength);
        setLimitColor(limitColor);
        setLimitTextColor(limitTextColor);
        initialize();
    }
	
	private void initialize() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            validarContraseña(newValue);
        });
    }
	
	

	/**
	 * 
	 * @param password
	 */
	private void validarContraseña(String password) {
        boolean esValida = true;

        // 1. Validar que NO supere el límite máximo
        if (password.length() > getMaxLength()) {
            esValida = false;
        }
        // 2. Validar que cumpla la longitud mínima
        else if (password.length() < getMinLength()) {
            esValida = false;
        }
        // 3. Validar que contenga al menos un número
        else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            esValida = false;
        }
        // 4. Validar que contenga al menos una letra
        else if (!Pattern.compile("[a-zA-Z]").matcher(password).find()) {
            esValida = false;
        }

        setValid(esValida);

        // Aplicar estilo según validación
        if (!esValida) {
            this.setBackground(new Background(new BackgroundFill(
                    getLimitColor(), CornerRadii.EMPTY, Insets.EMPTY)));
            this.setStyle("-fx-text-fill: " + toRGBCode(getLimitTextColor()) + ";");
        } else {
            this.setBackground(Background.EMPTY);
            this.setStyle("-fx-text-fill: " + toRGBCode(Color.BLACK) + ";");
        }
    }


	// Getters y Setters para maxLength
    public int getMaxLength() {
        return numColumns.get();
    }

    public void setMaxLength(int max) {
        this.numColumns.set(max);
    }

    public IntegerProperty maxLengthProperty() {
        return numColumns;
    }

    // Getters y Setters para minLength
    public int getMinLength() {
        return numMinColumns.get();
    }

    public void setMinLength(int min) {
        this.numMinColumns.set(min);
    }

    public IntegerProperty minLengthProperty() {
        return numMinColumns;
    }

    // Getters y Setters para errorColor
    public Color getLimitColor() {
        return limitColor.get();
    }

    public void setLimitColor(Color color) {
        this.limitColor.set(color);
    }

    // Getters y Setters para errorTextColor
    public Color getLimitTextColor() {
        return limitTextColor.get();
    }

    public void setLimitTextColor(Color color) {
        this.limitTextColor.set(color);
    }

    public ObjectProperty<Color> errorTextColorProperty() {
        return limitTextColor;
    }

    // Getter y Setter para la propiedad de validez
    public boolean isValid() {
        return valid.get();
    }

    public void setValid(boolean value) {
        valid.set(value);
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    /**
     *  Método auxiliar para convertir los colores en código RGB
     * @param color introducido en SceneBuilder o en el FXML
     * @return String con el color en el formato correcto para estilo de texto
     */
	private String toRGBCode(Color color) {
	    return String.format("#%02X%02X%02X",
	        (int) (color.getRed() * 255),
	        (int) (color.getGreen() * 255),
	        (int) (color.getBlue() * 255));
	}
	
}
