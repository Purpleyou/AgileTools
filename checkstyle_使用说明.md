Checkstyle 是一个自动化检查java代码编码规范的工具，可以检查类的设计，方法的设计， 代码的布局，格式等等问题。
同时，它具有很高的可配置性，可以自定义不同的编码标准。当Checkstyle被集成到构建过程中或者开发环境中才能发挥出最
好的效用。它可以以插件的形式与许多工具进行集成， 比如： IntelliJ IDEA, Eclipse, Jenkins，SonarQube, Maven等等。
这里，我们以Maven checkstyle plugin 为例进行demo.

我们可以直接在工程的POM文件中配置checkstyle plugin。使用要点如下：

1. maven checkstyle plugin 中已经预置了sun_checks.xml和google_checks.xml 两个规则集，默认使用sun_checks.xml.
    我们可以使用：
        <configuration>
            <configLocation>google_checks.xml</configLocation>
        </configuration>
    进行配置，<configLocation> 中还可以指定自定义的规则集。


2. 生成Checkstyle项目报告。
    在POM文件的<reporting> 部分添加checkstyle plugin.
    执行 mvn site 命令，查看位于target--> site --> checkstyle.html 的报告。

    <reporting>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>2.17</version>
                <configuration>
                    <configLocation>google_checks.xml</configLocation>
                </configuration>
            </plugin>
        </plugins>
    </reporting>

3. 如果想要在控制台输出报告或者当代码不符合编码规范时让构建失败，则需要POM文件的<build>元素中进行相应配置。
	<build>
		<plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>2.17</version>
                <executions>
                    <execution>
                        <id>validate</id>
                        <phase>validate</phase>
                        <configuration>
                            <configLocation>google_checks.xml</configLocation>
                            <encoding>UTF-8</encoding>
                            <consoleOutput>true</consoleOutput>
                            <failsOnError>true</failsOnError>
                        </configuration>
                        <goals>
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
		</plugins>
	</build>

	<goal> 说明：
	a. checkstyle:help, 不生成报告。查看帮助。
	b. checkstyle:check, 不生成报告。检查工程是否满足编码规范，不满足则构建失败，输出违例信息或次数到控制台，
	    可以通过target--> checkstyle-result.xml 查看检查信息。
	c. checkstyle:checkstyle, 生成报告。不满足编码规范不会导致构建失败。通过target-->site --> checkstyle.html查看检查信息。
	d. checkstyle:checkstyle-aggregate, 生成报告，检查多模块的项目是否满足编码规范，不满足编码规范不会导致构建失败。
	    通过target-->site --> checkstyle.html查看检查信息。

4. 直接执行mvn checkstyle:checkstyle 命令也可以生成checkstyle的单独报告， "mvn + <goal>"。

5. 在<build>中使用内联的checker 配置，详情见pom1.xml 文件。

更多的使用细节请参加Apache Maven 官网： http://maven.apache.org/plugins/maven-checkstyle-plugin/

************************************************************************************************
checkstyle 生成的html 报告中只会显示不符合要求的代码在哪一行，并不能直接查看代码，为解决这一问题， 我们引入:
maven-jxr-plugin:
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jxr-plugin</artifactId>
        <version>2.5</version>
    </plugin>

