package pt.dott.scala.entity

import java.time.LocalDateTime

class Product(var name: String,
              var category: String,
              var weight: BigDecimal,
              var price: BigDecimal,
              var creationDate: LocalDateTime)
