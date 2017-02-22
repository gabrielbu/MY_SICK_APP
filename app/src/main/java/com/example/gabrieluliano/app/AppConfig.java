package com.example.gabrieluliano.app;
public class AppConfig {
	// Server user login url
	public static String URL_LOGIN = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/android_login_api/login.php";

	// Server user register url
	public static String URL_REGISTER = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/android_login_api/register.php";

	public static String IMAGE_UPLOAD = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/save_image.php";

	public static String URL_UPLOAD = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/sendtest.php";

	// File upload url (replace the ip with your server address)
	public static final String UPLOAD_URL = "sftp://ubuntu@ec2-54-171-67-69.eu-west-1.compute.amazonaws.com/images/upload.php";

	public static final String IMAGES_URL = "http://192.168.94.1/AndroidImageUpload/getImages.php";

	public static String get_name(){
		return "FoodOrClothesActivity";
	}
}
