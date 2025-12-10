package module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PasswordFieldValidadoIsa extends VBox {

	private final Label lblPassword;
    private final PasswordField passwordField;  
    private final Label visiblePasswordLabel; 
    private final ToggleButton toggleButton;
    private final Label validationLabel;

       
    private final StringProperty passwordProperty = new SimpleStringProperty();
    private final IntegerProperty longPassword = new SimpleIntegerProperty(10); 
    
    public PasswordFieldValidadoIsa() {
    	
    	lblPassword= new Label("Constraseña");    	
        // Campo de contraseña oculto
        passwordField = new PasswordField();
        passwordField.textProperty().bindBidirectional(passwordProperty);

        // Label para mostrar la contraseña
        visiblePasswordLabel = new Label();
        visiblePasswordLabel.textProperty().bind(passwordProperty);
        visiblePasswordLabel.setVisible(false);

        // Botón para mostrar/ocultar contraseña
        toggleButton = new ToggleButton("Mostrar");
        toggleButton.setOnAction(e -> togglePasswordVisibility());

        // Etiqueta para mensajes de validación
        validationLabel = new Label();

        // Evento para validar la contraseña en tiempo real
        passwordProperty.addListener((obs, oldVal, newVal) -> validatePassword(newVal));

        // Layout
        HBox passwordBox = new HBox(5, lblPassword, passwordField, toggleButton);        
        this.getChildren().addAll(passwordBox, visiblePasswordLabel,validationLabel);
    }

    private void togglePasswordVisibility() {
      
    	boolean isShowing = toggleButton.isSelected();
        visiblePasswordLabel.setVisible(isShowing);
        toggleButton.setText(isShowing ? "Ocultar" : "Mostrar");
    }

    private void validatePassword(String password) {    	
    	if (password.length() < longPassword.get()) {
            validationLabel.setText("La contraseña debe tener al menos "+ longPassword.get()+" caracteres.");
            validationLabel.setStyle("-fx-text-fill:red");
        } else if (!password.matches(".*\\d.*")) {
            validationLabel.setText("La contraseña debe incluir al menos un número.");
            validationLabel.setStyle("-fx-text-fill:red");
        } else if (!password.matches(".*[A-Z].*")) {
            validationLabel.setText("La contraseña debe incluir al menos una letra mayúscula.");
            validationLabel.setStyle("-fx-text-fill:red");
        }else if (!password.matches(".*[@#~$%&!?].*")){
            validationLabel.setText("La contraseña debe incluir al menos un símbolo especial '@,#,~,$,%,&,!,?' mayúscula.");
            validationLabel.setStyle("-fx-text-fill:red");
        } else {
            validationLabel.setText("Contraseña válida.");
            validationLabel.setStyle("-fx-text-fill:green");
        }
    }
              
    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    public String getPassword() {
        return passwordProperty.get();
    }

    public void setPassword(String password) {
        this.passwordProperty.set(password);
    }
    public IntegerProperty longPasswordProperty() {
        return longPassword;
    }

    public int getLongPassword() {
        return longPassword.get();
    }

    public void setlongPassword(int maxLength) {
        if (maxLength < 1 || maxLength > 128) {
            throw new IllegalArgumentException("La longitud máxima debe estar entre 1 y 128 caracteres.");
        }
        this.longPassword.set(maxLength);
    }

}
