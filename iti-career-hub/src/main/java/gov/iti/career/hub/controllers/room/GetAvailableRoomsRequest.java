package gov.iti.career.hub.controllers.room;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetAvailableRoomsRequest(LocalDate date)
implements Serializable {}
