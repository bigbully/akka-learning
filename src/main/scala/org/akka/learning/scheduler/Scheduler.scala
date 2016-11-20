package org.akka.learning.scheduler

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.FiniteDuration

/**
  * An Akka scheduler service. This one needs one special behavior: if
  * Closeable, it MUST execute all outstanding tasks upon .close() in order
  * to properly shutdown all dispatchers.
  *
  * Furthermore, this timer service MUST throw IllegalStateException if it
  * cannot schedule a task. Once scheduled, the task MUST be executed. If
  * executed upon close(), the task may execute before its timeout.
  *
  * Scheduler implementation are loaded reflectively at ActorSystem start-up
  * with the following constructor arguments:
  *  1) the system’s com.typesafe.config.Config (from system.settings.config)
  *  2) a akka.event.LoggingAdapter
  *  3) a java.util.concurrent.ThreadFactory
  */
trait Scheduler {

  /**
    * Schedules a function to be run repeatedly with an initial delay and a
    * frequency. E.g. if you would like the function to be run after 2 seconds
    * and thereafter every 100ms you would set delay = Duration(2, TimeUnit.SECONDS)
    * and interval = Duration(100, TimeUnit.MILLISECONDS)
    *
    * Scala API
    */
  final def schedule(
                      initialDelay: FiniteDuration,
                      interval: FiniteDuration)(f: ⇒ Unit)(
                      implicit executor: ExecutionContext): Cancellable =
    schedule(initialDelay, interval, new Runnable { override def run = f })

  /**
    * Schedules a function to be run repeatedly with an initial delay and
    * a frequency. E.g. if you would like the function to be run after 2
    * seconds and thereafter every 100ms you would set delay = Duration(2,
    * TimeUnit.SECONDS) and interval = Duration(100, TimeUnit.MILLISECONDS)
    *
    * Java API
    */
  def schedule(
                initialDelay: FiniteDuration,
                interval: FiniteDuration,
                runnable: Runnable)(implicit executor: ExecutionContext): Cancellable

  /**
    * Schedules a function to be run once with a delay, i.e. a time period that has
    * to pass before the function is run.
    *
    * Scala API
    */
  final def scheduleOnce(delay: FiniteDuration)(f: ⇒ Unit)(
    implicit executor: ExecutionContext): Cancellable =
    scheduleOnce(delay, new Runnable { override def run = f })

  /**
    * Schedules a Runnable to be run once with a delay, i.e. a time period that
    * has to pass before the runnable is executed.
    *
    * Java & Scala API
    */
  def scheduleOnce(
                    delay: FiniteDuration,
                    runnable: Runnable)(implicit executor: ExecutionContext): Cancellable

  /**
    * The maximum supported task frequency of this scheduler, i.e. the inverse
    * of the minimum time interval between executions of a recurring task, in Hz.
    */
  def maxFrequency: Double

}
