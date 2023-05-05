package Controllers;


import com.example.buildingmaterials.BuildingMaterialsApplication;

public class BaseViewController {
    protected String getTitle(){
        return "";
    }
    public BaseViewController() {
        SetTitle(getTitle());
    }

    private void SetTitle(String title) {
        BuildingMaterialsApplication.primaryStage.setTitle(title);
    }

}
