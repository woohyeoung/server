package com.douzone.server.service.method;

import com.douzone.server.dto.vehicle.impl.*;
import com.douzone.server.dto.vehicle.jpainterface.*;
import com.douzone.server.entity.Vehicle;
import com.douzone.server.repository.VehicleImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class VehicleServiceMethod {

	private final VehicleImgRepository vehicleImgRepository;


	public LocalDateTime now() {
		return LocalDateTime.now();
	}

	public LocalDateTime parsing(String time) {
		return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public List<String> setPathToList(Long id) {
		return vehicleImgRepository.findPathByVehicleId(id);
	}

	public List<VehicleListResDTO> convertToList(List<IVehicleListResDTO> list) {
		List<VehicleListResDTO> vList = new ArrayList<>();
		for (IVehicleListResDTO iVehicleListResDTO : list) {
			vList.add(new VehicleListResDTO().of(iVehicleListResDTO, setPathToList(iVehicleListResDTO.getVehicle().getId())));
		}
		return vList;
	}

	public List<VehiclePagingResDTO> convertToPaging(List<IVehiclePagingResDTO> list) {
		List<VehiclePagingResDTO> vList = new ArrayList<>();
		for (IVehiclePagingResDTO iVehiclePagingResDTO : list) {
			vList.add(new VehiclePagingResDTO().of(iVehiclePagingResDTO, setPathToList(iVehiclePagingResDTO.getVId())));
		}
		return vList;
	}

	public List<VehicleResDTO> convertToVehicle(List<IVehicleResDTO> list) {
		List<VehicleResDTO> vList = new ArrayList<>();
		for (IVehicleResDTO iVehicleResDTO : list) {
			vList.add(new VehicleResDTO().of(iVehicleResDTO, setPathToList(iVehicleResDTO.getId())));
		}
		return vList;
	}

	public List<VehicleResDTO> convertToVehicleOne(List<Vehicle> list) {
		List<VehicleResDTO> vList = new ArrayList<>();
		for (Vehicle vehicle : list) {
			vList.add(new VehicleResDTO().of(vehicle, setPathToList(vehicle.getId())));
		}
		return vList;
	}

	public List<VehicleEmpResDTO> convertToEmp(List<IVehicleEmpResDTO> list) {
		List<VehicleEmpResDTO> vList = new ArrayList<>();
		for (IVehicleEmpResDTO iVehicleEmpResDTO : list) {
			vList.add(new VehicleEmpResDTO().of(iVehicleEmpResDTO, setPathToList(iVehicleEmpResDTO.getVehicle().getId())));
		}
		return vList;
	}

	public List<VehicleWeekDTO> convertToWeek(List<IVehicleWeekDTO> list) {
		List<VehicleWeekDTO> vList = new ArrayList<>();
		for (IVehicleWeekDTO iVehicleWeekDTO : list) {
			vList.add(new VehicleWeekDTO().of(iVehicleWeekDTO, setPathToList(iVehicleWeekDTO.getVehicle().getId())));
		}
		return vList;
	}

	public List<VehicleWeekTimeDTO> convertToWeekTime(List<IVehicleWeekTimeDTO> list) {
		List<VehicleWeekTimeDTO> vList = new ArrayList<>();
		for (IVehicleWeekTimeDTO iVehicleWeekTimeDTO : list) {
			vList.add(new VehicleWeekTimeDTO().of(iVehicleWeekTimeDTO, setPathToList(iVehicleWeekTimeDTO.getVId())));
		}
		return vList;
	}

	public List<VehicleDateResDTO> convertToDate(List<IVehicleDateResDTO> list) {
		List<VehicleDateResDTO> vList = new ArrayList<>();
		for (IVehicleDateResDTO iVehicleDateResDTO : list) {
			vList.add(new VehicleDateResDTO().of(iVehicleDateResDTO, setPathToList(iVehicleDateResDTO.getVehicle().getId())));
		}
		return vList;
	}

//	public void convertToTime(LocalDateTime s, LocalDateTime e) {
//		String start = s.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//		String end = e.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//
//		if (start.equals(end)) {
//
//		}
//	}
}
