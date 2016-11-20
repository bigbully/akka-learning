package org.akka.learning.util

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

/**
  * User: bigbully
  * Date: 16/11/7
  * Time: 下午11:39
  */
class SimpleThreadFactory(namePrefix:String) extends ThreadFactory{

  private val threadIndex: AtomicInteger = new AtomicInteger(0)

  override def newThread(r: Runnable): Thread = new Thread(r, s"${namePrefix}_${this.threadIndex.incrementAndGet}")
}
