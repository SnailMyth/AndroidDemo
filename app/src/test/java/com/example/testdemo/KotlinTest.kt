package com.example.testdemo

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class KotlinTest {

  @Test fun testWith() {
    //with 不是一个扩展函数 传入this  返回最后一行
    val s = "123"
    val t = with(s) {
      substring(0, 1)
      //this.substring(0, 1)
    }
    println("s=$s t=$t")
    val r = with("123456", { this.substring(2) })
    println("r=$r")
  }

  fun isToday(time: Long): Boolean {
    val todayStart = Calendar.getInstance()
    todayStart[Calendar.HOUR_OF_DAY] = 0
    todayStart[Calendar.MINUTE] = 0
    todayStart[Calendar.SECOND] = 0
    val start = todayStart.timeInMillis / 1000 //转换秒级
    val todayEnd = Calendar.getInstance()
    todayEnd[Calendar.HOUR_OF_DAY] = 23
    todayEnd[Calendar.MINUTE] = 59
    todayEnd[Calendar.SECOND] = 59
    val end = todayEnd.timeInMillis / 1000 //转换秒级
    print("""start-->$start|\n,now-->$time\n,end-->$end, """.trimMargin())
    print(secondLongToString(start,""))
    print(secondLongToString(time,""))
    print(secondLongToString(end,""))
    return time in start..end
  }

  @Test
  fun testIsToday(){
    print(isToday("1602691200".toLong()))
  }

  /**
   * @param longTime 秒
   */
  fun secondLongToString(
    longTime: Long,
    format: String?
  ): String? {
    val date = Date(longTime * 1000)
    val formatter = SimpleDateFormat("MM月dd日hh时mm分")
    return formatter.format(date)
  }

  @Test
  fun testToStr(){
    print(secondLongToString(1602691200.toLong(),""))
  }
}