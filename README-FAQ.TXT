1) If you experience this error
est.java.SeleniumDockerGrid.openBrowser FAILED
    org.openqa.selenium.SessionNotCreatedException: session not created: This version of ChromeDriver only supports Chrome version 76

This means the browser version installed does not match with the test driver version
Check your installed browser version

google-chrome --version

You should see a result similar to this
Google Chrome 75.0.3770.100 
Accordingly change the value in the build.gradle 
Reference url https://stackoverflow.com/questions/55201226/session-not-created-this-version-of-chromedriver-only-supports-chrome-version-7

2)

