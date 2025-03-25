package RahulShettyAcademy.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	// This is an utility to scan the Json, and convert to String, then to hashmap

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		// Read Jason to String below
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\RahulShettyAcademy\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
//		Now convert to HashMap - use jackson databind API ---> Add the dependency in pom.xml
//		As shown below ObjectMapper is class, and inside the class, readValue method reads jason and converts to string
//		As shown below TypeReference is asking which type of HashMaps you want
//		List<HashMap<String, String> stores two hashmaps, In 0th location of the List map, on 1st location of the List map1
		
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		
		//Took the above method in Base Class

		// {map, map}

	}
}
