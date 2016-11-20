package org.akka.learning.scheduler

/**
  * Signifies something that can be cancelled
  * There is no strict guarantee that the implementation is thread-safe,
  * but it should be good practice to make it so.
  */
trait Cancellable {

  /**
    * Cancels this Cancellable and returns true if that was successful.
    * If this cancellable was (concurrently) cancelled already, then this method
    * will return false although isCancelled will return true.
    *
    * Java & Scala API
    */
  def cancel(): Boolean

  /**
    * Returns true if and only if this Cancellable has been successfully cancelled
    *
    * Java & Scala API
    */
  def isCancelled: Boolean

}
