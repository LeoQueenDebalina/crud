package com.example.crud;

import com.example.crud.contrller.MyController;
import com.example.crud.entity.MyEntity;
import com.example.crud.repository.MyRepository;
import com.example.crud.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CrudApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private MyRepository myRepository;
	@Test
	public void myRepositoryTestFindById(){
		Optional<MyEntity> expectedResult = Optional.ofNullable(new MyEntity(1,"Gopi","9933"));
		int id = 1;
		assertEquals(expectedResult, this.myRepository.findById(id));
	}
	@Test
	public void myRepositoryTestSave(){
		MyEntity expectedResult = new MyEntity();
		expectedResult.setName("Nill");
		expectedResult.setPhoneNumber("9944564587");
		assertEquals(expectedResult, this.myRepository.save(expectedResult));
	}
	@Test
	public void myRepositoryTestUpdate(){
		MyEntity expectedResult = new MyEntity();
		expectedResult.setId(10);
		expectedResult.setName("Raju");
		expectedResult.setPhoneNumber("9944564587");
		assertEquals(expectedResult, this.myRepository.save(expectedResult));
	}
	@Test
	public void myRepositoryTestDelete(){
		int id = 10;
		this.myRepository.deleteById(id);
		verify(this.myRepository, times(1)).deleteById(id);
	}
	@Autowired
	private MyService myService;
	@MockBean
	private MyRepository myRepositoryTemp;
	@Test
	public void myServiceTestGetAll(){
		when(this.myRepositoryTemp.findAll()).thenReturn(Stream.of(new MyEntity(1,"Gopi","9933"),new MyEntity(2,"Deblina","9922")).collect(Collectors.toList()));
		int expectedResult = 2;
		assertEquals(expectedResult, this.myService.getAll().size());
	}
	@Test
	public void myServiceTestGetById(){
		int id = 1;
		Optional<MyEntity> temp = Optional.ofNullable(new MyEntity(1,"Gopi","9933"));
		when(this.myRepositoryTemp.findById(id)).thenReturn(temp);
		MyEntity expectedResult = new MyEntity(1,"Gopi","9933");
		assertEquals(expectedResult, this.myService.getById(id));
	}
	@Test
	public void myServiceTestSaveAllData(){
		MyEntity expectedResult = new MyEntity(1,"Gopi","9933");
		when(this.myRepositoryTemp.save(expectedResult)).thenReturn(expectedResult);
		assertEquals(expectedResult, this.myService.saveAllData(expectedResult));
	}
	@Test
	public void myServiceTestUpdateData(){
		MyEntity expectedResult = new MyEntity(1,"Gopi","9933");
		when(this.myRepositoryTemp.save(expectedResult)).thenReturn(expectedResult);
		assertEquals(expectedResult, this.myService.updateData(expectedResult));
	}
	@Test
	public void myServiceTestDeleteById() {
		int id = 1;
		this.myService.deleteById(id);
		verify(this.myRepositoryTemp, times(1)).deleteById(id);
	}
	@Autowired
	private MyController myController;
	@MockBean
	private MyService myServiceTemp;
	@Test
	public void MyControllerTestGetAll(){
		when(this.myServiceTemp.getAll()).thenReturn(Stream.of(new MyEntity(1,"Gopi","9933"),new MyEntity(2,"Deblina","9922")).collect(Collectors.toList()));
		int expectedResult = 2;
		assertEquals(expectedResult, this.myController.getAll().size());
	}
	@Test
	public void MyControllerTestGetById(){
		int id = 1;
		MyEntity temp = new MyEntity(1,"Gopi","9933");
		when(this.myServiceTemp.getById(id)).thenReturn(temp);
		MyEntity expectedResult = new MyEntity(1,"Gopi","9933");
		assertEquals(expectedResult, this.myController.getById(id));
	}
	@Test
	public void MyControllerTestSaveAllData(){
		MyEntity expectedResult = new MyEntity(1,"Gopi","9933");
		when(this.myServiceTemp.saveAllData(expectedResult)).thenReturn(expectedResult);
		assertEquals(expectedResult, this.myController.saveData(expectedResult));
	}
	@Test
	public void MyControllerTestUpdateData(){
		MyEntity expectedResult = new MyEntity(1,"Gopi","9933");
		when(this.myServiceTemp.updateData(expectedResult)).thenReturn(expectedResult);
		assertEquals(expectedResult, this.myController.updateData(expectedResult));
	}
	@Test
	public void MyControllerTestDeleteById() {
		int id = 1;
		this.myController.deleteById(id);
		verify(this.myServiceTemp, times(1)).deleteById(id);
	}
}
