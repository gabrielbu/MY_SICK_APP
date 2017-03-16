package com.example.gabrieluliano.app;
public class AppConfig {
	// Server user login url
	public static String URL_LOGIN = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/android_login_api/login.php";

	// Server user register url
	public static String URL_REGISTER = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/android_login_api/register.php";

	public static String IMAGE_UPLOAD = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/save_image.php";

	public static String URL_UPLOAD = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/sendtest.php";

	public static String HOME = "HomeActivity";

	public static String SEARCH = "ClothesSearch.class";

	public static String UPLOAD = "PhotoActivity.class";

	public static String LOCATION = "LocationActivity.class";

	public static String USERPAGE = "MainActivity.class";

	//php keys
	public static final String KEY_NAME = "name";
	public static final String KEY_USERID = "userID";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_LOCATIONX = "locationX";
	public static final String KEY_LOCATIONY = "locationY";
	public static final String KEY_TITLE = "title";
	public static final String KEY_BRAND = "brand";
	public static final String KEY_COMMENT = "comment";
	public static final String KEY_COLOUR = "colour";


	// File upload url (replace the ip with your server address)
	public static final String UPLOAD_URL = "sftp://ubuntu@ec2-54-171-67-69.eu-west-1.compute.amazonaws.com/images/upload.php";

	public static final String IMAGES_URL = "http://192.168.94.1/AndroidImageUpload/getImages.php";

	public static String get_name(){
		return "FoodOrClothesActivity";
	}
}
