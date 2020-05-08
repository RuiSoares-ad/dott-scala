package pt.dott.scala

import pt.dott.scala.validator.ValidationUtils

import scala.collection.mutable.ListBuffer

class CustomIntervals(args: Array[String]) {

  var intervalsList = ListBuffer[Array[String]]()

  def validateArgs(args: Array[String])={
    var invalid = ListBuffer[String]()
    for(i<- 2 until args.length){
      if(args(i).contains("-")){
        val interval = args(i).split("-")
        if(validate(interval))intervalsList.+=(interval)
        else invalid.+=(args(i))
      }
      else if(args(i).contains(">") || args(i).contains("<")){
        val comparator = String.valueOf(args(i).charAt(0))
        val interval = args(i).split(comparator)
        if(validate(interval)){
          interval(0) = comparator
          intervalsList.+=(interval)
        } else {invalid.+=(args(i))}
      } else {invalid.+=(args(i))}
    }
    ValidationUtils.printInvalidIntervalParameters(invalid.toList)
  }

  def validate(interval: Array[String]): Boolean = {
    validIntervalLength(interval) && (validateInterval(interval) || validIntervalComparator(interval))
  }
  def validateInterval(interval: Array[String]): Boolean = {
    for(s <- interval)
      try{
        Integer.parseInt(s)
      }catch{
        case e: NumberFormatException => false
      }
    true
  }

  def validIntervalComparator(interval: Array[String]): Boolean = {
    try{
      Integer.parseInt(interval(1))
      true
    }catch{
      case e: NumberFormatException => false
    }

  }

  def validIntervalLength(interval: Array[String]):Boolean = interval.length==2


}
