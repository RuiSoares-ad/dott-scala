package pt.dott.scala.data

import java.util.Random

object Address {
  private val addressList: List[String] = List("Lisboa", "Porto", "Aveiro", "Beja", "Braga", "Bragança",
    "Castelo Branco", "Coimbra", "Évora", "Faro", "Funchal", "Guarda", "Leiria", "Ponta Delgada", "Portalegre",
    "Santarém", "Setúbal", "Viana do Castelo", "Vila Real", "Viseu")

  def getRandomAddress: String = addressList(new Random().nextInt(addressList.size))



}
