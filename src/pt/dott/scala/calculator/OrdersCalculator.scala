package pt.dott.scala.calculator

import java.lang.NumberFormatException

import pt.dott.scala.entity.Order
import pt.dott.scala.repository.OrdersRepository

class OrdersCalculator(lowerValue: String, higherValue:String, repository:OrdersRepository) extends Thread {

   val lValue:String = lowerValue
   val hValue: String = higherValue
   var startMonthAge: Int = 0
   var endMonthAge: Int = 0
   var resultCount: Int = 0

   override def run(): Unit = {
     val periodOrders = repository.ordersBetween
     calculateOrdersByInterval(periodOrders, lowerValue, higherValue)
   }

  def calculateOrdersByInterval(periodOrders: List[Order], lowerValue:String, higherValue:String): Int = {

    if(validateMonths(lowerValue, higherValue)){
      resultCount = getCountProductAgeGroupByOrders(periodOrders, startMonthAge, endMonthAge)
    } else if(validateMonthComparator(lowerValue, higherValue)){
      resultCount = getCountProductAgeComparatorByOrders(periodOrders, lowerValue, endMonthAge)
    }
    resultCount
  }

  def getCountProductAgeComparatorByOrders(periodOrders: List[Order], comparator:String, endMonthAge:Integer):Int = {
    repository.getProductsOlderNewerThan(periodOrders,comparator,endMonthAge).size
  }

  def getCountProductAgeGroupByOrders(periodOrders: List[Order], startMonthAge:Integer, endMonthAge:Integer):Int = {
    repository.getProductsByAgeInterval(periodOrders, startMonthAge, endMonthAge).size
  }

  def validateMonths(startMonth: String, endMonth: String): Boolean = {
    var valid = false
    try{
      startMonthAge = Integer.parseInt(startMonth)
      endMonthAge = Integer.parseInt(endMonth)
      valid = true
    }catch{
      case e: NumberFormatException =>
        valid = false
    }

    valid
  }

  def validateMonthComparator(comparator: String, referenceMonth: String): Boolean = {
    var valid:Boolean = true
    if(comparator.equals("<") || comparator.equals(">")){
      try{
        endMonthAge = Integer.parseInt(referenceMonth)
        valid = true
      }catch{
        case e: NumberFormatException => println("Insert a valid number")
          valid = false

      }
    }
    valid

  }



}
