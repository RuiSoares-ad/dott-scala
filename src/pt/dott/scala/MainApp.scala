package pt.dott.scala

import java.time.LocalDateTime
import java.time.format.{DateTimeFormatter, DateTimeParseException}

import pt.dott.scala.calculator.OrdersCalculator
import pt.dott.scala.repository.OrdersRepository

import scala.collection.mutable.ListBuffer

object MainApp {

  val dateFormat = "yyyy-MM-dd HH:mm:ss"
  var startDate:LocalDateTime = LocalDateTime.now()
  var endDate:LocalDateTime = LocalDateTime.now()

  def main(args: Array[String]): Unit = {

    if(lessParameters(args)) return

    if(!validateDates(args)) return

    val repository = new OrdersRepository(startDate, endDate)
    calculateOrders(repository, args)


  }

  def calculateOrders(repository: OrdersRepository, args: Array[String]) ={
    val customIntervals = new CustomIntervals(args)
    customIntervals.validateArgs(args)
    if(customIntervals.intervalsList.nonEmpty){
      println("RESULT DEFAULT INTERVALS: \n")
      calculateOrdersDefaultInterval(repository)
      println("\nRESULT CUSTOM INTERVALS: \n")
      calculateOrdersCustomIntervals(repository, customIntervals.intervalsList.toList)
    }else{
      println("RESULT DEFAULT INTERVALS: \n")
      calculateOrdersDefaultInterval(repository)
    }
  }

  def calculateOrdersCustomIntervals(repository: OrdersRepository, intervals: List[Array[String]]) = {
    val calculatorList = ListBuffer[OrdersCalculator]()

    for(interval <- intervals){
      calculatorList.addOne(new OrdersCalculator(interval(0), interval(1), repository))
    }

    runThreads(calculatorList.toList)
  }

  def calculateOrdersDefaultInterval(repository: OrdersRepository)={
    val calculatorList = ListBuffer[OrdersCalculator]()

    calculatorList+=(new OrdersCalculator("1", "3", repository))
    calculatorList+=(new OrdersCalculator("4", "6", repository))
    calculatorList+=(new OrdersCalculator("7", "12", repository))
    calculatorList+=(new OrdersCalculator(">", "12", repository))

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
    var valueReturn = "-"
    try{
      Integer.parseInt(value)
    }catch{
      case e: NumberFormatException => valueReturn = ""

    }
    valueReturn
  }

  def lessParameters(args: Array[String]): Boolean ={
    var areLess = true
    if(args.length < 2){
      println("Parameters not valid. You should provide two parameters, startDate and endDate in this date format YYYY-MM-DD HH:MM:SS")
      areLess = true
    } else{
      areLess = false
    }
    areLess
  }

  def validateDates(args: Array[String]): Boolean ={
    var valid = false
    val formatter = DateTimeFormatter.ofPattern(dateFormat)

    try{
      startDate = LocalDateTime.parse(args(0), formatter)
      endDate = LocalDateTime.parse(args(1), formatter)
    }catch{
      case e: DateTimeParseException => println("Date format is invalid. Check provided dates and try again")
        valid = false
    }
    valid = true
    valid
  }


}
