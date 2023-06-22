package gov.iti.career.hub.controllers.room;

import gov.iti.career.hub.controllers.room.requests.AddRoomRequest;
import gov.iti.career.hub.controllers.room.requests.UpdateRoomRequest;
import gov.iti.career.hub.controllers.room.responses.GetRoomResponse;
import gov.iti.career.hub.services.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<Collection<GetRoomResponse>> getAllRooms(){
        return ResponseEntity.ok(
                roomService.getAllRooms()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<GetRoomResponse> getAllRooms(@PathVariable Integer id){
        return ResponseEntity.ok(
                roomService.getRoom(id)
        );
    }

    @PostMapping
    public ResponseEntity<GetRoomResponse> addRoom(@Valid @RequestBody AddRoomRequest request){
        return ResponseEntity.ok(roomService.addRoom(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<GetRoomResponse> updateRoom(@PathVariable Integer id, @Valid @RequestBody UpdateRoomRequest request){
        return ResponseEntity.ok(roomService.updateRoom(request, id));
    }
}