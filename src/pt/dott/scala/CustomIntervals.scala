package pt.dott.scala

import scala.collection.mutable.ListBuffer

class CustomIntervals(args: Array[String]) {

  var intervalsList = ListBuffer[Array[String]]()

  def validateArgs(args: Array[String])={

    for(s <- args){
      if(s.contains("-")){
        val interval = s.split("-")
        if(validIntervalLength(null, interval)){
          if(validateInterval(interval)){
            intervalsList.+=(interval)
          }
        }
      }
      var comparator = ""
      if(s.contains(">") || s.contains("<")){
        comparator = String.valueOf(s.charAt(0))
        val interval = s.split(comparator)
        if(validIntervalLength(comparator, interval)){
          if(validIntervalComparator(interval)){
            interval(0) = comparator
            intervalsList.+=(interval)
          }
        }
      }
    }
  }

  def validateInterval(interval: Array[String]): Boolean = {
    var valid = false
    for(s <- interval)
      try{
        Integer.parseInt(s)
        valid = true
      }catch{
        case e: NumberFormatException => println("Insert a valid number")
          valid = false
      }
    valid
  }

  def validIntervalComparator(interval: Array[String]): Boolean = {
    var valid = false
    try{
      Integer.parseInt(interval(1))
      valid = true
    }catch{
      case e: NumberFormatException => println("Insert a valid number")
        valid = false
    }
    valid
  }

  def validIntervalLength(comparator: String, interval: Array[String]):Boolean = interval.length==2


}
