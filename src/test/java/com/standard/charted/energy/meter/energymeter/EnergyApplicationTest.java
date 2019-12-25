package com.standard.charted.energy.meter.energymeter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.standard.charted.energy.config.CustomConfig;
import com.standard.charted.energy.controller.EnergyMeterController;
import com.standard.charted.energy.meter.util.LoadMasterDataUtil;
import com.standard.charted.energy.request.ReadingInfo;
import com.standard.charted.energy.request.StoreReadingsRequest;
import com.standard.charted.energy.response.ApiResponse;
import com.standard.charted.energy.service.EnergyMeterService;

@WebMvcTest(value = EnergyMeterController.class)
public class EnergyApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private EnergyMeterService energyMeterService;

	/** The user repository. */
	@MockBean
	private UserRepository userRepository;

	/** The log reading repository. */
	@MockBean
	private LogReadingRepository logReadingRepository;

	/** The custom config. */
	@Mock
	private CustomConfig customConfig;

	@Mock
	private LoadMasterDataUtil loadMasterDataUtil;

	@MockBean
	private ElectricProviderRepository electricProviderRepository;

	@InjectMocks
	private EnergyMeterController energyMeterController;

	@Test
	public void testStoreReadings() throws Exception {

		StoreReadingsRequest storeReadingsRequest = new StoreReadingsRequest();
		storeReadingsRequest.setSmartMeterId("smart-meter-0");
		ReadingInfo readingInfo = new ReadingInfo();
		readingInfo.setReading(11.5);
		readingInfo.setTime(new Date());
		List<ReadingInfo> list = new ArrayList<ReadingInfo>();
		list.add(readingInfo);
		storeReadingsRequest.setElectricityReadings(list);

		String rr = new ObjectMapper().writeValueAsString(storeReadingsRequest);

		Mockito.when(energyMeterService.storeReadings(storeReadingsRequest)).thenReturn(new ApiResponse());

		Users user = new Users();

		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));

		Mockito.when(logReadingRepository.save(Mockito.any(LogElectricReadings.class)))
				.thenReturn(new LogElectricReadings());
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/store-reading").content(rr)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

		MvcResult andReturn = mockMvc.perform(request).andReturn();

		System.out.println(andReturn.getResponse().getContentAsString());

	}

	@Test
	public void testGetRecommandedPlan() throws Exception {

		Mockito.when(energyMeterService.recommandPlan()).thenReturn(new ApiResponse());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/recommand-plan")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult andReturn = mockMvc.perform(request).andReturn();

		System.out.println(andReturn.getResponse().getContentAsString());

	}

	@Test
	public void testCurrentVsAllPlan() throws Exception {

		Mockito.when(energyMeterService.getCurrentVsPlanInfo()).thenReturn(new ApiResponse());

		Users user = new Users();
		ElectricityProviders findBySupplierName = new ElectricityProviders();
		findBySupplierName.setPId("2");
		findBySupplierName.setSupplierName("The Green Eco");

		user.setElectricityProviders(findBySupplierName);
		user.setUserName("Sarah");
		user.setSmartMeterId("smart-meter-0");
		user.setPricePlanId("price-plan-0");

		List<Users> list = new ArrayList<Users>();
		list.add(user);

		Mockito.when(userRepository.findAll()).thenReturn(list);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/current-vs-all-plan")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult andReturn = mockMvc.perform(request).andReturn();

		System.out.println(andReturn.getResponse().getContentAsString());

	}

	@Test
	public void testStoredReading() throws Exception {

		Mockito.when(energyMeterService.getAllStoredReadings()).thenReturn(new ApiResponse());

		LogElectricReadings logElectricReadings = new LogElectricReadings();
		List<LogElectricReadings> ll = new ArrayList<LogElectricReadings>();
		ll.add(logElectricReadings);
		Mockito.when(logReadingRepository.findAll()).thenReturn(ll);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/stored-readings")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult andReturn = mockMvc.perform(request).andReturn();

		System.out.println(andReturn.getResponse().getContentAsString());

	}

}
