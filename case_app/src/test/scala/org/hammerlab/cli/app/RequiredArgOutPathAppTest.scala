package org.hammerlab.cli.app

import org.hammerlab.cli.app.Cmd.With
import org.hammerlab.cli.args.PrinterArgs
import org.hammerlab.io.Printer._

class RequiredArgOutPathAppTest
  extends MainSuite(RequiredArgOutPathTest) {

  test("run") {
    check(
      tmpPath()
    )(
      "output basename: foo\n"
    )
  }

  test("missing out path") {
    intercept[IllegalArgumentException] {
      main(tmpPath())
    }
    .getMessage should be("Expected at least two arguments (input and output paths)")
  }

  override def outBasename = "foo"
}

object RequiredArgOutPathTest
  extends Cmd.With[PrinterArgs] {
  val main = Main(
    new RequiredArgOutPathApp(_)
      with HasPrinter {
      echo(s"output basename: ${out.basename}")
    }
  )
}