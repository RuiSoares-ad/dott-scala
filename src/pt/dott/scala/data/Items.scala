package pt.dott.scala.data

import java.math.BigDecimal

import pt.dott.scala.entity.Item

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Items {


  def generateRandomList():List[Item] = {
    val dummyItems = ListBuffer[Item]()
    val listSize = new Random().nextInt(5)
    val productList = Products.getProductList()

    for( i <- 1 to listSize){
      val productToAdd = productList(new Random().nextInt(productList.size-1))
      dummyItems.+=(new Item(productToAdd.price, BigDecimal.valueOf(2.00), BigDecimal.valueOf(4.00), productToAdd))
    }
    dummyItems.toList
  }

}
