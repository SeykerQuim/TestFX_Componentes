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
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class LimitedTextField extends TextField{
	
	// Esta indica el límite de caracteres
	private final IntegerProperty numColumns = new SimpleIntegerProperty(10);
	
	// Esta propiedad marca el color al superar el límite
	private final ObjectProperty<Color> limitColor = new SimpleObjectProperty<>(javafx.scene.paint.Color.RED);
	
	
	//Constructores
	public LimitedTextField() {
		super();
		initialize();
	}
	
	/**
	 * Constructor con métodos
	 * @param numColumns
	 * @param limitColor
	 */
	public LimitedTextField(int numColumns, Color limitColor) {
		super();
		setNumColumns(numColumns);
		setLimitColor(limitColor);
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
			} else {
				this.setBackground(Background.EMPTY);
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
	

	
}
