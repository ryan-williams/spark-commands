
default(
  subgroup("cli"),
  v"1.0.0",
  sonatypeStage(
    1473,  // tests 1.0.1
    1475,  // shapeless-utils 1.3.0
    1476,  // io 5.1.0
    1477,  // spark-util:2.0.4
    1478   // magic-rdds
  ),
  versions(
       hammerlab.io → "5.1.0",
              paths → "1.5.0",
    shapeless_utils → "1.3.0",
         spark_util → "2.0.4"
  )
)

lazy val base = project.settings(
  dep(
    case_app,
    io_utils,
    paths,
    shapeless,
    shapeless_utils
  ),
  publishTestJar  // `MainSuite` is useful in downstream libraries' tests
)

lazy val spark = project.settings(
  `2.11` only,
  dep(
    case_app,
    paths,
    slf4j,
    spark_util
  ),
  testDeps ++= Seq(
    cats,
    magic_rdds % "4.2.1"
  ),
  addSparkDeps,
  publishTestJar  // `MainSuite` is useful in downstream libraries' tests
).dependsOn(
  base andTest
)

lazy val `cli-root` =
  root(
    base,
    spark
  )

github.repo("spark-commands")
