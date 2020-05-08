package pt.dott.scala

import java.time.LocalDateTime
import java.time.format.{DateTimeFormatter, DateTimeParseException}

import pt.dott.scala.calculator.OrdersCalculator
import pt.dott.scala.repository.OrdersRepository
import pt.dott.scala.validator.ValidationUtils

import scala.collection.mutable.ListBuffer

object MainApp {

  val dateFormat = "yyyy-MM-dd HH:mm:ss"
  var startDate:LocalDateTime = LocalDateTime.now()
  var endDate:LocalDateTime = LocalDateTime.now()

  def main(args: Array[String]): Unit = {

    if(lessParameters(args) || !validateDates(args)){
      ValidationUtils.printInvalidDateParametersMessages()
      return
    }


    val repository = new OrdersRepository(startDate, endDate)
    calculateOrders(repository, args)

  }

  def calculateOrders(repository: OrdersRepository, args: Array[String]) ={
    val customIntervals = new CustomIntervals(args)
    customIntervals.validateArgs(args)
    println("RESULT DEFAULT INTERVALS: \n")
    calculateOrdersDefaultInterval(repository)
    if(customIntervals.intervalsList.nonEmpty){
      println("\nRESULT CUSTOM INTERVALS: \n")
      calculateOrdersCustomIntervals(repository, customIntervals.intervalsList.toList)
    }
  }

  def calculateOrdersCustomIntervals(repository: OrdersRepository, intervals: List[Array[String]]) = {
    val calculatorList = ListBuffer[OrdersCalculator]()

    for(interval <- intervals){
      calculatorList.+=(new OrdersCalculator(interval(0), interval(1), repository))
    }

    runThreads(calculatorList.toList)
  }

  def calculateOrdersDefaultInterval(repository: OrdersRepository)={
    val calculatorList = ListBuffer[OrdersCalculator]()

    calculatorList.+=(new OrdersCalculator("1", "3", repository))
    calculatorList.+=(new OrdersCalculator("4", "6", repository))
    calculatorList.+=(new OrdersCalculator("7", "12", repository))
    calculatorList.+=(new OrdersCalculator(">", "12", repository))

    runThreads(calculatorList.toList)
  }

  def runThreads(calculatorList:List[OrdersCalculator])={
    for(calc <- calculatorList){
      calc.start()
    }

    for(calc <- calculatorList){
      try{
        calc.join()
      }catch{
        case e : InterruptedException => println(calc.getName + "has been interrupted")
      }
    }

    for(calc <- calculatorList){
      println(calc.lValue + checkIntegerValue(calc.lValue) + calc.hValue + " months : " + calc.resultCount + " orders" )
    }

  }

  def checkIntegerValue(value:String):String ={
    try{
      Integer.parseInt(value)
      "-"
    }catch{
      case e: NumberFormatException => ""
    }
  }

  def lessParameters(args: Array[String]): Boolean ={
    args.length<2
  }

  def validateDates(args: Array[String]): Boolean ={
    val formatter = DateTimeFormatter.ofPattern(dateFormat)
    try{
      startDate = LocalDateTime.parse(args(0), formatter)
      endDate = LocalDateTime.parse(args(1), formatter)
      true
    }catch{
      case e: DateTimeParseException => false
    }

  }


}
