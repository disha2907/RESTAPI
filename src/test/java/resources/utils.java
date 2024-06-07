package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class utils {
    TestDataBuild ts;
    public static RequestSpecification req; //static so that its not null for every run and does not override the logging file if there are more than one test data

    public RequestSpecification requestSpecification() throws IOException {

        if(req == null) {
            PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
            RestAssured.baseURI = "https://rahulshettyacademy.com";
            ts = new TestDataBuild();
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(stream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Disha Grover\\Documents\\RestAPI\\APICucumber\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        String propertyKey = prop.getProperty(key);
        return propertyKey;
    }

    public String getJsonPath(Response response, String key){
        String jsResponse = response.asString();
        JsonPath js = new JsonPath(jsResponse);
        return js.get(key).toString();

    }
}
