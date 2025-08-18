package com.atc.inventory.grpc;

import com.atc.shared.SeatLockRequest;
import com.atc.inventory.entity.EventSeat;
import com.atc.inventory.service.SeatService;
import com.atc.shared.grpc.SeatLockResponse;
import com.atc.shared.grpc.SeatServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class SeatGrpcService extends SeatServiceGrpc.SeatServiceImplBase {

    private final SeatService seatService;

    @Override
    public void lockSeats(com.atc.shared.grpc.SeatLockRequest request,
                          StreamObserver<SeatLockResponse> responseObserver) {
        SeatLockRequest dto = new SeatLockRequest();
        dto.setEventId(request.getEventId());
        dto.setSeatLabels(request.getSeatLabelsList());
        dto.setLockDurationSeconds(request.getLockDurationSeconds());
        dto.setBookingRef(request.getBookingRef());
        dto.setLockedByUser(request.getLockedByUser());

        List<EventSeat> seats = seatService.lockSeats(dto);
        SeatLockResponse.Builder builder = SeatLockResponse.newBuilder();
        for (EventSeat seat : seats) {
            builder.addSeatLabels(seat.getSeatLabel());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
