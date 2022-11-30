package cool_shop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddController implements Initializable {

	private ObservableList<ShopItem> items = FXCollections.observableArrayList();

	public ObservableList<ShopItem> getItems() {
		return items;
	}

	// -------------------------------------------------table
	@FXML
	private TableView<ShopItem> TableView_output;
	@FXML
	private TableColumn<ShopItem, Integer> IdColumn;
	@FXML
	private TableColumn<ShopItem, String> NameColumn;
	@FXML
	private TableColumn<ShopItem, Float> PriceColumn;
	// --------------------------------------------------add
	@FXML
	private TextField textField_add_name;
	@FXML
	private TextField textField_add_price;
	@FXML
	private Button add_button;
	// -------------------------------------------------remove
	@FXML
	private TextField textField_remove_byID;
	@FXML
	private Button remove_button;
	// -------------------------------------------------error
	@FXML
	private Label error_label_add;

	public void toEdit(ActionEvent event) throws IOException, ClassNotFoundException {
		MainMenuControler call_scene = new MainMenuControler();
		call_scene.switchToSceneEdit(event);
	};

	public void add(ActionEvent e) {
		try {
			String item_name = textField_add_name.getText();

			float item_price = Float.valueOf(checkFloatImput(textField_add_price.getText()));

			items.add(new ShopItem(items.size() + 1, item_name, item_price));
			arrListToFile(items);
		} catch (ClassNotFoundException | IOException e1) {
			error_label_add.setText("Write error");
			e1.printStackTrace();
		}
	}

	public void remove(ActionEvent e) {
		try {
			//get id from txt field
			int id = Integer.parseInt(textField_remove_byID.getText());
			//chek if id is valid 
			if(id>items.size() || id <0) {
				error_label_add.setText("Id not found");
			}
			//removes from 
			items.remove(id - 1);
			arrListToFile(items);

			for (int i = id - 1; i < items.size(); i++) {
				int temp = items.get(i).getItem_id() - 1;
				items.get(i).setItem_id(temp);
			}
			
		} catch (ClassNotFoundException | IOException e1) {
			error_label_add.setText("Write error");
			e1.printStackTrace();
		}

	}

	public String checkFloatImput(String str) {
		try {
			Float.parseFloat(str);
		} catch (NumberFormatException e) {
			error_label_add.setText("Imput is not float");
			e.printStackTrace();
		}
		return str;
	}

	public void arrListToFile(ObservableList<ShopItem> new_items) throws IOException, ClassNotFoundException {

		FileOutputStream f = new FileOutputStream("ShopItems.ser");

		try (ObjectOutputStream oos = new ObjectOutputStream(f)) {
			oos.writeObject(new ArrayList<>(items));
		}
	}

	public void FileToArrList() throws IOException, ClassNotFoundException {
		FileInputStream f = new FileInputStream("ShopItems.ser");
		try (ObjectInputStream ois = new ObjectInputStream(f)) {
			items = FXCollections.observableList((List<ShopItem>) ois.readObject());
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {

			FileToArrList();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		IdColumn.setCellValueFactory(new PropertyValueFactory<ShopItem, Integer>("item_id"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<ShopItem, String>("item_name"));
		PriceColumn.setCellValueFactory(new PropertyValueFactory<ShopItem, Float>("item_price"));
		TableView_output.setItems(items);

		// System.out.println(items.toString());

	}
}
