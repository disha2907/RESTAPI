package resources;

public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String response;

    APIResources(String response){ //constructor which is called in step def
        this.response = response;
    }

    public String getResource(){
        return response;
    }
}
