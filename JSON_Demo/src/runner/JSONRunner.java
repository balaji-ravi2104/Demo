package runner;

import java.util.Iterator;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class JSONRunner {

	public static void main(String[] args) throws JSONException {
		// 1. CDL - Comma-Delimited List
//		String data1 = "Balaji,Surya,Ponvel";
//
//		JSONArray jsonArray = CDL.rowToJSONArray(new JSONTokener(data1));
//		System.out.println(jsonArray);
//
//		String data2 = CDL.rowToString(jsonArray);
//		System.out.println(data2);
//
//		String data3 = "empId,Name,Age\n" + "101,Balaji,21\n" + "102,Surya,20\n" + "103,Ponvel,22";
//		JSONArray jsonArray2 = CDL.toJSONArray(data3);
//		System.out.println(jsonArray2);
//
//		JSONArray jsonArray3 = new JSONArray();
//		jsonArray3.put("Id");
//		jsonArray3.put("Name");
//		jsonArray3.put("Dept");
//		String data4 = "101,Balaji,CCE\n" + "102,Surya,ECE\n" + "103,Ponvel,CSE";
//
//		JSONArray jsonArray4 = CDL.toJSONArray(jsonArray3, data4);
//		System.out.println(jsonArray4);
//		
//		System.out.println("-".repeat(100));
//		System.out.println("-".repeat(100));

		
		
		// 2.Cookie

//		String cookie = "username = Balaji Ravi; expires = Sun, 21 Apr 2024 12:00:00 UTC; path = /";
//		JSONObject jsonObject = Cookie.toJSONObject(cookie);
//
//		System.out.println(jsonObject);
//
//		String convertedCookie = Cookie.toString(jsonObject);
//		System.out.println(convertedCookie);
//
//		System.out.println("-".repeat(100));
//		System.out.println("-".repeat(100));
		
		
		
		// 3.CookieList
//		String[] parts = cookie.split(";");
//
//        JSONObject cookieJSONObject = new JSONObject();
//
//        for (String part : parts) {
//            String[] keyValue = part.trim().split("=");
//
//            if (keyValue.length == 2) {
//                String key = keyValue[0].trim();
//                String value = keyValue[1].trim();
//                cookieJSONObject.put(key, value);
//            }
//        }
//        
//        String cookieList = CookieList.toString(cookieJSONObject);
//        System.out.println(cookieList);
//        System.out.println(CookieList.toJSONObject(cookieList));
//        
//        System.out.println("-".repeat(100));
//        System.out.println("-".repeat(100));
        
        
        
        //4.HTTP
//        String headerText = "POST \"http://www.tutorialspoint.com/\" HTTP/1.1";
//        JSONObject httpJSONObject = HTTP.toJSONObject(headerText);
//        
//        System.out.println(httpJSONObject);
//        
//        JSONObject jsonObject2 = new JSONObject();
//        jsonObject2.put("Method", "POST");
//        jsonObject2.put("Request-URI", "http://www.tutorialspoint.com/");
//        jsonObject2.put("HTTP-Version", "HTTP/1.1");
//        
//        String headerText2 = HTTP.toString(jsonObject2);
//        System.out.println(headerText2);
//        
//        System.out.println("-".repeat(100));
//        System.out.println("-".repeat(100));
        
        
        //5.JSONArray
        JSONArray jsonArray5 = new JSONArray();
        jsonArray5.put("Balaji");
        jsonArray5.put(false);
        jsonArray5.put(2104.2003);
        jsonArray5.put(21);
        jsonArray5.put(JSONObject.NULL);
        
        System.out.println("JSON Array :"+jsonArray5);
        System.out.println(jsonArray5.get(2)); // if index exceeded then JSONException will be thrown..
        System.out.println(jsonArray5.length());
        System.out.println(jsonArray5.opt(6)); // if index exceeded then null will be returned..
        System.out.println(jsonArray5.toString(2));
        
        System.out.println("-".repeat(100));
        System.out.println("-".repeat(100));
        
        //6.JSONML
//        JSONArray list = new JSONArray();
//        list.put("name");
//        list.put("Balaji");
//        
//        System.out.println("XML from a JSONArray: ");
//        String xml = JSONML.toString(list);
//        System.out.println(xml);
//        
//        System.out.println("JSONArray from a XML: ");
//        list = JSONML.toJSONArray(xml);
//        System.out.println(list);
//        
//        System.out.println("JSONObject from a XML: ");
//        JSONObject jo = JSONML.toJSONObject(xml);
//        System.out.println(jo.toString(4));
//        
//        System.out.println("XML from a JSONObject: ");
//        xml = JSONML.toString(jo);
//        System.out.println(xml);
//        
//        System.out.println("-".repeat(100));
//        System.out.println("-".repeat(100));
        
        //7.JSONObject
        JSONObject object = new JSONObject();
        object.put("Name", "Balaji");
        object.put("ID",7360);
        object.put("Salary",57000.245);
        object.put("Status",true);
        object.put("Others",JSONObject.NULL);
        
        System.out.println(object.toString(4));
        
        System.out.println("-".repeat(100));
        System.out.println("-".repeat(100));
        
        
        //8.JSONStringer
        String jsonText = new JSONStringer()
        		.object()
        		.key("name")
        		.value("Balaji")
        		.endObject()
        		.toString();       
        System.out.println(jsonText);
        
        jsonText = new JSONStringer()
        		.array()
        		.value("Balaji")
        		.value("Sharan")
        		.value("Ponvel")
        		.value("Surya")
        		.endArray()
        		.toString();      
        System.out.println(jsonText);
        
        jsonText = new JSONStringer()
        		.array()
        		.value("Balaji")
        		.value("Sharan")
        		.value("Ponvel")
        		.value("Surya")
        		.object()
        		.key("name")
        		.value("Balaji")
        		.endObject()
        		.endArray()
        		.toString();
        System.out.println(jsonText);

        System.out.println("-".repeat(100));
        System.out.println("-".repeat(100));
        
        // 9. Properties
        Properties properties = new Properties();
        properties.put("Name","Balaji");
        properties.put("Team", "I-AM Engineering");
        
        
        System.out.println("Properties to JSON");
        JSONObject jsonObject3 = new JSONObject(properties);
        System.out.println(jsonObject3);
        
        System.out.println("JSON to properties");
        Properties propertiesFromJSON = new Properties();
        Iterator<String> keys = jsonObject3.keys();
        
        while (keys.hasNext()) {
            String key = keys.next();
            propertiesFromJSON.setProperty(key, jsonObject3.getString(key));
        }
        System.out.println(propertiesFromJSON);
        
        System.out.println("-".repeat(100));
        System.out.println("-".repeat(100));
        
        // 10.XML
        
//        String xmlText = XML.toString(object);
//        System.out.println(xmlText);
//        
//        JSONObject jsonObject4 = XML.toJSONObject(xmlText);
//        System.out.println(jsonObject4.toString(4));
//        
//        System.out.println("-".repeat(100));
//        System.out.println("-".repeat(100));
        
        // 11.JSONException
        
//        try {
//            //XML tag name should not have space.
//            String xmlText1 = "<Employee Name>Balaji Ravi</Employee Name>";
//            System.out.println(xmlText1);
//
//            //Convert an XML to JSONObject
//            System.out.println(XML.toJSONObject(xmlText1));
//         } 
//         catch(JSONException e){   
//            System.out.println(e.getMessage());
//         }
	}

}
