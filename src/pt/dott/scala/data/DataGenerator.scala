package pt.dott.scala.data

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, LocalDateTime, LocalTime, Month}

import pt.dott.scala.entity.{Customer, Item, Order}

import scala.collection.mutable.ListBuffer
import scala.util.Random

object DataGenerator {

  def generateOrders():List[Order] = {
    var allOrders = ListBuffer[Order]()
    var customersNames:List[Customer] = customerNames()
    for(i <- customersNames.indices){
      val itemsList = Items.generateRandomList()
      allOrders+=(new Order(customersNames(i).name, customersNames(i).name + "@dott.pt", getRandomAddress(), calculatOrderTotal(itemsList), getRandomDate(), Items.generateRandomList()))
    }

    allOrders.toList
  }

  def calculatOrderTotal(itemList: List[Item]) : BigDecimal = {
    var amount:BigDecimal = 0.0
    for(item <- itemList){
      amount.+(item.cost)
    }
    amount
  }

  def getRandomAddress() : String = Address.getRandomAddress

  def customerNames() : List[Customer] = CustomerName.getRandomNamesList()

  def getRandomDate(): LocalDateTime = {
    val start = LocalDate.of(2018, Month.JANUARY, 1)
    val days:Long = ChronoUnit.DAYS.between(start, LocalDate.now())
    val randomDate = start.plusDays(new Random().nextInt((days.toInt)+1))
    LocalDateTime.of(randomDate, LocalTime.now())
  }
}
