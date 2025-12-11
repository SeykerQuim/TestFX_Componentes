package application;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.web.WebView;
//import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Estudiante;

public class EjemploController {

	@FXML
	private Label etiqueta;
	@FXML
	private TextField txtNombre;
	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private ProgressIndicator progressIndicator;

	@FXML
	private Slider slider;

	@FXML
	private Label lbslider;

	@FXML
	private CheckBox checkBox1;
	@FXML
	private CheckBox checkBox2;
	@FXML
	private CheckBox checkBox3;

	@FXML
	private RadioButton radioButton1;
	@FXML
	private RadioButton radioButton2;
	@FXML
	private RadioButton radioButton3;
	@FXML
	private ToggleGroup groupColor;

	@FXML
	private ListView<String> miLista;

	@FXML
	private TableView<Estudiante> table;
	@FXML
	private TableColumn<Estudiante, Integer> id;
	@FXML
	private TableColumn<Estudiante, String> name;
	@FXML
	private TableColumn<Estudiante, String> surname;
	@FXML
	private TableColumn<Estudiante, Integer> age;

	public ObservableList<Estudiante> listaPersonas = FXCollections
			.observableArrayList(
					new Estudiante(1, "Maria", "Diaz Fernández", 2),
					new Estudiante(2, "Luis", "Naves Casado", 9),
					new Estudiante(3, "Ana", "Álvarez", 36),
					new Estudiante(4, "Diego", "Perez", 25));

	@FXML
	private MenuBar menuBar;
	@FXML
	private MenuItem openHelp;

	/**
	 * Método que se ejecuta cuando el usuario pulsa el botón Saludador.
	 * Acciones a ejecutar
	 */
	@FXML
	public void handleSaludaClick() {
		// Se recoge el valor del campo nombre
		String nom = txtNombre.getText();
		// Se establece un valor para la etiqueta mensaje de bienvenida
		// con el nombre en mayúsculas
		etiqueta.setText("Bienvenido a JavaFX , " + nom.toUpperCase() + " !!");
		// Se establece un estilo concreto fuente en color rojo
		etiqueta.setStyle("-fx-text-fill:red");
	}

