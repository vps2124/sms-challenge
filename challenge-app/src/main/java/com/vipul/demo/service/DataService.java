package com.vipul.demo.service;

import java.util.List;

import com.vipul.demo.shared.dto.DataDto;

public interface DataService {

	DataDto createData(DataDto data);

	List<DataDto> getAllData();

	DataDto updateData(long id, DataDto dataDto);

	void deleteData(long id);
}
