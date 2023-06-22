package gov.iti.career.hub.controllers.room.responses;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Room} entity
 */
public record GetRoomResponse(Integer id, String name) implements Serializable {
}