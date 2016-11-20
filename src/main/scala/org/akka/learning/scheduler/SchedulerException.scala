package org.akka.learning.scheduler

import scala.util.control.NoStackTrace

/**
  * This exception is thrown by Scheduler.schedule* when scheduling is not
  * possible, e.g. after shutting down the Scheduler.
  */
final case class SchedulerException(msg: String) extends NoStackTrace
