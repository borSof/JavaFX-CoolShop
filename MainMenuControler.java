package cool_shop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainMenuControler implements Initializable {

	private Stage stage;
	private Scene scene;
	private Stage root;

	AddController call_functionality = new AddController();

	@FXML
	private TableView<ShopItem> TableView_output_main;
	@FXML
	private TableColumn<ShopItem, Integer> IdColumn_main;
	@FXML
	private TableColumn<ShopItem, String> NameColumn_main;
	@FXML
	private TableColumn<ShopItem, Float> PriceColumn_main;

	// -----------------------------scnene
	// switch---------------------------------------------
	public void switchToSceneAdd(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("scene_add.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneEdit(ActionEvent event) throws IOException, ClassNotFoundException {
		Parent root = FXMLLoader.load(getClass().getResource("scene_edit.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	// -----------------------------scnene
	// switch---------------------------------------------

	// -----------------------------output to
	// TableView---------------------------------------------

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		try {
			call_functionality.FileToArrList();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		IdColumn_main.setCellValueFactory(new PropertyValueFactory<ShopItem, Integer>("item_id"));
		NameColumn_main.setCellValueFactory(new PropertyValueFactory<ShopItem, String>("item_name"));
		PriceColumn_main.setCellValueFactory(new PropertyValueFactory<ShopItem, Float>("item_price"));
		TableView_output_main.setItems(call_functionality.getItems());
		// System.out.println(items.toString());

	}
	// -----------------------------output to
	// TableView---------------------------------------------
}
