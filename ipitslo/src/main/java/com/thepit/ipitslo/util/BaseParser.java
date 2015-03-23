package com.thepit.ipitslo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.net.Uri;
import android.util.Log;

import com.thepit.ipitslo.model.BlogEntry;

public class BaseParser<T extends Object> {

	public static final String TAG = "BaseParser";
	
	private String ITEM_KEY = "item";
	
	byte[] getUrlBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			
			if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
			
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();
		} finally {
			connection.disconnect();
		}
	}
	
	public String getUrl(String urlSpec) throws IOException {
		return new String(getUrlBytes(urlSpec));
	}
	
    public ArrayList<T> downloadItems(String url, String className, Map<String,String> objKey) {
        ArrayList<T> items = new ArrayList<T>();
        
        try {
            String xmlString = getUrl(url);
            Log.i(TAG, "Received xml: " + xmlString);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));

            parseItems(items, objKey, parser, className);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (XmlPullParserException xppe) {
            Log.e(TAG, "Failed to parse items", xppe);
        }
        return items;
    }
    
    
    void parseItems(ArrayList<T> items, Map<String, String> objKey, XmlPullParser parser, String className) throws XmlPullParserException, IOException {
        int eventType = parser.next();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG &&
                ITEM_KEY.equals(parser.getName())) {
                //String title = parser.getAttributeValue(null, "title");
                //String detailLink = parser.getAttributeValue(null, "link");


				try {
					Class clazz = Class.forName(className);
					Object item = clazz.newInstance();

                    while(eventType != XmlPullParser.END_TAG || !ITEM_KEY.equals(parser.getName())) {
                        if(objKey.containsValue(parser.getName())) {
                            String fieldName = parser.getName();
                            String fieldValue = parser.nextText();

                            Field field;
                            try {
                                field = clazz.getDeclaredField(fieldName);
                                field.setAccessible(true);
                                Object objFieldValue = fieldValue;
                                field.set(item, objFieldValue);
                            } catch (NoSuchFieldException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        eventType = parser.next();
                    }
                	                	


                //Class <T> clazz1 = clazz;
                
                	T item2 = (T) item;
					T t = item2;
                	items.add(t);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch(IllegalAccessException e2) {
					e2.printStackTrace();
				} catch(InstantiationException e3) {
					e3.printStackTrace();
				}
            }

            eventType = parser.next();
        }
    }
    
    /**
     * fetch items returning array list with Contents of Type T
     * @return
     */
    public ArrayList<T> fetchItems(Map<String, String> endpointKey, Map<String, String> objKey) {
        String url = endpointKey.get(CoreConstants.DATA_URL);
        String className = endpointKey.get(CoreConstants.CLASS_NAME);
        ITEM_KEY = endpointKey.get(CoreConstants.PARSER_ITEM_KEY);
        return downloadItems(url, className, objKey);
    }
}
