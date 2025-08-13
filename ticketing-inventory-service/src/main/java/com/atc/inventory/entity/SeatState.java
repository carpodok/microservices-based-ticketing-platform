package com.atc.inventory.entity;


public enum SeatState {
    /** The seat has not been locked or sold and is available for booking. */
    FREE,
    /** The seat is currently reserved by a lock operation and has not yet been sold. */
    LOCKED,
    /** The seat has been sold (committed) and is no longer available. */
    SOLD
}
