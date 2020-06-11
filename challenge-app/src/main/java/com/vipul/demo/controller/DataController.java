package com.vipul.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vipul.demo.request.DataRequestModel;
import com.vipul.demo.response.DataResponseModel;
import com.vipul.demo.response.OperationStatusModel;
import com.vipul.demo.response.RequestOperationStatus;
import com.vipul.demo.service.DataService;
import com.vipul.demo.shared.dto.DataDto;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	DataService dataService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public DataResponseModel createUser(@RequestBody DataRequestModel dataDetails) throws Exception {

		DataResponseModel returnValue = new DataResponseModel();

		ModelMapper modelMapper = new ModelMapper();
		DataDto dataDto = modelMapper.map(dataDetails, DataDto.class);
		DataDto createdata = dataService.createData(dataDto);
		returnValue = modelMapper.map(createdata, DataResponseModel.class);
		return returnValue;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<DataResponseModel> getAllData() {

		List<DataResponseModel> returnValue = new ArrayList<>();

		List<DataDto> allData = dataService.getAllData();

		for (DataDto dataDto : allData) {
			DataResponseModel dataModel = new DataResponseModel();
			ModelMapper modelMapper = new ModelMapper();
			dataModel = modelMapper.map(dataDto, DataResponseModel.class);
			returnValue.add(dataModel);
		}

		return returnValue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public DataResponseModel updateData(@PathVariable long id, @RequestBody DataRequestModel dataDetails) {
		DataResponseModel returnValue = new DataResponseModel();
		ModelMapper modelMapper = new ModelMapper();
		DataDto dataDto = modelMapper.map(dataDetails, DataDto.class);
		DataDto updateData = dataService.updateData(id, dataDto);
		returnValue = modelMapper.map(updateData, DataResponseModel.class);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable long id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		dataService.deleteData(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

}
