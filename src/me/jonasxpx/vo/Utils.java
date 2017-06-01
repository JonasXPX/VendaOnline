package me.jonasxpx.vo;

import java.io.BufferedReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

public class Utils {
	
	public static String urlParse(String urls){
		try{
			URL url = new URL("http://api.adf.ly/api.php?key=9b948fd24e930e130740b15a400b7d63&uid=202711&advert_type=int&domain=adf.ly&url=" + urls);
			try(BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()))){
				return r.readLine();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH-mm").format(new Date());
	}

}
