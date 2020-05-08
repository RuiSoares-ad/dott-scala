package pt.dott.scala.validator

object ValidationUtils {

  def printInvalidDateParametersMessages()={
    println("You must provide at least 2 parameters setting an interval between two dates.")
    println("Dates should be provided on the following date format: YYYY-MM-DD HH:MM:SS")
  }

  def printInvalidIntervalParameters(invalidArgs : List[String])={
    if(invalidArgs.nonEmpty){
      System.out.println("\nInterval parameter(s) incorrect: ")
      invalidArgs.foreach((arg: String) => println(arg + " "))
      System.out.println("\nYou must provide a valid interval which includes: ")
      System.out.println("- x-y for interval between months, being x the smaller integer")
      System.out.println("- >x for interval older than x")
      System.out.println("- <x for interval newer than x\n")
    }
  }

}
