
import mill._, mill.scalalib._

object deps {
  val case_app = ivy"com.github.alexarchambault::case-app:2.0.0-M3"
  val cats = ivy"org.typelevel::cats-core:1.0.1"
  val shapeless = ivy"com.chuusai::shapeless:2.3.3"

  object hammerlab {
    def lib(name: String, version: String) =
      ivy"org.hammerlab::$name:$version"

    def lib(subgroup: String, name: String, version: String) =
      ivy"org.hammerlab.$subgroup::$name:$version"

    val io = lib("io", "5.1.0")
    val paths = lib("paths", "1.5.0")

    object shapeless {
      val utils = lib("shapeless-utils", "1.3.0")
    }

    object test {
      val  base = lib("test",  "base", "1.0.1")
      val suite = lib("test", "suite", "1.0.1")
    }
  }
}

import deps._

object base extends SbtModule {
  def scalaVersion = "2.12.6"
  def ivyDeps = Agg(
    case_app,
    cats,
    shapeless,

    hammerlab.io,
    hammerlab.paths,
    hammerlab.shapeless.utils
  )

  object test extends Tests {
    def ivyDeps = Agg(
      ivy"org.scalatest::scalatest:3.0.4",
      hammerlab.test.base
    )
    def forkArgs = Seq("-Xmx4g")
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}

object spark extends SbtModule {
  def moduleDeps = Seq(base)
  def scalaVersion = "2.11.12"
}
