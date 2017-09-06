/**
* The Cayuga Dictionary project is to keep the language vibrant and alive 
* through immersion courses for adults and language daycare for children.
* 
* The project is Led by Dr. Carrie Dyck
* Faculty of Humanities and Social Sciences
* Memorial University of Newfoundland
*
* @author  Chen Zhang
* @version online 2.0
* @since   2016-12-01 
*/
package mun.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import mun.util.ImageEditor;

public class TempViewController implements Initializable{

	@FXML
	private ImageView imgView;
	private final ImageEditor imageEditor;
	
    public TempViewController() {
        imageEditor = new ImageEditor();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		imageEditor.setImageOn(imgView, "/buttons/cayuga.png", 91, 83);
	}

}
