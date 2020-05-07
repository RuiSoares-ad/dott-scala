package pt.dott.scala.entity

import java.time.LocalDateTime

class Order(var customerName: String,
           var contact: String,
            var shippingAddress: String,
            var total: BigDecimal,
            var orderDate: LocalDateTime,
            var items: List[Item])
