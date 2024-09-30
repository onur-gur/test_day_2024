package com.orchestration.delivery.integration;

import com.orchestration.delivery.dto.BuyerDto;
import com.orchestration.delivery.dto.DeliveryCreateCommand;
import com.orchestration.delivery.dto.PointDto;
import com.orchestration.delivery.model.DeliveryDao;
import com.orchestration.delivery.model.DeliveryType;
import com.orchestration.delivery.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class DeliveryControllerIT extends AbstractTestIT {

    @Autowired
    private DeliveryRepository deliveryRepository;


    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        overrideProps(registry);
    }

    @Test
    public void should_create_delivery() {
        //given
        DeliveryCreateCommand command = createDeliveryCommand(113L);
        mockStoreSearch();

        //when
        ResponseEntity<Void> response =
                testRestTemplate.exchange("/delivery/create", HttpMethod.POST, new HttpEntity<>(command, null), Void.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        List<DeliveryDao> deliveryDaos = deliveryRepository.findAll();
        assertThat(deliveryDaos)
                .hasSize(1)
                .extracting("price", "quantity", "description", "deliveryType", "storeId")
                .containsExactlyInAnyOrder(
                        tuple(command.getPrice(), command.getQuantity(), command.getDescription(), command.getDeliveryType(), command.getStoreId())
                );

        assertThat(deliveryDaos.get(0).getBuyer())
                .extracting("firstName", "lastName", "phoneNumber", "email", "address")
                .containsExactlyInAnyOrder(command.getBuyer().getFirstName(), command.getBuyer().getLastName(), command.getBuyer().getPhoneNumber(), command.getBuyer().getEmail(), command.getBuyer().getAddress());

        assertThat(deliveryDaos.get(0).getBuyer().getLocation())
                .extracting("x", "y")
                .containsExactlyInAnyOrder(command.getBuyer().getLocation().getX(), command.getBuyer().getLocation().getY());
    }


    private DeliveryCreateCommand createDeliveryCommand(Long storeId) {
        BuyerDto buyer = new BuyerDto();
        buyer.setAddress("test address");
        buyer.setEmail("email@email.com");
        buyer.setFirstName("firstName test");
        buyer.setLastName("lastName test");
        buyer.setPhoneNumber("909008007060");
        buyer.setLocation(new PointDto(34.57485303747497, 36.7741834997352));

        DeliveryCreateCommand command = new DeliveryCreateCommand();
        command.setDeliveryType(DeliveryType.SCHEDULED);
        command.setDescription("test description");
        command.setPrice(new BigDecimal("10.00"));
        command.setQuantity(10);
        command.setStoreId(storeId);
        command.setBuyer(buyer);

        return command;
    }
}
