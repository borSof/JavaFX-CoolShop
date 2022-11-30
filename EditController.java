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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditController implements Initializable {
	private AddController call_functionality = new AddController();
	private MainMenuControler call_scene = new MainMenuControler();

	// -------------------------------------------------table
	@FXML
	private TableView<ShopItem> TableView_output_edit;
	@FXML
	private TableColumn<ShopItem, Integer> IdColumn_edit;
	@FXML
	private TableColumn<ShopItem, String> NameColumn_edit;
	@FXML
	private TableColumn<ShopItem, Float> PriceColumn_edit;
	// --------------------------------------------------edit
	@FXML
	private TextField textField_edit_name;
	@FXML
	private TextField textField_edit_price;
	@FXML
	private Button edit_button;
	// -------------------------------------------------select
	@FXML
	private TextField textField_edit_byID;
	@FXML
	private Button select_id_button;
	// -------------------------------------------------error
	@FXML
	private Label error_label_add;

	@FXML
	private Button to_addRemove;

	public void toAddRemove(ActionEvent event) throws IOException {
		call_scene.switchToSceneAdd(event);
	};

	public void select() {
		int id = Integer.parseInt(textField_edit_byID.getText()) - 1;

		textField_edit_name.setText(call_functionality.getItems().get(id).getItem_name());

		textField_edit_price.setText(Float.toString(call_functionality.getItems().get(id).getItem_price()));
	}

	public void edit() throws ClassNotFoundException, IOException {
		int id = Integer.parseInt(textField_edit_byID.getText());
		String str = textField_edit_name.getText();
		float item_price = Float.valueOf(call_functionality.checkFloatImput(textField_edit_price.getText()));

		call_functionality.getItems().set(id - 1, new ShopItem(id, str, item_price));

		call_functionality.arrListToFile(call_functionality.getItems());
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		try {
			call_functionality.FileToArrList();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		IdColumn_edit.setCellValueFactory(new PropertyValueFactory<ShopItem, Integer>("item_id"));
		NameColumn_edit.setCellValueFactory(new PropertyValueFactory<ShopItem, String>("item_name"));
		PriceColumn_edit.setCellValueFactory(new PropertyValueFactory<ShopItem, Float>("item_price"));
		TableView_output_edit.setItems(call_functionality.getItems());
		// System.out.println(items.toString());

	}
}
