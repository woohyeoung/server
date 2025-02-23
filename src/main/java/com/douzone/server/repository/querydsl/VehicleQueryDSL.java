package com.douzone.server.repository.querydsl;

import com.douzone.server.dto.room.RoomReservationSearchDTO;
import com.douzone.server.dto.vehicle.QVehicleVariousDTO;
import com.douzone.server.dto.vehicle.VehicleSearchDTO;
import com.douzone.server.dto.vehicle.VehicleVariousDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.douzone.server.entity.QEmployee.employee;
import static com.douzone.server.entity.QRoomReservation.roomReservation;
import static com.douzone.server.entity.QVehicle.vehicle;
import static com.douzone.server.entity.QVehicleReservation.vehicleReservation;

@Repository
@RequiredArgsConstructor
public class VehicleQueryDSL {
	private final JPAQueryFactory jpaQueryFactory;

	private BooleanExpression vehicleIdEq(Long vehicleId) {
		return vehicleId != null ? vehicleReservation.vehicle.id.eq(vehicleId) : null;
	}

	private BooleanExpression capacityEq(Integer capacity) {
		return capacity != null ? vehicleReservation.vehicle.capacity.eq(capacity) : null;
	}

	private BooleanExpression teamIdEq(Long teamId) {
		return teamId != null ? vehicleReservation.employee.team.id.eq(teamId) : null;
	}

	private BooleanExpression positionIdEq(Long positionId) {
		return positionId != null ? vehicleReservation.employee.position.id.eq(positionId) : null;
	}

	private BooleanExpression empNoEq(String empNo) {
		return empNo != null ? vehicleReservation.employee.empNo.eq(empNo) : null;
	}

	private BooleanExpression startAtEq(String startedAt) {
		if (startedAt != null) {
			LocalDateTime test = LocalDateTime.parse(startedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			return vehicleReservation.startedAt.after(test);
		} else return null;
	}

	private BooleanExpression endedAtEq(String endedAt) {
		if (endedAt != null) {
			return vehicleReservation.endedAt.before(LocalDateTime.parse(endedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		} else return null;
	}


	public List<VehicleVariousDTO> selectByVariousColumns(VehicleSearchDTO search) {
		return jpaQueryFactory
				.select(new QVehicleVariousDTO(
						vehicleReservation.id,
						vehicle.id.as("vId"),
						vehicleReservation.reason,
						vehicleReservation.title,
						vehicle.name.as("vName"),
						vehicle.number.as("vNumber"),
						vehicle.model,
						vehicle.color,
						employee.empNo,
						employee.name.as("eName"),
						employee.email,
						employee.tel,
						employee.profileImg,
						employee.team.name.as("team"),
						employee.position.name.as("position"),
						vehicleReservation.startedAt,
						vehicleReservation.endedAt,
						vehicleReservation.createdAt,
						vehicleReservation.modifiedAt,
						employee.birthday,
						vehicle.capacity
				))
				.from(vehicleReservation)
				.leftJoin(vehicleReservation.vehicle, vehicle)
				.leftJoin(vehicleReservation.employee, employee)
				.where(
						vehicleIdEq(search.getVehicleId()),
						capacityEq(search.getCapacity()),
						positionIdEq(search.getPositionId()),
						teamIdEq(search.getTeamId()),
						empNoEq(search.getEmpNo()),
						startAtEq(search.getStartedAt()),
						endedAtEq(search.getEndedAt())
				)
				.orderBy(vehicleReservation.modifiedAt.desc())
				.fetch();
	}
	public List<VehicleVariousDTO> selectByVariousColumns(VehicleSearchDTO search,long page) {
		return jpaQueryFactory
				.select(new QVehicleVariousDTO(
						vehicleReservation.id,
						vehicle.id.as("vId"),
						vehicleReservation.reason,
						vehicleReservation.title,
						vehicle.name.as("vName"),
						vehicle.number.as("vNumber"),
						vehicle.model,
						vehicle.color,
						employee.empNo,
						employee.name.as("eName"),
						employee.email,
						employee.tel,
						employee.profileImg,
						employee.team.name.as("team"),
						employee.position.name.as("position"),
						vehicleReservation.startedAt,
						vehicleReservation.endedAt,
						vehicleReservation.createdAt,
						vehicleReservation.modifiedAt,
						employee.birthday,
						vehicle.capacity
				))
				.from(vehicleReservation)
				.leftJoin(vehicleReservation.vehicle, vehicle)
				.leftJoin(vehicleReservation.employee, employee)
				.where(
						vehicleIdEq(search.getVehicleId()),
						capacityEq(search.getCapacity()),
						positionIdEq(search.getPositionId()),
						teamIdEq(search.getTeamId()),
						empNoEq(search.getEmpNo()),
						startAtEq(search.getStartedAt()),
						endedAtEq(search.getEndedAt())
				)
				.orderBy(vehicleReservation.modifiedAt.desc())
				.limit(10)
				.offset((page-1)*10)
				.fetch();
	}
//	이것의 카운트
	public long countReservation(VehicleSearchDTO search) {
		return jpaQueryFactory.select(vehicleReservation).from(vehicleReservation)
				.where(
						vehicleIdEq(search.getVehicleId()),
						capacityEq(search.getCapacity()),
						positionIdEq(search.getPositionId()),
						teamIdEq(search.getTeamId()),
						empNoEq(search.getEmpNo()),
						startAtEq(search.getStartedAt()),
						endedAtEq(search.getEndedAt())
				)
				.stream().count();
	}
}
