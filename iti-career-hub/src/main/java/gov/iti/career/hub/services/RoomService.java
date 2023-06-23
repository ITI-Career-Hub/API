package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.room.GetAvailableRoomsRequest;
import gov.iti.career.hub.controllers.room.RoomMapper;
import gov.iti.career.hub.controllers.room.requests.AddRoomRequest;
import gov.iti.career.hub.controllers.room.requests.UpdateRoomRequest;
import gov.iti.career.hub.controllers.room.responses.GetRoomResponse;
import gov.iti.career.hub.persistence.entities.Appointment;
import gov.iti.career.hub.persistence.entities.Room;
import gov.iti.career.hub.persistence.repositories.AppointmentRepository;
import gov.iti.career.hub.persistence.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final AppointmentRepository appointmentRepository;
    private final RoomMapper roomMapper;

    public Collection<GetRoomResponse> getAllRooms(){
        return roomMapper.toGetRoomResponseDto(
                roomRepository.findAll()
        );
    }

    public Collection<GetRoomResponse> getAvailableRooms(LocalDate date){
        Collection<Appointment> appointments = appointmentRepository.findByAppointmentDate(date);
        Set<Room> occupiedRooms = appointments.stream()
                                    .map(Appointment::getRoom)
                                    .collect(Collectors.toSet());
        Set<Room> allRooms = new HashSet<>(roomRepository.findAll());
        allRooms.removeAll(occupiedRooms);
        return roomMapper.toGetRoomResponseDto(allRooms);
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
