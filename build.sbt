name := "spark-semantic"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.2",
  "org.openrdf.sesame" % "sesame-runtime" % "2.8.1",
  "org.openrdf.sesame" % "sesame-queryrender" % "2.8.1",
  "org.openrdf.sesame" % "sesame-queryalgebra-model" %"2.8.1",
  "org.apache.jena" % "jena-core" % "2.11.0",
  "org.apache.spark" %% "spark-hive" % "1.6.2",
  "org.apache.spark" %% "spark-sql" % "1.6.2",
  "org.apache.jena" % "jena-arq" % "2.11.0",
  "net.sourceforge.owlapi" % "owlapi-distribution" % "3.4.5",
  "com.github.jsqlparser" % "jsqlparser" % "0.9",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "com.ibm.wala" % "com.ibm.wala.util" % "1.3.7",
  "com.ibm.research.hashmvn" % "hashmvn" % "1.1",
  "com.ibm.research" % "iodt.sor" % "1.0",
  "org.antlr" % "antlr" % "3.4",
  "org.antlr" % "antlr-runtime" % "3.4",
  "commons-dbutils" % "commons-dbutils" % "1.5",
  "ant-contrib" % "ant-contrib" % "1.0b3",
  "com.cedarsoftware" % "json-io" % "4.0.0",
  "org.topbraid" % "spin" % "1.4.0",
  "org.apache.hive" % "hive-jdbc" % "1.2.1"
)

resolvers += "Local Maven Repository" at "file:///"+Path.userHome+"/.m2/repository"

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

