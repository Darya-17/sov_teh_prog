package Controllers;

import com.example.buildingmaterials.BuildingMaterialsApplication;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    public static ScreenController instance = new ScreenController();
    private HashMap<String, String> screenMap = new HashMap<>();

    public void add(String name, String view_name) {
        addScreen(name, view_name);
    }

    public void remove(String name, String pane) {
        removeScreen(name);
    }

    public void activate(String name) throws IOException {
        activateScreen(name);
    }

    protected void addScreen(String name, String view_name) {
        screenMap.put(name, view_name);
    }

    protected void removeScreen(String name) {
        screenMap.remove(name);
    }

    private Pane getPane(String view_name) throws IOException {
        return BuildingMaterialsApplication.loadPane("/pages/" + screenMap.get(view_name));
    }

    protected void activateScreen(String name) throws IOException {
        if (BuildingMaterialsApplication.primaryScene == null)
            BuildingMaterialsApplication.primaryScene = new Scene(getPane(name), 867, 675);
        else
            BuildingMaterialsApplication.primaryScene.setRoot(getPane(name));
    }
}