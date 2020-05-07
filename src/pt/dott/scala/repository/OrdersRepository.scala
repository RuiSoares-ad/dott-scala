package pt.dott.scala.repository

import java.time.LocalDateTime

import pt.dott.scala.data.DataGenerator
import pt.dott.scala.entity.{Item, Order, Product}

import scala.collection.mutable.ListBuffer

class OrdersRepository(startDate: LocalDateTime, endDate: LocalDateTime){

  val orders = DataGenerator.generateOrders()
  println("Total Orders : " +  orders.size)
  val ordersBetween = getAllOrdersBetween(orders, startDate, endDate)
  println("Orders from " + startDate.toString + " to " + endDate.toString + " : " + ordersBetween.size)


  def getAllOrdersBetween(allOrders : List[Order], startDate: LocalDateTime, endDate: LocalDateTime) : List[Order] ={
    val filteredOrders = ListBuffer[Order]()

    for(order <- allOrders){
      if(order.orderDate.isAfter(startDate) && order.orderDate.isBefore(endDate)){
        filteredOrders.+=(order)
      }
    }

    filteredOrders.toList

  }

  def getProductsByAgeInterval(filteredOrders: List[Order], intervalStart : Int, intervalEnd : Int) : List [Product] = {
    val filteredProducts = ListBuffer[Product]()
    val intervalStartDate = LocalDateTime.now().minusMonths(intervalStart)
    val intervalEndDate = LocalDateTime.now().minusMonths(intervalEnd)

    this.synchronized{
      for(order <- filteredOrders){
        for(item <- order.items){
          if(item.product.creationDate.isBefore(intervalStartDate) && item.product.creationDate.isAfter(intervalEndDate)){
            filteredProducts.+=(item.product)
          }
        }
      }
    }

    filteredProducts.toList
  }

  def getProductsOlderNewerThan(filteredOrders: List[Order], operator: String, startingPoint: Int) : List[Product] = {
    val filteredProducts = ListBuffer[Product]()
    val refDate = LocalDateTime.now().minusMonths(startingPoint)

    this.synchronized{
      for(order<- filteredOrders){
        for(item <- order.items){
          if(operator.equals(">")){
            if(item.product.creationDate.isBefore(refDate)){
              filteredProducts.+=(item.product)
            }
          } else if(operator.equals("<")){
            if(item.product.creationDate.isAfter(refDate)){
              filteredProducts.+=(item.product)
            }
          }
        }
      }
    }

    filteredProducts.toList

  }






}
