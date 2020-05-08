package pt.dott.scala.repository

import java.time.LocalDateTime

import pt.dott.scala.data.DataGenerator
import pt.dott.scala.entity.Order

class OrdersRepository(startDate: LocalDateTime, endDate: LocalDateTime){

  val orders = DataGenerator.generateOrders()
  println("Total Orders : " +  orders.size)
  val ordersBetween = getAllOrdersBetween(orders, startDate, endDate)
  println("Orders from " + startDate.toString + " to " + endDate.toString + " : " + ordersBetween.size)


  def getAllOrdersBetween(allOrders : List[Order], startDate: LocalDateTime, endDate: LocalDateTime) : List[Order] ={
    allOrders.toStream.filter(order => order.orderDate.isAfter(startDate) && order.orderDate.isBefore(endDate)).toList
  }

  def getProductsByAgeInterval(filteredOrders: List[Order], intervalStart : Int, intervalEnd : Int) : List [Order] = {
    var resultOrders = List[Order]()
    val intervalStartDate = LocalDateTime.now().minusMonths(intervalStart)
    val intervalEndDate = LocalDateTime.now().minusMonths(intervalEnd)

    this.synchronized{
      resultOrders = filteredOrders.toStream.filter(order => order.items.toStream.
        exists(item => item.product.creationDate.isBefore(intervalStartDate)&&item.product.creationDate.isAfter(intervalEndDate) )).toList
    }

    resultOrders
  }

  def getProductsOlderNewerThan(filteredOrders: List[Order], operator: String, startingPoint: Int) : List[Order] = {
    var resultOrders = List[Order]()
    val refDate = LocalDateTime.now().minusMonths(startingPoint)

    this.synchronized{
      if(operator.equals(">")){
        resultOrders = filteredOrders.toStream.filter(order => order.items.toStream.
          exists(item => item.product.creationDate.isBefore(refDate))).toList
      } else if(operator.equals("<")){
        resultOrders = filteredOrders.toStream.filter(order => order.items.toStream.
          exists(item => item.product.creationDate.isAfter(refDate))).toList
      }
    }

    resultOrders

  }






}
