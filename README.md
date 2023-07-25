# CucumberGradleProject
A template used for creating Automation QA projects, using the Cucumber framework and Gradle as a build tool

#Development and testing
For testing purposes these variables can be changed to any given value.
![image](https://github.com/kristiyanbstoychev/CucumberGradleProject/assets/77746043/2a2039c1-4954-4f78-8aec-26d524c3ad37)

Also by default the tests will run in headless a browser, unless debug mode is triggered. 

# To edit the project name and packajes
![image](https://github.com/kristiyanbstoychev/Automation-QA-template-gradle-project/assets/77746043/b46b54c7-ca76-458a-927f-20c476865c8a)
![image](https://github.com/kristiyanbstoychev/Automation-QA-template-gradle-project/assets/77746043/00ad855c-98f2-43f0-afeb-1547174b5361)
![image](https://github.com/kristiyanbstoychev/Automation-QA-template-gradle-project/assets/77746043/b62efe62-824b-46dc-9eac-377d42c48e04)
![image](https://github.com/kristiyanbstoychev/Automation-QA-template-gradle-project/assets/77746043/6a029da0-c13e-41f0-ae18-5ed94dad63ab)
![image](https://github.com/kristiyanbstoychev/CucumberGradleProject/assets/77746043/73ab5dbb-8f47-4c41-b58d-d4940af2a982)



# Example Automation Testing Websites For Practice purposes
  https://demoqa.com/

  https://bstackdemo.com
  
  https://www.demoblaze.com/

  # CI/CD Setup using Jenkins
To run any given Test Class from the command line

```
./gradlew -i clean test --tests 'TestSuiteRunnerCFUI' - to run jUnit tests with Gradle
```

Jenkins setup
In the Build steps section, select Execute Shell and type:
```
#!/bin/bash
source ~/.bashrc
source ~/.profile
cd path/to/the/local/repository
export URL_FOR_TESTING="url for testing"
export BROWSER_FOR_TESTING="browser" - chrome, firefox, safari or mobile
export DEVICE_FOR_TESTING="device" - any device that is listen in the browser emulation console can be used (ex. iPhone 14, Galaxy S8 etc.)
./gradlew -i clean test --tests 'TestsRunner'
```

![image](https://github.com/kristiyanbstoychev/CucumberGradleProject/assets/77746043/e6135938-ed54-4453-989b-4a69f297d11f)


In the Build Triggers section select Build periodically, to run the test on a schedule
Example: to run the Tests at 17:50 
```
50 17 * * *
```
