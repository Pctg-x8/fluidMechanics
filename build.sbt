name := "mcfm1710"
version := "1.0"
organization := "com.cterm2"
scalaVersion := "2.11.7"
minecraftVersion := "1.7.10"
val forgeRevision = 1614
forgeVersion := s"10.13.4.$forgeRevision"
buildName := s"FluidMechanics-mc1710-134$forgeRevision"

scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "src" / "test"
val srcJarVersionSignature = Def.setting { Seq(minecraftVersion.value, forgeVersion.value, minecraftVersion.value).mkString("-") }
val gradleCaches = file(System.getProperty("user.home")) / ".gradle" / "caches"
val forgeSources = Def.setting { gradleCaches / "minecraft" / "net" / "minecraftforge" / "forge" / srcJarVersionSignature.value }
unmanagedJars in Compile += forgeSources.value / ("forgeSrc-" + srcJarVersionSignature.value + ".jar")
unmanagedBase in Compile := (baseDirectory in Compile).value / "libs"
libraryDependencies ++= Seq(
	"org.apache.logging.log4j" % "log4j-api" % "2.0-beta9",
	"org.apache.logging.log4j" % "log4j-core" % "2.0-beta9",
	"com.google.code.gson" % "gson" % "2.2.4",
	"org.lwjgl.lwjgl" % "lwjgl" % "2.9.1",
	"org.lwjgl.lwjgl" % "lwjgl_util" % "2.9.1",
	"org.apache.commons" % "commons-compress" % "1.8.1",
	"org.apache.commons" % "commons-lang3" % "3.3.2",
	"commons-logging" % "commons-logging" % "1.1.3",
	"com.mojang" % "authlib" % "1.5.16",
	"tv.twitch" % "twitch" % "5.16",
	"com.ibm.icu" % "icu4j-core-mojang" % "51.2",
	"io.netty" % "netty-all" % "4.0.10.Final",
	"com.paulscode" % "soundsystem" % "20120107",
	"com.paulscode" % "libraryjavasound" % "20101123",
	"com.paulscode" % "librarylwjglopenal" % "20100824",
	"com.paulscode" % "codecjorbis" % "20101023",
	"com.paulscode" % "codecwav" % "20101023",
	"java3d" % "vecmath" % "1.3.1",
	"net.minecraft" % "launchwrapper" % "1.12",
	"net.sf.trove4j" % "trove4j" % "3.0.3",
	"lzma" % "lzma" % "0.0.1",
	// "com.google.code.findbugs" % "jsr305" % "1.3.+",
	"org.scalactic" %% "scalactic" % "2.2.6",
	"org.scalatest" %% "scalatest" % "2.2.6"
)
resolvers += "minecraft" at "https://libraries.minecraft.net/"

scalacOptions ++= Seq("-deprecation", "-encoding", "UTF8", "-feature", "-language:implicitConversions")

fork := true
baseDirectory in run := (baseDirectory in Compile).value / "eclipse"
mainClass in Compile := Some("GradleStart")

/// Custom Settings ///
lazy val minecraftVersion = settingKey[String]("Target Minecraft Version")
lazy val forgeVersion = settingKey[String]("Target Forge Version")
lazy val buildName = settingKey[String]("Build Filename")
