package gov.iti.career.hub.controllers.room.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Room} entity
 */
public record AddRoomRequest(String name) implements Serializable {
}