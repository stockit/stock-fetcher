package com.stockit

/**
 * Created by jmcconnell1 on 2/28/15.
 */
object Stockit extends App {
  var client = yql.StockClient

  var ok = true
  println("Command format: symbol1,symbol2,...,startDate,endDate")
  println("Example: YHOO,2009-10-14,2009-11-14")
  while (ok) {
    val ln = scala.io.StdIn.readLine()
    ok = ln != null

    if (ln == "exit") System.exit(0)
    if (ok) {
      val args = ln.split(",")
      val symbols = args.dropRight(2)
      val startDate = args(args.length - 2)
      val endDate = args(args.length - 1)
      val auditor = (A: String) => { println("Using Query: ", A) }
      println(client.priceDataBatch(symbols.toList, startDate, endDate, auditor))
    }
  }
}
