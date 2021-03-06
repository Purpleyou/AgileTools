PMD 是一款源码分析工具，能够查出一些常见的代码缺陷，例如：没有被使用的变量， 空的catch块， 非必要的对象创建等等。
能够对多种语言进行支持：Java, JavaScript, Salesforce.com Apex， Visualforce, PLSQL, Apache Velocity, XML 以及 XSL。
此外，PMD还具有代码查重功能，CPD(copy-paste-detector)。此项功能适用于：Java, C, C++, C#, Groovy, PHP, Ruby, Fortran,
JavaScript, PLSQL, Apache Velocity, Scala, Objective C, Matlab, Python, Go, Swift and Salesforce.com Apex 以及 Visualforce.

PMD能与不同的开发环境相集成，提供以下插件：
* Maven PMD plugin
* Gradle: The PMD Plugin
* Eclipse plugin
* NetBeans plugin
* JBuilder plugin
* JDeveloper plugin
* IntelliJ IDEA plugin

我们选取Maven PMD plugin 进行demo. 使用要点如下：
1. Goals 说明：
* pmd:pmd, 根据规则集及配置集生成site 报告，也可以生成 xml, csv 或者 txt 格式的检查结果。
* pmd:cpd, 生成 PMD's Copy/Paste Detector (CPD) tool 查重报告，也可以生成 xml, csv 或者 txt 格式的检查结果。
* pmd:check, 检查PMD报告是否为空，如不为空则构建失败。当执行 pmd:pmd 时，本命令为默认执行。
* pmd:cpd-check 检查CPD报告是否为空，如不为空则构建失败。当执行 pmd:cpd 时，本命令为默认执行。

2. 使用默认规则集及配置只需在POM 文件中的<reporting>添加：
  ```
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-pmd-plugin</artifactId>
        <version>3.8</version>
      </plugin>
    </plugins>
  </reporting>
  ```
  也可以在<build>中显示执行：
  ```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-pmd-plugin</artifactId>
        <version>3.8</version>
      </plugin>
    </plugins>
  </build>
  ```

 3. PMD 与 CPD 报告使用同一份配置，以下为简单的示例：

  ```
  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-pmd-plugin</artifactId>
        <version>3.8</version>
        <configuration>
          <linkXref>true</linkXref>
          <sourceEncoding>utf-8</sourceEncoding>
          <minimumTokens>100</minimumTokens>
          <targetJdk>1.8</targetJdk>
          <excludes>
            <exclude>**/*Bean.java</exclude>
            <exclude>**/generated/*.java</exclude>
          </excludes>
          <excludeRoots>
            <excludeRoot>target/generated-sources/stubs</excludeRoot>
          </excludeRoots>
        </configuration>
      </plugin>
    </plugins>
  </reporting>
  ```
    其中， <minimumTokens> 属性是CPD 的配置，它用于定义被判定为重复代码段的最短长度。 这个值越小，代码重复度
    就越大。
    关于规则集：maven-pmd-plugin 默认使用以下三个规则集： java-basic, java-imports, java-unusedcode.
    如果想要检测出特定的某种问题，需要进一步配置 property rulesets.

4. PMD Maven Plugin 默认在没有violation 的时候不生成报告。可以查看pmd.xml 以及 cpd.xml 来判定是否存在violation.
  ```
  <configuration>
    <skipEmptyReport>false</skipEmptyReport>
  </configuration>
  ```
    将之设置为False 即可生成空白报告。

 更多使用详情请参见 [Apache Maven 官网](https://maven.apache.org/plugins/maven-pmd-plugin/index.html)