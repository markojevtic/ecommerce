package com.prodyna.ecommerce.server.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.resource.dto.AddressDto;
import com.prodyna.ecommerce.server.resource.dto.ContactDto;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import com.prodyna.ecommerce.server.services.CostCenterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CostCenterResourceSpringTest {

    private final static String TEST_ID = "22";
    private final static String TEST_NAME = "Test name";
    private final static Address TEST_ADDRESS = Address.builder().city("Beograd").zip("11000").streetAndNumber("Ludacka 13").build();
    private final static CostCenter TEST_COST_CENTER = CostCenter.builder().costCenterId(TEST_ID).name(TEST_NAME).address(TEST_ADDRESS).build();

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ConversionService conversionService;

    @MockBean
    private CostCenterService costCenterService;

    private MockMvc mockMvc;

    @Before
    public void setupMvcMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loadCostCenterReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(TEST_COST_CENTER)
                .when(costCenterService)
                .load(anyString());

        mockMvc.perform(get(CostCenterResource.createSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$.costCenterId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_COST_CENTER.getName()))
                .andExpect(jsonPath("$.address").value(TEST_COST_CENTER.getAddress()));
    }

    @Test
    public void loadCostCenterReturnsNotFoundStatusOnServiceEntityNotFoundException() throws Exception {
        doThrow(EntityNotFoundException.class)
                .when(costCenterService)
                .load(anyString());

        mockMvc.perform(get(CostCenterResource.createSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound());
    }

    @Test
    public void getAllReturnsProperResourceAndStatusOk() throws Exception {
        doReturn(Collections.singletonList(TEST_COST_CENTER))
                .when(costCenterService)
                .getAll();

        mockMvc.perform(get(CostCenterResource.createLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].costCenterId").value(TEST_ID))
                .andExpect(jsonPath("$[0].name").value(TEST_COST_CENTER.getName()))
                .andExpect(jsonPath("$[0].address").exists())
                .andExpect(jsonPath("$[0].address.zip").value(TEST_ADDRESS.getZip()))
                .andExpect(jsonPath("$[0].address.city").value(TEST_ADDRESS.getCity()))
                .andExpect(jsonPath("$[0].address.streetAndNumber").value(TEST_ADDRESS.getStreetAndNumber()) );;
    }

    @Test
    public void deletePerformsProperActionAndStatusIsNoContent() throws Exception {
        mockMvc.perform(delete(CostCenterResource.createSingleLink(TEST_ID).toString()).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNoContent());

        verify(costCenterService, times(1)).delete(eq(TEST_ID));
    }

    @Test
    public void insertReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_COST_CENTER)
                .when(costCenterService)
                .insert(any(CostCenter.class));

        mockMvc.perform(post(CostCenterResource.createLink().toString()).accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_COST_CENTER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.costCenterId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_COST_CENTER.getName()))
                .andExpect(jsonPath("$.address").value(TEST_COST_CENTER.getAddress()));
    }

    @Test
    public void updateReturnsProperResourceAndStatusIsOk() throws Exception {
        doReturn(TEST_COST_CENTER)
                .when(costCenterService)
                .update(any(CostCenter.class));

        mockMvc.perform(post(CostCenterResource.createLink().toString() + "/update").accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(TEST_COST_CENTER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.costCenterId").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_COST_CENTER.getName()))
                .andExpect(jsonPath("$.address").value(TEST_COST_CENTER.getAddress()));
    }

    @Test
    public void conversionServiceIsAvailableAndConvertsEntityToDto() {
        assertThat(conversionService).isNotNull();
        assertThat(conversionService.canConvert(Address.class, AddressDto.class)).isTrue();
        assertThat(conversionService.canConvert(Contact.class, ContactDto.class)).isTrue();
        assertThat(conversionService.canConvert(CostCenter.class, CostCenterDto.class)).isTrue();
    }

    @Test
    public void resourceLinkAndResourceSingleLinkAreCreated() {
        Link resourcesLink = CostCenterResource.createLink().withSelfRel();
        Link singleResourceLink = CostCenterResource.createSingleLink("13").withSelfRel();
        assertThat(resourcesLink.getHref()).endsWith("/costCenters");
        assertThat(singleResourceLink.getHref()).endsWith("/costCenters/13");
    }
}
