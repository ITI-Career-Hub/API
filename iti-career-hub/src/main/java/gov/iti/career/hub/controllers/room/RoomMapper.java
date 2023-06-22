package gov.iti.career.hub.controllers.room;

import gov.iti.career.hub.controllers.room.requests.AddRoomRequest;
import gov.iti.career.hub.controllers.room.requests.UpdateRoomRequest;
import gov.iti.career.hub.controllers.room.responses.GetRoomResponse;
import gov.iti.career.hub.persistence.entities.Room;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    Room toEntity(GetRoomResponse getRoomResponse);

    GetRoomResponse toGetRoomResponseDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(GetRoomResponse getRoomResponse, @MappingTarget Room room);

    Collection<GetRoomResponse> toGetRoomResponseDto(Collection<Room> room);

    Room toEntity(AddRoomRequest addRoomRequest);

    AddRoomRequest toAddRoomRequestDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(AddRoomRequest addRoomRequest, @MappingTarget Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(UpdateRoomRequest updateRoomRequest, @MappingTarget Room room);
}