package com.lld.BookMyShow.models;

// caveat with using @Enumerated(EnumType.ORDINAL), new values should always be added at last and not in between
public enum ShowSeatStatus {
    AVAILABLE, // ordinal 1
    BLOCKED, // oridinal 2
    BOOKED // ordinal 3
}
