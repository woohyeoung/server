package com.douzone.server.dto.reservation;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomWeekReservationCountDTO {

	private long roomId;
	private String content;
	private String categoryName;
	private int roomNo;
	private int capacity;
	private LocalDateTime modifiedAt;

	private long count;

	private List<ReservationResDTO> reservationResDTOList;

	@Builder
	@QueryProjection
	public RoomWeekReservationCountDTO(long roomId, String content, String categoryName, int roomNo, int capacity, LocalDateTime modifiedAt, long count) {
		this.roomId = roomId;
		this.content = content;
		this.categoryName = categoryName;
		this.roomNo = roomNo;
		this.capacity = capacity;
		this.modifiedAt = modifiedAt;
		this.count = count;
	}
}
