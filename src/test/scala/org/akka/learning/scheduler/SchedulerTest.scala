package org.akka.learning.scheduler

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

import org.akka.learning.util.SimpleThreadFactory

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  * User: bigbully
  * Date: 16/11/7
  * Time: 下午11:38
  */
object SchedulerTest extends App{

  implicit val pool = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(5))

  val scheduler = new LightArrayRevolverScheduler(new SimpleThreadFactory("test"), ticksPerWheel = 2, 10 millis, 1 second)

  scheduler.schedule(1 second, 2 seconds)(println("abc"))



//  val q = new MyTaskQueue
//  q.add("1")
//  q.add("2")
//
//  q.peek()


  new AtomicLong with Cancellable {self =>


    /**
      * Cancels this Cancellable and returns true if that was successful.
      * If this cancellable was (concurrently) cancelled already, then this method
      * will return false although isCancelled will return true.
      *
      * Java & Scala API
      */
    override def cancel(): Boolean = ???

    /**
      * Returns true if and only if this Cancellable has been successfully cancelled
      *
      * Java & Scala API
      */
    override def isCancelled: Boolean = ???
  }
}

class MyTaskQueue extends AbstractNodeQueue[String]
