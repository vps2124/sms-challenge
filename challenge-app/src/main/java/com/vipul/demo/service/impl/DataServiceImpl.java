package com.vipul.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vipul.demo.entity.DataEntity;
import com.vipul.demo.exceptions.DataServiceException;
import com.vipul.demo.repository.DataRepository;
import com.vipul.demo.response.ErrorMessages;
import com.vipul.demo.service.DataService;
import com.vipul.demo.shared.dto.DataDto;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	DataRepository dataRepository;

	@Override
	public DataDto createData(DataDto data) {

		ModelMapper modelMapper = new ModelMapper();
		DataEntity dataEntity = new DataEntity();
		dataEntity = modelMapper.map(data, DataEntity.class);

		DataEntity storedData = dataRepository.save(dataEntity);

		DataDto returnValue = new DataDto();
		returnValue = modelMapper.map(storedData, DataDto.class);

		return returnValue;
	}

	@Override
	public List<DataDto> getAllData() {
		List<DataDto> returnValue = new ArrayList<>();
		Iterable<DataEntity> dataEntity = dataRepository.findAll();
		ModelMapper modelMapper = new ModelMapper();
		for (DataEntity allData : dataEntity) {
			returnValue.add(modelMapper.map(allData, DataDto.class));
		}
		return returnValue;
	}

	@Override
	public DataDto updateData(long id, DataDto dataDto) {
		DataDto returnValue = new DataDto();
		// DataEntity dataEntity = dataRepository.getOne(id);

		DataEntity dataEntity = dataRepository.findById(id);
		if (dataEntity == null)
			throw new DataServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		dataEntity.setCity(dataDto.getCity());
		dataEntity.setColor(dataDto.getColor());
		dataEntity.setEnd_date(dataDto.getEnd_date());
		dataEntity.setStart_date(dataDto.getStart_date());
		dataEntity.setPrice(dataDto.getPrice());
		dataEntity.setStatus(dataDto.getStatus());

		DataEntity updatedData = dataRepository.save(dataEntity);
		ModelMapper modelMapper = new ModelMapper();
		returnValue = modelMapper.map(updatedData, DataDto.class);
		return returnValue;
	}

	@Override
	public void deleteData(long id) {
		DataEntity dataEntity = dataRepository.findById(id);
		if (dataEntity == null)
			throw new DataServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		dataRepository.delete(dataEntity);
	}

}
