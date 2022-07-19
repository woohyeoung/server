package com.douzone.server.dto.reservation;

import lombok.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("classpath:messages.properties")
@Builder
public class RegistReservationReqDto {

	@NotNull(groups = {registRes.class, updateRes.class})
	private Long roomId;
	private Long empId;
	@NotBlank(groups = {registRes.class, updateRes.class})
	private String reason;
	@NotBlank(groups = {registRes.class, updateRes.class})
	private String title;
	@NotNull(groups = {registRes.class, updateRes.class})
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime startedAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@NotNull(groups = {registRes.class, updateRes.class})
	@FutureOrPresent
	private LocalDateTime endedAt;


}
