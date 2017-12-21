name := "ooleproject"
version := "1.0-SNAPSHOT"

mainClass in (Compile,run) := Some("CodeGenerator.Launcher")

// Do not append Scala versions to the generated artifacts

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
    exclude("junit", "junit-dep"),
  // https://mvnrepository.com/artifact/commons-io/commons-io
  "commons-io" % "commons-io" % "2.5"

)