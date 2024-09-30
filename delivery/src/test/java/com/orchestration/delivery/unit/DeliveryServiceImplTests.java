package com.orchestration.delivery.unit;

import com.orchestration.delivery.dto.*;
import com.orchestration.delivery.exception.NotFoundException;
import com.orchestration.delivery.model.DeliveryDao;
import com.orchestration.delivery.model.DeliveryType;
import com.orchestration.delivery.repository.DeliveryRepository;
import com.orchestration.delivery.service.DeliveryServiceImpl;
import com.orchestration.delivery.service.httpClient.StoreApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class DeliveryServiceImplTests {

    @InjectMocks
    DeliveryServiceImpl deliveryServiceImpl;

    @Mock
    DeliveryRepository deliveryRepository;

    @Mock
    StoreApiClient storeApiClient;

    @Captor
    ArgumentCaptor<DeliveryDao> deliveryDaoArgumentCaptor;


    @Test
    void create_whenNoStoreFound_shouldThrowException() {
        //given
        DeliveryCreateCommand command = createDeliveryCommand(35L);
        when(storeApiClient.search(anyDouble(), anyDouble())).thenReturn(createStoreSearchResponse(12L));

        //when
        final Throwable exception = catchThrowable(() -> deliveryServiceImpl.create(command));

        //then
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("no store found in your area");
    }

    @Test
    void create_whenStoreFound_shouldNotThrowException() {
        //given
        DeliveryCreateCommand command = createDeliveryCommand(12L);

        when(storeApiClient.search(anyDouble(), anyDouble()))
                .thenReturn(createStoreSearchResponse(12L));

        //when
        final Throwable exception = catchThrowable(() -> deliveryServiceImpl.create(command));

        //then
        assertThat(exception).isNull();
        verify(deliveryRepository).save(deliveryDaoArgumentCaptor.capture());
    }

    private DeliveryCreateCommand createDeliveryCommand(Long storeId) {
        BuyerDto buyer = new BuyerDto();
        buyer.setAddress("address");
        buyer.setEmail("email@email.com");
        buyer.setFirstName("firstName");
        buyer.setLastName("lastName");
        buyer.setPhoneNumber("909008007060");
        buyer.setLocation(new PointDto(34.57485303747497, 36.7741834997352));

        DeliveryCreateCommand command = new DeliveryCreateCommand();
        command.setDeliveryType(DeliveryType.SCHEDULED);
        command.setDescription("test description");
        command.setPrice(BigDecimal.TEN);
        command.setQuantity(10);
        command.setStoreId(storeId);
        command.setBuyer(buyer);

        return command;
    }

    private StoreSearchResponse createStoreSearchResponse(Long storeId) {
        PointDto pointDto1 = new PointDto(34.57485303747497, 36.7741834997352);
        PointDto pointDto2 = new PointDto(34.57485303747497, 36.7741834997352);
        PointDto pointDto3 = new PointDto(34.57485303747497, 36.7741834997352);
        PointDto pointDto4 = new PointDto(34.57485303747497, 36.7741834997352);

        StoreSearchDto storeSearchDto = new StoreSearchDto();
        storeSearchDto.setId(storeId);
        storeSearchDto.setAddress("address");
        storeSearchDto.setEmail("email@email.com");
        storeSearchDto.setDeliveryType(DeliveryType.SCHEDULED);
        storeSearchDto.setPhone("909008007060");
        storeSearchDto.setName("store name");
        storeSearchDto.setLogoUrl("logo_url");
        storeSearchDto.setDeliveryArea(List.of(pointDto1, pointDto2, pointDto3, pointDto4));

        StoreSearchResponse storeSearchResponse = new StoreSearchResponse(List.of(storeSearchDto));

        return storeSearchResponse;
    }
}
