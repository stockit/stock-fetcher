package com.stockit.yql

import java.net.{URI, URL, URLEncoder}

import org.apache.commons.httpclient.util.URIUtil


/**
 * Created by jmcconnell1 on 3/1/15.
 */
class Client {
  def query(statement: String) = {

    val baseUrl = "http://query.yahooapis.com/v1/public/yql?q="
    val fullUrlStr = baseUrl + URIUtil.encodeQuery(statement + "&format=json", "UTF-8")

    val fullUrl = new URL(fullUrlStr)
    println(fullUrl)

    val is = fullUrl.openStream()
    val stream = scala.io.Source.fromInputStream(is).mkString
    is.close()

    stream
  }
}
