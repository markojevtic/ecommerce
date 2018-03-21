package com.prodyna.ecommerce.server.service;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.ComponentTest;
import com.prodyna.ecommerce.server.repository.CostCenterRepository;
import com.prodyna.ecommerce.server.repository.entity.Address;
import com.prodyna.ecommerce.server.repository.entity.Contact;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.services.CostCenterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostCenterServiceComponentTest extends ComponentTest {

    private final static String COST_CENTER_ID = "556";
    private final static String COST_CENTER_ID_MUNICH = "987";
    private final static String COST_CENTER_NAME = "Eschborn";
    private final static String CITY = "Beograd";
    private final static String ZIP = "11000";
    private final static String STREET_AND_NUMBER = "Ludacka 13";

    private final static String CONTACT_NAME = "Contact name";
    private final static String CONTACT_EMAIL = "bestEmail@test.com";
    private final static String CONTACT_PHONE = "38111123456789";

    private final static Contact TEST_CONTACT = Contact.builder().name(CONTACT_NAME).email(CONTACT_EMAIL).phone(CONTACT_PHONE).build();

    private final static List<Contact> TEST_CONTACTS = Collections.singletonList(TEST_CONTACT);
    private final static Address TEST_ADDRESS = Address.builder().city(CITY).zip(ZIP).streetAndNumber(STREET_AND_NUMBER).build();
    private final static CostCenter TEST_COST_CENTER = CostCenter.builder().address(TEST_ADDRESS).costCenterId(COST_CENTER_ID)
            .contactList(TEST_CONTACTS).build();
    private final static CostCenter TEST_COST_CENTER_MUNICH = CostCenter.builder().costCenterId(COST_CENTER_ID_MUNICH).address(TEST_ADDRESS).build();

    @Autowired
    private CostCenterService costCenterService;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Test
    public void insertCostCenterPersistsProperEntity() {
        costCenterService.insert(TEST_COST_CENTER);
        CostCenter retrievedCostCenter = costCenterRepository.findById(COST_CENTER_ID).orElse(null);

        assertThat(retrievedCostCenter).isNotNull();
        assertThat(retrievedCostCenter).isEqualTo(TEST_COST_CENTER);
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertDuplicateCostCenterThrowsException() {
        costCenterService.insert(TEST_COST_CENTER);
        costCenterService.insert(TEST_COST_CENTER);
    }

    @Test
    public void updateCostCenterAltersProperEntry() {
        costCenterService.insert(TEST_COST_CENTER);
        CostCenter retrievedCostCenter = costCenterRepository.findById(COST_CENTER_ID).orElse(null);
        assertThat(retrievedCostCenter).isNotNull();
        assertThat(retrievedCostCenter.getName()).isNull();

        TEST_COST_CENTER.setName(COST_CENTER_NAME);
        costCenterService.update(TEST_COST_CENTER);
        retrievedCostCenter = costCenterRepository.findById(COST_CENTER_ID).orElse(null);
        assertThat(retrievedCostCenter.getName()).isEqualTo(COST_CENTER_NAME);
    }

    @Test
    public void getAllCostCentersReturnsAllInsertedEntities() {
        costCenterService.insert(TEST_COST_CENTER);
        costCenterService.insert(TEST_COST_CENTER_MUNICH);

        List<CostCenter> costCenters = costCenterService.getAll();
        assertThat(costCenters).isNotNull();
        assertThat(costCenters.size()).isEqualTo(2);
        assertThat(costCenters.contains(TEST_COST_CENTER));
        assertThat(costCenters.contains(TEST_COST_CENTER_MUNICH));
    }

    @Test
    public void deleteRemovesProperCostCenter() {
        costCenterService.insert(TEST_COST_CENTER_MUNICH);
        costCenterService.delete(TEST_COST_CENTER_MUNICH.getCostCenterId());

        CostCenter costCenter = costCenterRepository.findById(TEST_COST_CENTER_MUNICH.getCostCenterId()).orElse(null);
        assertThat(costCenter).isNull();
    }

    @Test
    public void loadReturnsProperCostCenter() {
        costCenterService.insert(TEST_COST_CENTER);
        CostCenter loadedCostCenter = costCenterService.load(TEST_COST_CENTER.getCostCenterId());

        assertThat(loadedCostCenter).isEqualTo(TEST_COST_CENTER);
    }

    @Test(expected = EntityNotFoundException.class)
    public void loadNonexistentCostCenterThrowsException() {
        costCenterService.load(COST_CENTER_ID);
    }

}
