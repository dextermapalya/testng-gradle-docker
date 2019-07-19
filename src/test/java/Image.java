package test.java;
import org.openqa.selenium.WebElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

class Image {

	private int invalidImageCount;

    public void verifyBrokenImage(WebElement imgElement) {
        invalidImageCount = 0;

		try {
            String BASE_URL = "http://dashboard.sunnxt.com:81/";
			HttpClient client = HttpClientBuilder.create().build();
            System.out.println("Image url is " + imgElement.getAttribute("src") );
			HttpGet request = new HttpGet( imgElement.getAttribute("src") );
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() === 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}