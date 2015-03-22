package com.stockit
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets
/**
 * Created by jmcconnell1 on 2/28/15.
 */
object Stockit extends App {
  var client = yql.StockClient

  if (args.length > 0) {
    runQuery(args(0))
  } else {
    println("Command format: symbol1,symbol2,...,startDate,endDate,outdir")
    println("Example: YHOO,2009-10-14,2009-11-14,data")

    var ok = true

    while (ok) {
      val ln = scala.io.StdIn.readLine()
      ok = ln != null

      if (ok) {
        runQuery(ln)
      }
    }
  }

  def runQuery(ln: String) = {
    val args = ln.split(",")
    val symbols = args.dropRight(3)
    val startDate = args(args.length - 3)
    val endDate = args(args.length - 2)
    val outDir = args(args.length - 1)
    val auditor = (A: String) => { println("Using Query: ", A) }
    for (symbol <- symbols) {
      val result = client.priceData(symbol, startDate, endDate, auditor)
      saveToFile(result, outDir, symbol, startDate, endDate)
      println(result)
    }
  }

  def saveToFile(message: String, outDir: String, ticket: String, startDate: String, endDate: String) = {
    val formattedFileName = String.format("%s_%s_%s.json", ticket.toLowerCase, startDate, endDate)
    Files.write(Paths.get(outDir, formattedFileName), message.getBytes(StandardCharsets.UTF_8))
  }
}
