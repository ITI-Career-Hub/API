package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.room.RoomMapper;
import gov.iti.career.hub.controllers.room.requests.AddRoomRequest;
import gov.iti.career.hub.controllers.room.requests.UpdateRoomRequest;
import gov.iti.career.hub.controllers.room.responses.GetRoomResponse;
import gov.iti.career.hub.persistence.entities.Room;
import gov.iti.career.hub.persistence.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public Collection<GetRoomResponse> getAllRooms(){
        return roomMapper.toGetRoomResponseDto(
                roomRepository.findAll()
        );
    }

    public GetRoomResponse getRoom(Integer id){
        return roomMapper.toGetRoomResponseDto(
                roomRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Not Found"))
        );
    }

    public GetRoomResponse addRoom(AddRoomRequest request){
        return roomMapper.toGetRoomResponseDto(
                roomRepository.save(
                        roomMapper.toEntity(request)
                )
        );
    }

    public GetRoomResponse updateRoom(UpdateRoomRequest request, Integer id){
        Room room = roomRepository.findById(id).orElseThrow(
                () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Not Found")
        );
        return roomMapper.toGetRoomResponseDto(
                roomRepository.save(
                        roomMapper.partialUpdate(request, room)
                )
        );
    }


}
