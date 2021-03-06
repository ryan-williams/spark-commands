package org.hammerlab.cli.args4j

import grizzled.slf4j.Logging
import org.bdgenomics.utils.cli.Args4j

/**
 * Interface for running a command from command line arguments.
 */
abstract class Command[T <: Args: Manifest]
  extends Serializable
    with Logging {

  /** The name of the command, as it will be specified on the command line. */
  def name: String

  /** A short description of the command, for display in the usage info on the command line. */
  def description: String

  /**
   * Run the command.
   *
   * @param args the command line arguments.
   */
  def run(args: Array[String]): Unit =
    Args4j[T](args) match {
      case Left(args) ⇒ run(args)
      case _ ⇒
    }

  def run(args: String*): Unit = run(args.toArray)

  def run(args: T): Unit

  def main(args: Array[String]): Unit =
    run(args)
}
