package com.example.testdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong

fun main() {

  val deferred = (1..1_000_000).map { n ->
    GlobalScope.async {
      n
    }
  }
  runBlocking {
    val sum = deferred.map {
      it.await()
          .toLong()
    }
        .sum()
    println("Sum: $sum")
  }

}