package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.plugin.event.StepDefinition;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        placeValidationStepDefinition def = new placeValidationStepDefinition();
        if (placeValidationStepDefinition.place_id == null) {

            def.addPlacePayload("Disha", "Hindi", "India");
            def.userCallsWithHttpRequest("AddPlaceAPI", "POST");
            def.verifyPlace_idCreatedMapsToUsing("Disha", "getPlaceAPI");
        }
    }
}
