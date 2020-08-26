package com.springboot.backend.apirest.auth;

/**
 * 
 * @author juan
 * {@link https://www.openssl.org/}
 */
public class JwtConfig {

	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345";
	
	/**
	 * @param RSA_PRIVADA generada con: openssl rsa -in jwt.pem
	 */
	public static final String RSA_PRIVADA= "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpQIBAAKCAQEAvfULbmnViFkeooqUFeP2sQkbFzGV/cwI82uBXloP7jwc5uEd\n" + 
			"HHnLX6DhPqbw7gi9Vd+LePrlPoJGSTwWN8ad1BX/3yXdajOLgPcm+0jni2V13o3N\n" + 
			"J0oGvkERAB/Yoa4uQO4W3/SZqRcPJRFLf1/kr5ejypXSf38HbAdqt4QlKMW5D+vf\n" + 
			"fHtNjJCL4ee3j4zXVxkYDw9bjUnCfW499ZnUSVvnarH835oWLsl3GWWH8b6bFRyI\n" + 
			"hIowdxza3zeGXNjoiRqy+LRWcPAxnXe7BtIu6YOi1Cc/CqPtcT4iCKIW+K9HoQr5\n" + 
			"uBbBkjN929RsDf8nL9zbv5EEZcV9zMSzKPhb/wIDAQABAoIBAQC6A+mS0R7iq2rd\n" + 
			"ftju6tKboLAfsVYRN6mYPbFs5MLu53zh9pYcWYR3e8K3KKGMZ5CJNCSPbxnocYhE\n" + 
			"lQuoyN2rX3O2YI0HP7yfqOdKcaUUk2Guc6vvioU579unBIiOI1R2DvDllNCYjdUt\n" + 
			"1lbZP91guh0R+E8uvQVWm5i8ZzJATlaxlCzfbfkrUH9B4XkEEvPYv1L8PV3iy9j0\n" + 
			"N4Bit6Pbdkz78xftSVUH/XqEyB5MSBe1wXCfkslZFuohxRJBfnmXcHk0pQVe3iX3\n" + 
			"tm1dE44xatm6MWwKjPUFee0cHaneiF88LwLSaBklkGLawHOFtbFiqdAzdd6KrgAz\n" + 
			"OawFKwExAoGBAOfNgeNxHRIZwVVuljVKWwph60ShLmGs2jQuIYXn6QpfiwaRBoz3\n" + 
			"MrQo4dHfaFpxRL4sqKcIKlLYKNUaPgsRc8jhu9/AcLL4cTKA6ixjH+UKMEQESGyS\n" + 
			"KP+rngGDIz9t90TsbLPcu0V0ejJCCcOBrwgw7EyGMWUswCDIuwLsPo8JAoGBANHJ\n" + 
			"SyLUqfv8oS1/af1U7l8VMqEyqD2QmgP1yxxqQCBCFUuaHwGKH6DJxvEbjPSucHkR\n" + 
			"mSf8n0VKav8iD27MfruD3EBpz26Pu9ZAyolGuM4y0Y6KMcqwMbFr0Gg8Jra4SKfM\n" + 
			"eayQKmcA/DNa8bikl8xoJuPG9SoUnImozoHSfMzHAoGBAJVGRosNYMczRsmZeam/\n" + 
			"xHkfLb74fUHxamOIAZaWDgDcHk+QPUOyPTi/ywy4bh8wKjC92a4XGApZoZH6YFII\n" + 
			"XyPSGoaxCuJ2E4+ObT9AZl28/fAPQgSI61YaUpputNg4HLCKAEajswW7SKXs2RTK\n" + 
			"Asmnl8R/9ndNJjz2h1gOgwrhAoGAZV0vssqyv5DXiBZSbheFfefOKNlDNUwTPuqa\n" + 
			"k0Y7adrEU11KsgG9OoGEYKeBOhC3Q6hfKlUD2AUXIeOhNVYVviD+YRk/IxrMFcAE\n" + 
			"nMPRM8CWRJs9Z8Qunp2Le3Hk+tCcOoewGtMlydNOCLpepWrR9fA1KkXN2+FNqa4o\n" + 
			"6c18g60CgYEAwSmufaXUoH1OXndhui6n94mHBPrJlnZdNKfoiVrDvjWR6fOnloyz\n" + 
			"MvLXQZf+9OdRmQmmxaakw00DWyd9GeAN5s8bQ61mBHZ3qli2BmnM6kfZzyxnkIqq\n" + 
			"pK2TcnYPN7Kt6cTK9e4NipjhVcnhx1aD+qQYw9U3k517o+1J+Gh1fEc=\n" + 
			"-----END RSA PRIVATE KEY-----";
	/**
	 * @param RSA_PUBLICA generada con: openssl rsa -in jwt.pem
	 */
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvfULbmnViFkeooqUFeP2\n" + 
			"sQkbFzGV/cwI82uBXloP7jwc5uEdHHnLX6DhPqbw7gi9Vd+LePrlPoJGSTwWN8ad\n" + 
			"1BX/3yXdajOLgPcm+0jni2V13o3NJ0oGvkERAB/Yoa4uQO4W3/SZqRcPJRFLf1/k\n" + 
			"r5ejypXSf38HbAdqt4QlKMW5D+vffHtNjJCL4ee3j4zXVxkYDw9bjUnCfW499ZnU\n" + 
			"SVvnarH835oWLsl3GWWH8b6bFRyIhIowdxza3zeGXNjoiRqy+LRWcPAxnXe7BtIu\n" + 
			"6YOi1Cc/CqPtcT4iCKIW+K9HoQr5uBbBkjN929RsDf8nL9zbv5EEZcV9zMSzKPhb\n" + 
			"/wIDAQAB\n" + 
			"-----END PUBLIC KEY-----"; 
}
