# TeamCity UI Automation test task
There've been automated following E2E scenarios which are supposed to be good candidates for smoke test regression supposed
to be executed on regular basis:
- **Basic setup workflow** (a scenario with embedded database);
- **General build workflow** (a scenario triggering build with result verification);
- **User management** (creating a user with default access rights verification);

## Technical implementation details
The used tech stack is **Kotlin**, **Gradle**, **Selenide** (selenium wrapper lib), **Selenoid** (test containers runner),
**Docker** itself and **Allure** test report tool.

Every test supposed to be run against one of the two types of test environment: A "clean" or not yet installed Teamcity's distributive and
already pre-installed and pre-configured system having snapshot of some data to be used in test.
Both environments test execution is managed by docker containers, gradle tasks and categories annotation.

### Limitations of the solution
- No support of parallel test execution
- No support of multi-platform (themes) locators

## How to run tests

### Prerequisites
**Should be installed/presented on test machine**:
- Docker with docker-compose tool. The solution is tested on docker engine with limit resources for this project is 4GB of RAM with 2GB Swap and 4 cores of CPU.
- Gradle
- Java version >= 1.8
- Cloned repository with project code

**Note:** Steps 1 and 2 are optional if you already have selenium hub up and running, in such case just specify the hub url in **config.properties** file. 

#### 1. Run the following command:
```
docker pull selenoid/chrome
```

#### 2. From ${PROJECT_HOME}/env directory run the following command:
```
docker-compose -f docker-compose.selenoid.yml up
```
Once it finished, selenoid server with selenoid-ui tools should be up and running with attached chrome browser instance. To verify this check that selenoid url http://selenoid:4444/wd/hub 
or http://localhost:4444/wd/hub (depends from host OS) is accessible and chrome browser is attached. Make sure that available host is specified in **config.properties** file as hub_url.

#### 3. For MacOS users only
MacOS has some network limitations applicable to docker containers. Particularly it doesn't allow to resolve localhost address from  containers inside.
Use special docker DNS host.docker.internal as a workaround in such cases. See https://docs.docker.com/docker-for-mac/networking/ for more details.
Make sure that base_url property in **config.properties** file has the following value:
```
base_url=http://host.docker.internal:8111
```
#### 4. Run the tests
From ${PROJECT_HOME} directory run the following command:
```
gradle clean testAll
```

There is possible to view the test progress in "live" mode via **Selenoid UI** - http://localhost:8080:
![live-selenoid](images/selenoid-live-view.gif?raw=true "Live test progress view in Selenoid") 

#### 5. Get the test report
From ${PROJECT_HOME} directory run the following command:
```
gradle allureServ
```
Allure test report will be opened in browser. There is available Selenide steps log for each test. 
Screenshots are presented only for failed tests.
![allure-report-example](images/allure-report-example.png?raw=true "Allure report example") 







