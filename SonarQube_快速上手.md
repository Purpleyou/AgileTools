# SonarQube_快速上手
SonarQube 是一款代码质量管理平台，支持包括java,C#,C/C++,JavaScrip,Groovy等二十几种编程语言的检测，可以从七个维度检测代码：复杂度分别、代码重复程度、单元测试覆盖率、与PMD,CheckStyle等工具结合检测代码编写规范、注释覆盖率、潜在的bug、糟糕的设计。


快速上手使用要点如下：

* [下载](https://www.sonarqube.org/downloads/) 安装SonarQube Server
* 解压SonarQube并运行 ./sonarqube/bin/[OS]/sonar.sh console
* 访问 localhost:9000， 登录。 用户名：admin, 密码：admin
* 根据网页指引进行操作
* 打开conf\sonar.properties文件，配置：
	* sonar.login=admin
	* sonar.password=admin 
  以及其他相关数据库配置， 这里使用内嵌数据库，暂不做其他配置
* 进入要分析的项目目录，运行 mvn sonar:sonar 构建成功后，再次访问localhost:9000 即可查看分析结果

