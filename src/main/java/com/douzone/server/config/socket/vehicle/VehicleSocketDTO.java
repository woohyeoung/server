package com.douzone.server.config.socket.vehicle;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleSocketDTO {
	public enum MessageType {
		ENTER, TALK, QUIT
	}

	private VehicleSocketDTO.MessageType type;
	private String roomId;
	private String sender;
	private String uid;
	private Integer isSeat;
	private String empNo;
	private String message;
}
