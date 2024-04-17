# Project Name: Gold Bar Identification Automation

# Description:
This project aims to automate the identification of a fake gold bar among a set of genuine bars using a Selenium-based automation framework. The algorithm utilizes a binary search approach to efficiently narrow down the search range and identify the counterfeit gold bar.


# Framework:
The automation framework is built using Java and Selenium WebDriver. It interacts with a web application that simulates the weighing process of gold bars.
* Components:
    * Main Algorithm: The core algorithm employs binary search to systematically compare the weights of subsets of gold bars until the fake bar is found.
    * Selenium Automation: Selenium WebDriver is used to automate interactions with the web application, including filling the bowls, clicking buttons, and capturing measurement results.
    * Weighing display Challenge: An additional function has been implemented to display the weighing list output on the console, providing visibility into the history of weighing operations.
    * Test Cases: Various test cases have been developed to validate the functionality of the web application, including button functionality, measurement accuracy, bowl filling conditions, and bottom gold bar interactions.


![Image](TestCasesScreenshots/SeleniumControlWebsite.png?raw=true "SeleniumControlWebsite.png")


# Usage:
To run the automation script:
Set up the Selenium WebDriver and ensure the correct browser driver is configured.
Execute the Test.java file, which contains the main automation logic.
Uncomment relevant sections in the code to run specific test cases, if required.

# Algorithm:
The binary search algorithm is employed to efficiently identify the fake gold bar. It systematically divides the search range in half with each iteration, reducing the search space until the counterfeit bar is found.

# Test Cases:
* Test case 1: Verify the functionality of the "Weigh" button and obtain a list of weighing operations.
* Test case 2: Test the functionality of the "Reset" button to clear the bowls.
* Test case 3: Validate the accuracy of measurement results displayed between the bowls.
* Test case 4: Check the conditions for filling the bowls with gold bar numbers.
* Test case 5: Verify the functionality of the bottom gold bar buttons and check for alert messages.

  
# Attachments:
Please see the attached algorithm document for detailed information on the search algorithm used.
Output screenshots are available in the attached file, showcasing the results and interactions during the automation process.

# Contributors:
Anshita Verma

# Note:
Ensure proper setup and configuration of the Selenium WebDriver and browser drivers before running the automation script.





