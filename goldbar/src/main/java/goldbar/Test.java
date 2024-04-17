package goldbar;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;



public class Test {
	public static void  main(String[] args) {
		
		
        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
        
        // Launch the website
        driver.get("http://sdetchallenge.fetch.com/");

        
        // Perform test actions
        try {
        	
        	String buttonTextAfter="";
        	
        	String[] weights= {"0","1","2","3","4","5","6","7","8"};
    		
    		int l=0;
    		int r=8;
        	
    		// Algorithm to find fake gold bar in minimum number of weighings
        	while(l<r) {
        		
    			// Click the "Reset" button
    	        WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));

    	        resetButton.click();    	        
    			
    			fillLeftBowl(driver, l, (int)Math.ceil((l+r)/2.0),weights); 
    			fillRightBowl(driver,(int)Math.floor((l+r)/2.0)+1,r,weights); 
    			
    			// Get the button element
    	        WebElement button = driver.findElement(By.id("reset"));

    	        // Get the text of the button before clicking "Weigh"
    	        String buttonTextBefore = button.getText();
    			
    			 // Click the "Weigh" button
    	        WebElement weighButton = driver.findElement(By.id("weigh"));
    	        weighButton.click();
    	        
    	        // Wait for the button text to change
    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(button, buttonTextBefore)));

    	        // Get the text of the button after clicking "Weigh"
    	        buttonTextAfter = button.getText();
    	         
    	         if(buttonTextAfter.equals("=")) {
    	             
    	             break;
    	        	 
    	         }
    	         else if(buttonTextAfter.equals("<")){
    	        	 
    	        	 r= (int) (Math.ceil((l+r)/2.0))-1;
    	        	 
    	        	 
    	         }
    	         else if(buttonTextAfter.equals(">")){
    	        	 l= (l+r)/2 +1;
    	        	
    	         }

    			
    			
    		}
        	
        	Thread.sleep(12000);
        	              
              
            //printing the Weighing list on the output console
            showWeighingList(driver);
        	
        	//Clicking on the bottom bar buttons and finally finding the fake one
        	if(buttonTextAfter.equals("=")) {
        		
        		 WebElement goldBarButton = driver.findElement(By.id("coin_4"));
	             
	             // Click on the gold bar number button
	             goldBarButton.click();

	             // Wait for the alert to be present
	    	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             wait.until(ExpectedConditions.alertIsPresent());

	             // Switch to the alert
	             Alert alert = driver.switchTo().alert();

	             // Get the text of the alert
	             String alertText = alert.getText();
	             
	             if (alertText.contains("Yay! You find it!")) {
	                 System.out.println("testGoldBarButtons test Passed");
	             } else {
	                 System.out.println("testGoldBarButtons test Failed.");
	             }
    	
        	}
        	else if(buttonTextAfter.equals("<")) {
        		 WebElement goldBarButton = driver.findElement(By.id("coin_"+r));
	             
	             // Click on the gold bar number button
	             goldBarButton.click();

	             // Wait for the alert to be present
	    	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             wait.until(ExpectedConditions.alertIsPresent());

	             // Switch to the alert
	             Alert alert = driver.switchTo().alert();

	             // Get the text of the alert
	             String alertText = alert.getText();
	             
	             if (alertText.contains("Yay! You find it!")) {
	                 System.out.println("testGoldBarButtons test Passed");
	             } else {
	                 System.out.println("testGoldBarButtons test Failed.");
	             }
        	}
        	else if(buttonTextAfter.equals(">")) {
        		 WebElement goldBarButton = driver.findElement(By.id("coin_"+l));
	             
	             // Click on the gold bar number button
	             goldBarButton.click();

	             // Wait for the alert to be present
	    	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	             wait.until(ExpectedConditions.alertIsPresent());

	             // Switch to the alert
	             Alert alert = driver.switchTo().alert();

	             // Get the text of the alert
	             String alertText = alert.getText();
	             
	             if (alertText.contains("Yay! You find it!")) {
	                 System.out.println("testGoldBarButtons test Passed");
	             } else {
	                 System.out.println("testGoldBarButtons test Failed.");
	             }
        		
        	}

             
           
        	
        	//	Comment out the above algorithm and Uncomment the below code to see individual test cases for checking UI functions
        	
        	/*
        	//TEST CASES for checking UI FUNCTIONS 
        	
        	
        	// Test case for checking function of Weigh button and getting a list of weighing
        	testWeighingProcess(driver); // a. clicks on buttons (“Weigh”, “Reset”)
        								 //d. getting a list of weighing

        	// Test case for checking function of Reset button
        	testResetButton(driver); //a. clicks on buttons (“Weigh”, “Reset”)
        	
        	// Test case for checking measurement results
            testMeasurementResults(driver); //b. Getting the measurement results (field between the 'bowls')

            
        	// Test case for checking conditions of Filling the Bowls with Numbers
            testBowlFillingConditionsSameBars(driver); //c. filling out the bowls grids with bar numbers (0 to 8)

            testBowlFillingConditionsDuplicates(driver); //c. filling out the bowls grids with bar numbers (0 to 8)
            
        	// Test case for checking function of Bottom Gold Bar button
            testGoldBarButtons(driver); //e. Clicking on the gold bar number at the bottom of the website and checking for the alert message

            */
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
		
	}
	
	// Method to fill left Bowl with gold bars
	private static void fillLeftBowl(WebDriver driver,int low, int high, String[] weights) {
		 for (int i = low; i < high; i++) {
	            String bowlId = "left" + "_" + i;
	            WebElement bowl = driver.findElement(By.id(bowlId));
	            bowl.sendKeys(weights[i]);
	        }
	}
	
	// Method to fill right Bowl with gold bars
	private static void fillRightBowl(WebDriver driver,int low, int high, String[] weights) {
		 for (int i = low; i <= high; i++) {
	            String bowlId = "right" + "_" + i;
	            WebElement bowl = driver.findElement(By.id(bowlId));
	            bowl.sendKeys(weights[i]);
	        }
	}
	
	
	//d. getting a list of weighing
	private static void showWeighingList(WebDriver driver) {
		// Find the WebElement containing the weighing results
        WebElement weighingResultsElement = driver.findElement(By.xpath("//ol"));

        // Get the text of the WebElement
        String weighingResultsText = weighingResultsElement.getText();

        // Split the text into individual weighing results
        String[] resultsArray = weighingResultsText.split("\n");

        // Store the weighing results in a list
        List<String> weighingResults = new ArrayList<String>();
        for (String result : resultsArray) {
            weighingResults.add(result);
        }

        // Display the output on the console
        System.out.println("Weighing Results:");
        for (String result : weighingResults) {
            System.out.println(result);
        }
		
	}
	
	
	//a. clicks on buttons (“Weigh”, “Reset”)
	//d. getting a list of weighing
	 private static void testWeighingProcess(WebDriver driver) {
	        // Place gold bars on the scale
	        WebElement leftBowl = driver.findElement(By.id("left_0"));
	        leftBowl.sendKeys("0");

	        WebElement rightBowl = driver.findElement(By.id("right_1"));
	        rightBowl.sendKeys("1");
	        
	        WebElement leftBowl1 = driver.findElement(By.id("left_1"));
	        leftBowl1.sendKeys("2");

	        WebElement rightBowl1 = driver.findElement(By.id("right_2"));
	        rightBowl1.sendKeys("3");

	        // Click the "Weigh" button
	        WebElement weighButton = driver.findElement(By.id("weigh"));
	        weighButton.click();

	        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
	        
	        WebElement newElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='game-info']/*")));
	        
	        // The below code will help to display output on Console whether the test case for Weigh button passed or not
            // Check if a we are getting a list of weighing
            if (newElement.isDisplayed()) {
                System.out.println("The Weigh button is working, test Passed.");
            } else {
                System.out.println("The Weigh button is not working, test Failed.");
            }
	    }
	 
	 
	 //a. clicks on buttons (“Weigh”, “Reset”)
	 private static void testResetButton(WebDriver driver) {
		 	WebElement leftBowl = driver.findElement(By.id("left_0"));
	        leftBowl.sendKeys("0");
	        // Click the "Reset" button
	        WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
	        resetButton.click();

	        // Verify that bowls are empty after reset
	        List<WebElement> leftBowls = driver.findElements(By.cssSelector("#left-grid .cell"));
	        List<WebElement> rightBowls = driver.findElements(By.cssSelector("#right-grid .cell"));
	        
	        // The below code will help to display output on Console whether the test case for Reset button passed or not

	        if (leftBowls.isEmpty() && rightBowls.isEmpty()) {
	            System.out.println("Reset button test Passed");
	        } else {
	            System.out.println("Reset button test Failed");
	        }
	    }
	 
	// b. Getting the measurement results (field between the 'bowls')
	 
	 private static void testMeasurementResults(WebDriver driver) {
		// Get the button element
         WebElement button = driver.findElement(By.id("reset"));

         // Get the text of the button before clicking "Weigh"
         String buttonTextBefore = button.getText();
         System.out.println("Button Text Before Weigh: " + buttonTextBefore);

         // Click the "Weigh" button
         WebElement weighButton = driver.findElement(By.id("weigh"));
         weighButton.click();

         // Wait for the button text to change
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(button, buttonTextBefore)));

         // Get the text of the button after clicking "Weigh"
         String buttonTextAfter = button.getText();
         System.out.println("Button Text After Weigh: " + buttonTextAfter);
         
         if (!buttonTextBefore.equals(buttonTextAfter)) {
             System.out.println("testMeasurementResults test Passed.");
         } else {
             System.out.println("testMeasurementResults test Failed.");
         }
         


	    }
	 
	 
	//c. filling out the bowls grids with bar numbers (0 to 8)
	 
	 private static void testBowlFillingConditionsDuplicates(WebDriver driver) {
		 
		// Click the "Reset" button
	        WebElement resetButton = driver.findElement(By.id("reset"));
	        resetButton.click();
		 
	        
		   // Place gold bars on the scale
	        WebElement leftBowl = driver.findElement(By.id("left_4"));
	        leftBowl.sendKeys("4");
	        
	        WebElement leftBowl1 = driver.findElement(By.id("left_5"));
	        leftBowl1.sendKeys("4");
	        
	     // Click the "Weigh" button
	        WebElement weighButton = driver.findElement(By.id("weigh"));
	        weighButton.click();

	        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	        
	     // Wait for the alert to be present
	        wait.until(ExpectedConditions.alertIsPresent());

	        // Switch to the alert
	        Alert alert = driver.switchTo().alert();

	        // Get the text of the alert
	        String alertText = alert.getText();
	        System.out.println("Alert Text: " + alertText);

	        // Accept the alert
	        alert.accept();
	        
	        // The below code will help to display output on Console whether the test case for filling bowl with number correctly passed or not
	        
	        if (alertText.contains("Left side has duplicates")) {
	             System.out.println("testBowlFillingConditionsDuplicates test Passed");
	         } else {
	             System.out.println("testBowlFillingConditionsDuplicates test Failed.");
	         }
	        
		 
	 }
	 
	 
	//c. filling out the bowls grids with bar numbers (0 to 8)
	 
	 private static void testBowlFillingConditionsSameBars(WebDriver driver) {
		// Click the "Reset" button
	        WebElement resetButton = driver.findElement(By.id("reset"));
	        resetButton.click();
		 
		   // Place gold bars on the scale
		 WebElement leftBowl = driver.findElement(By.id("left_6"));
	        leftBowl.sendKeys("6");
	        
	     WebElement rightBowl = driver.findElement(By.id("right_6"));
	        rightBowl.sendKeys("6");;
	        
	     // Click the "Weigh" button
	        WebElement weighButton = driver.findElement(By.id("weigh"));
	        weighButton.click();

	        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	        
	     // Wait for the alert to be present
	        wait.until(ExpectedConditions.alertIsPresent());

	        // Switch to the alert
	        Alert alert = driver.switchTo().alert();

	        // Get the text of the alert
	        String alertText = alert.getText();
	        System.out.println("Alert Text: " + alertText);

	        // Accept the alert
	        alert.accept();
	        
	        // The below code will help to display output on Console whether the test case for filling bowl with number correctly passed or not
	        
	        if (alertText.contains("Both sides have coin(s)")) {
	             System.out.println("testBowlFillingConditionsSameBars test Passed");
	         } else {
	             System.out.println("testBowlFillingConditionsSameBars test Failed.");
	         }
		 
	 }
	 
	 
	 
	 //e. Clicking on the gold bar number at the bottom of the website and checking for the alert message
	 private static void testGoldBarButtons(WebDriver driver) {
		 
		 // Locate the button by its ID
        // WebElement goldBarButton = driver.findElement(By.id("coin_0"));
		 
		 for (int i = 0; i < 9; i++) {
		// Locate the button by its ID
	     WebElement goldBarButton = driver.findElement(By.id("coin_" + i));
         
         // Click on the gold bar number button
         goldBarButton.click();

         // Wait for the alert to be present
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
         wait.until(ExpectedConditions.alertIsPresent());

         // Switch to the alert
         Alert alert = driver.switchTo().alert();

         // Get the text of the alert
         String alertText = alert.getText();
         System.out.println("Alert Text: " + alertText);

         // Accept the alert
         alert.accept();
         
         
	     // The below code will help to display output on Console whether the test case for bottom Gold Bar Buttons working correctly passed or not

         if (alertText.contains("Oops! Try Again!")||alertText.contains("Yay! You find it!")) {
             System.out.println("testGoldBarButtons test Passed");
         } else {
             System.out.println("testGoldBarButtons test Failed.");
         }
         
		 }
	 
		 
	 }
	 
	 
	 
}
