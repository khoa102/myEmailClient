# Start a new JavaFX Project with Maven

## Main Reference: https://openjfx.io/openjfx-docs//index.html#maven

---
## Terminal Cmd:
- Run this command inside the folder where you want your project to be. This cmd will create a folder for the project.

```cmd
mvn archetype:generate \
        -DarchetypeGroupId=org.openjfx \
        -DarchetypeArtifactId=javafx-archetype-simple \
        -DarchetypeVersion=0.0.8 \
        -DgroupId=org.khoatran \
        -DartifactId=myEmailClient \
        -Dversion=1.0.0 \
        -Djavafx-version=17
```

---
## pom.xml
- Add javafx.version to properties in pom files to keep the version of all javafx components consistent over the project.
- The other two are basic dependencies for JavaFX project

```xml
<project>
    <properties>
        <javafx.version>17</javafx.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>
    </dependencies>
</project>
```

- To run and debug project, make sure pom.xml contains the following
```xml
<project>
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.0</version>
        <configuration>
          <release>11</release>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-maven-plugin</artifactId>
        <version>0.0.8</version>
        <executions>
          <execution>
            <!-- Default configuration for running -->
            <id>default-cli</id>
            <configuration>
              <mainClass>org.khoatran.myEmailClient.App</mainClass>
            </configuration>
          </execution>
          <execution>
            <!-- Configuration for debugging -->
            <id>debug</id>
            <configuration>
              <options>
                <option>-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:8000</option>
              </options>
              <mainClass>org.khoatran.myEmailClient.App</mainClass>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

- To be able to load fxml files from src/main, add this to include src/main/java as a resource folder:
```xml
<project>
    <build>
        <resources>
          <resource>
            <filtering>false</filtering>
            <directory>src/main/Java</directory>
            <includes>
              <include>**/*.fxml</include>
            </includes>
          </resource>
        </resources>
        ...
    </build>
    ...
</project>
```
---
## Running: 
- To run a javaFX project, using javafx:run from maven plugin

---
## Debug
- Add a remote configuration that connect to localhost with port set in debug part of the pom.xml
- To debug a javaFX project:
    - Run the program using cmd: mvn clean javafx:run@debug
    - Debug the program remotely using the configuration added earlier
      - host: localhost
      - port: the port that is set in debug part of the pom.xml (e.g: port is 8000 if address=*:8000)

### Debug Reference
- https://stackoverflow.com/questions/56197372/i-cant-debug-an-application-using-netbeans-11-with-javafx-12
- https://www.jetbrains.com/help/idea/tutorial-remote-debug.html#78a65992

---
## Extra reference
- https://stackoverflow.com/questions/30488223/how-to-create-a-javafx-maven-project-in-intellij-idea
- https://edencoding.com/javafx-maven/

---
## Another way of working with javaFX
- Create a normal Maven project
- Download javaFX SDK
- Add the javaFX SDK to project as library
- Make the run and debug of the program easier but harder to manage the version of javaFX
- Have not tested this way. Will need more tests to how it works.

### Rerence
- https://openjfx.io/openjfx-docs/index.html#introduction
- https://www.jetbrains.com/help/idea/javafx.html#create-project