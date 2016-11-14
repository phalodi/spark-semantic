package com.ibm.research.utils

import java.math.BigInteger
import java.util.UUID

import scala.collection.mutable.WeakHashMap

class Base36Converter {

  val lookup = new WeakHashMap[String, String]

  def prefixToBase36(prefix: String) = {
    val uuid = UUID.randomUUID()
    val base36UID=guidToBase36(uuid.toString.replace("-", ""))
    lookup+=(prefix->base36UID)
    base36UID
  }

  def getBase36FromURI(prefix:String) = {
    lookup.get(prefix)
  }

  private def guidToBase36(guid: String) = {
    val big = new BigInteger(guid, 16)
    big.toString(36)
  }

}
