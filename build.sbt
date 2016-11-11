name := "spark-semantic"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
                      "org.apache.spark" %% "spark-core" % "1.6.2"
                    )


ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

