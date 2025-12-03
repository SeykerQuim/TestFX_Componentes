/**
 * Clase {file_name}
 * 
 * @author Quim Navarro Vaquero
 * @version 1.0
 */

package module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class PasswordFieldValidado extends PasswordField{
	// Esta indica el límite de caracteres
	private final IntegerProperty numColumns = new SimpleIntegerProperty(10);
	
	// Esta propiedad marca el color al superar el límite
	private final ObjectProperty<Color> limitColor = new SimpleObjectProperty<>(javafx.scene.paint.Color.RED);
	
	// Propiedad que indica el color del texto al superar el limite
	private final ObjectProperty<Color> limitTextColor = new SimpleObjectProperty<>(Color.WHITE); // Por defecto, blanco
	
	
	//Constructores
	public PasswordFieldValidado() {
		super();
		initialize();
	}
	
	/**
	 * Constructor con métodos
	 * @param numColumns
	 * @param limitColor
	 */
	public PasswordFieldValidado(int numColumns, Color limitColor, Color limitTextColor) {
		super();
		setNumColumns(numColumns);
		setLimitColor(limitColor);
	    setLimitTextColor(limitTextColor);
		initialize();
	}
	
	
	/**
	 * Método que inicializa el constructor
	 */
	private void initialize() {
		this.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.length() > getNumColumns()) {
				this.setBackground(new Background(new BackgroundFill(
						getLimitColor(), CornerRadii.EMPTY, Insets.EMPTY)));
				this.setStyle("-fx-text-fill: "+toRGBCode(getLimitTextColor())+";");
			} else {
//				this.setBackground(Background.EMPTY);
				this.setStyle("-fx-text-fill: "+toRGBCode(Color.BLACK));
			}
		});
		
	}


	//Getter y Setter para el límite de caracteres
	public int getNumColumns() {
		return numColumns.get();
	}
	
	public void setNumColumns(int limit) {
		this.numColumns.set(limit);
	}
	
	
	public IntegerProperty numColumnsProperty() {
		return numColumns;
	}

	//Getter y Setter para el color
	public Color getLimitColor() {
		return limitColor.get();
	}
	
	public void setLimitColor(Color color) {
		this.limitColor.set(color);
	}
	
	
	// Getter para el color del texto al superar el límite
	public Color getLimitTextColor() {
	    return limitTextColor.get();
	}

	// Setter para el color del texto al superar el límite
	public void setLimitTextColor(Color color) {
	    this.limitTextColor.set(color);
	}

	// Property para el color del texto al superar el límite
    public ObjectProperty<Color> limitTextColorProperty() {
        return limitTextColor;
    }


	private String toRGBCode(Color color) {
	    return String.format("#%02X%02X%02X",
	        (int) (color.getRed() * 255),
	        (int) (color.getGreen() * 255),
	        (int) (color.getBlue() * 255));
	}
}
