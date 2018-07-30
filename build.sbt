// *****************************************************************************
// Projects
// *****************************************************************************

lazy val cassandraTest =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.quillCassandra,
        library.ficus,
        library.scalaCheck      % Test,
        library.scalaTest       % Test,
        library.circeGeneric,
        library.circeParser,
        library.circeJ8,
        library.enumeratum,
        library.enumeratumCirce,
        library.akkaHttp,
        library.akkaStream,
        library.akkaHttpTestKit % Test
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val scalaCheck    = "1.14.0"
      val scalaTest     = "3.0.5"
      val quill         = "2.5.4"
      val ficus         = "1.4.3"
      val enumeratum    = "1.5.13"
      val circe         = "0.9.3"
      val akkaHttp      = "10.1.3"
      val akkaStream    = "2.5.12"
    }
    val quillCassandra = "io.getquill" %% "quill-cassandra" % Version.quill

    val ficus = "com.iheart"       %% "ficus" % Version.ficus
    val enumeratum      = "com.beachape" %% "enumeratum"       % Version.enumeratum
    val enumeratumCirce = "com.beachape" %% "enumeratum-circe" % Version.enumeratum

    val circeGeneric = "io.circe" %% "circe-generic" % Version.circe
    val circeParser  = "io.circe" %% "circe-parser"  % Version.circe
    val circeJ8      = "io.circe" %% "circe-java8"   % Version.circe

    val akkaHttp     = "com.typesafe.akka" %% "akka-http"  % Version.akkaHttp
    val akkaStream   = "com.typesafe.akka" %% "akka-stream" % Version.akkaStream
    val akkaHttpTestKit = "com.typesafe.akka" %% "akka-http-testkit" % Version.akkaHttp

    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"  %% "scalatest"  % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    // scalaVersion from .travis.yml via sbt-travisci
    // scalaVersion := "2.12.4",
    organization := "default",
    organizationName := "lre",
    startYear := Some(2018),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-Ywarn-unused-import"
    ),
    Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value),
    Test / unmanagedSourceDirectories := Seq((Test / scalaSource).value),
    testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )
