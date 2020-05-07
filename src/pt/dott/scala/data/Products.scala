package pt.dott.scala.data

import java.math.BigDecimal
import java.time.{LocalDate, LocalDateTime, LocalTime, Month}

import pt.dott.scala.entity.Product

import scala.collection.mutable.ListBuffer

object Products {

  def getProductList():List[Product] = {
    val productList = ListBuffer[Product]()
    //products 1-3 months old
    val product1 = new Product("salt", "food", 1.0, BigDecimal.valueOf(0.55), LocalDateTime.of(LocalDate.of(2020, Month.APRIL, 2),LocalTime.of(0, 0, 0)))
    productList.+=(product1)
    val product2 = new Product("rice", "food", 1.0, BigDecimal.valueOf(0.75), LocalDateTime.of(LocalDate.of(2020, Month.APRIL, 1), LocalTime.of(0, 0, 0)))
    productList.+=(product2)
    val product3 = new Product("milk", "food", 1.0, BigDecimal.valueOf(0.5), LocalDateTime.of(LocalDate.of(2020, Month.MARCH, 15), LocalTime.of(0, 0, 0)))
    productList.+=(product3)
    val product4 = new Product("spaghetti", "food", 2.0, BigDecimal.valueOf(1.55), LocalDateTime.of(LocalDate.of(2020, Month.FEBRUARY, 21), LocalTime.of(0, 0, 0)))
    productList.+=(product4)
    val product5 = new Product("sugar", "food", 1.0, BigDecimal.valueOf(0.80), LocalDateTime.of(LocalDate.of(2020, Month.MARCH, 1), LocalTime.of(0, 0, 0)))
    productList.+=(product5)

    //products 4-7 months old
    val product6 = new Product("book", "library", 0.75, BigDecimal.valueOf(16.5), LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 4), LocalTime.of(0, 0, 0)))
    productList.+=(product6)
    val product7 = new Product("chair", "furniture", 2.2, BigDecimal.valueOf(20.00), LocalDateTime.of(LocalDate.of(2019, Month.DECEMBER, 25), LocalTime.of(0, 0, 0)))
    productList.+=(product7)
    val product8 = new Product("mobile charger", "techonology", 0.2, BigDecimal.valueOf(10.90), LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 20), LocalTime.of(0, 0, 0)))
    productList.+=(product8)
    val product9 = new Product("pillow", "home", 0.150, BigDecimal.valueOf(5.90), LocalDateTime.of(LocalDate.of(2020, Month.FEBRUARY, 1), LocalTime.of(0, 0, 0)))
    productList.+=(product9)
    val product10 = new Product("mousepad", "technology", 0.265, BigDecimal.valueOf(0.55), LocalDateTime.of(LocalDate.of(2019, Month.NOVEMBER, 10), LocalTime.of(0, 0, 0)))
    productList.+=(product10)

    //products 7-12 months old
    val product11 = new Product("laptop charger", "technology", 0.340, BigDecimal.valueOf(30.90), LocalDateTime.of(LocalDate.of(2019, Month.SEPTEMBER, 5), LocalTime.of(0, 0, 0)))
    productList.+=(product11)
    val product12 = new Product("usb cable", "technology", 0.08, BigDecimal.valueOf(4.50), LocalDateTime.of(LocalDate.of(2019, Month.OCTOBER, 1), LocalTime.of(0, 0, 0)))
    productList.+=(product12)
    val product13 = new Product("plate", "home", 0.200, BigDecimal.valueOf(3.5), LocalDateTime.of(LocalDate.of(2019, Month.AUGUST, 20), LocalTime.of(0, 0, 0)))
    productList.+=(product13)
    val product14 = new Product("headphones", "technology", 0.06, BigDecimal.valueOf(8.50), LocalDateTime.of(LocalDate.of(2019, Month.JULY, 14), LocalTime.of(0, 0, 0)))
    productList.+=(product14)
    val product15 = new Product("coat", "clothes", 0.400, BigDecimal.valueOf(25.99), LocalDateTime.of(LocalDate.of(2019, Month.JUNE, 2), LocalTime.of(0, 0, 0)))
    productList.+=(product15)

    //producs >12 months old
    val product16 = new Product("remote", "technology", 0.1, BigDecimal.valueOf(9.90), LocalDateTime.of(LocalDate.of(2019, Month.APRIL, 5), LocalTime.of(0, 0, 0)))
    productList.+=(product16)
    val product17 = new Product("LG TV", "technology", 8.0, BigDecimal.valueOf(229.00), LocalDateTime.of(LocalDate.of(2019, Month.MARCH, 25), LocalTime.of(0, 0, 0)))
    productList.+=(product17)
    val product18 = new Product("notepad", "office", 0.09, BigDecimal.valueOf(6.90), LocalDateTime.of(LocalDate.of(2018, Month.DECEMBER, 11), LocalTime.of(0, 0, 0)))
    productList.+=(product18)
    val product19 = new Product("pen", "office", 0.01, BigDecimal.valueOf(1.00), LocalDateTime.of(LocalDate.of(2018, Month.SEPTEMBER, 1), LocalTime.of(0, 0, 0)))
    productList.+=(product19)
    val product20 = new Product("mug", "home", 0.150, BigDecimal.valueOf(10.00), LocalDateTime.of(LocalDate.of(2018, Month.MAY, 28), LocalTime.of(0, 0, 0)))
    productList.+=(product20)
    productList.toList
  }

}
