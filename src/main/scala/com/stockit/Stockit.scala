package com.stockit

/**
 * Created by jmcconnell1 on 2/28/15.
 */
object Stockit extends App {
  var client = new yql.Client();

  var ok = true
  while (ok) {
    val ln = scala.io.StdIn.readLine()
    ok = ln != null

    if (ln == "exit") System.exit(0)

    if (ok) println(client.query(ln))
  }
}