	/**
	 * Método que se invoca después de que se hayan realizado todas las
	 * inyecciones de componentes UI. Este es el momento adecuado para realizar
	 * la inicialización de datos, ya que las referencias de los widgets UI han
	 * sido resueltas completamente e inyectadas hasta este punto.
	 */
	@FXML
	public void initialize() {
		// Inicializamos el ChoiceBox con opciones, el equivalente al ComboBox
		choiceBox.setItems(FXCollections.observableArrayList("Opción A",
				"Opción B", "Opción C"));
		// Opcional: Establecemos un valor por defecto
		choiceBox.setValue("Opción A");
		System.out.println("ProgressBar es null? " + (progressBar == null));
		startProgress();

		// llamada al handleChoice(); Manejador de los eventos del ChoiceBox
		handleChoice();

		// Listener para obtener el valor actualizado del Slider
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			double sliderValue = newValue.doubleValue();
			System.out.println("Valor Slider: " + sliderValue);
			// Llamamos al método que maneja el cambio de valor. Manejador del
			// UI se ejecutará cuando detecte un cambio en el valor
			handleSliderChange();
		});
		// llamada al hancleCheckBox. Manejador de los eventos del CheckBox
		handleCheckBox();

		// Crear grupo con los radioButtons para que sean discrimintes
		groupColor = new ToggleGroup();
		radioButton1.setToggleGroup(groupColor);
		radioButton2.setToggleGroup(groupColor);
		radioButton3.setToggleGroup(groupColor);
		handleRadioButton();

		// Cargamos valores en la lista miLista
		// Agregar elementos al ListView programáticamente
		miLista.setItems(FXCollections.observableArrayList("Elemento 1",
				"Elemento 2", "Elemento 3", "Elemento 4"));

		// Manejar la selección de elementos. Manejador de los eventos del
		// ListView
		handleListView();

		/**
		 * Si queremos permitir la seleccion de varios valores
		 */
		// Configurar el ListView para permitir selección múltiple

		miLista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		miLista.getSelectionModel().getSelectedItems()
				.addListener(new ListChangeListener<String>() {

					@Override
					public void onChanged(Change<? extends String> change) {
						while (change.next()) {
							if (change.wasAdded()) {
								System.out.println("Elementos seleccionados: "
										+ change.getAddedSubList());
							}
							if (change.wasRemoved()) {
								System.out.println(
										"Elementos eliminados de la selección: "
												+ change.getRemoved());
							}
						}
					}
				});

		// Cargamos valores en la tabla. TableView

		// Configurar las columnas
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		age.setCellValueFactory(new PropertyValueFactory<>("age"));

		// Creamos estudiante que se cargarán en la tabla
		ObservableList<Estudiante> estudiantes = FXCollections
				.observableArrayList(new Estudiante(1, "Ana", "García", 20),
						new Estudiante(2, "Luis", "Martínez", 27),
						new Estudiante(3, "María", "López", 19));

		// Cargar datos en el TableView
		table.setItems(estudiantes);

	}

	/**
	 * Manejador de TableView. Al hacer doble clic sobre una fila, obtiene esta
	 * fila
	 **/
	/*
	 * private void handleTable() { table.setRowFactory( (TableView<Estudiante>
	 * estudiante) -> { TableRow<Estudiante> row = new TableRow<>();
	 * row.setOnMouseClicked(event -> { if (event.getClickCount() == 2 &&
	 * (!row.isEmpty())) { Estudiante rowD = row.getItem();
	 * System.out.println(rowD.getName()); } }); return row; });
	 * 
	 * }
	 */

	private void handleCheckBox() {
		checkBox1.setOnAction((event) -> {
			boolean selected = checkBox1.isSelected();
			System.out.println("CheckBox1 (selected: " + selected + ")");
		});
		checkBox2.setOnAction((event) -> {
			boolean selected = checkBox2.isSelected();
			System.out.println("CheckBox2 (selected: " + selected + ")");
		});
		checkBox3.setOnAction((event) -> {
			boolean selected = checkBox3.isSelected();
			System.out.println("CheckBox3 (selected: " + selected + ")");
		});

	}

	@FXML
	private void handleChoice() {
		choiceBox.valueProperty()
				.addListener((ChangeListener<String>) (ov, t, t1) -> {
					String valor = t1;
					System.out.println(valor);
				});
	}

	/**
	 * handleSlider Método que recibe el valor cada vez que cambia el Slider
	 * 
	 * @param newValue
	 */
	@FXML
	private void handleSliderChange() {
		// Listener para obtener el valor actualizado del Slider
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValor,
					Number oldValor, Number newValor) {
				lbslider.textProperty().setValue(
						"Valor " + String.valueOf(newValor.intValue()));
			}
		});
	}

	/**
	 * handle de ListView
	 */
	private void handleListView() {
		miLista.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					System.out.println("Elemento seleccionado: " + newValue);
				});
	}

	/**
	 * Método para iniciar la actualización del progreso
	 */
	@FXML
	private void startProgress() {
		// Inicializamos el progreso en 30
		progressBar.setProgress(30);

		progressIndicator.setProgress(30);

		// Creamos un nuevo hilo para actualizar el progreso
		Thread thread = new Thread(() -> {
			try {
				// Simulamos la actualización gradual del progreso
				for (int i = 1; i <= 100; i++) {
					final double progress = i / 100.0;

					// Usamos Platform.runLater para actualizar el UI desde el
					// hilo de JavaFX
					Platform.runLater(() -> progressBar.setProgress(progress));

					Platform.runLater(
							() -> progressIndicator.setProgress(progress));

					// Simulamos un trabajo pesado (por ejemplo, una tarea de
					// fondo)
					Thread.sleep(100); // Espera de 100 ms
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Inicia el hilo
		thread.start();
	}

	/**
	 * handleRadioButton. Manejador de RadioButton
	 */
	@FXML
	private void handleRadioButton() {
		// Listener para mostrar el valor seleccionado
		groupColor.selectedToggleProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (groupColor.getSelectedToggle() != null) {
						RadioButton selected = (RadioButton) groupColor
								.getSelectedToggle();
						System.out.println(
								"Color seleccionado: " + selected.getText());
					}
				});

	}

	/**
	 * handleSalir. Manejador para salir de la Aplicación
	 */
	@FXML
	private void handleSalirApp() {
		Stage stage = (Stage) this.menuBar.getScene().getWindow();
		stage.close();
	}

}
