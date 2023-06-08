package com.softplan.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softplan.api.dto.request.OrderRequestDTO;
import com.softplan.api.dto.request.OrderStatusRequest;
import com.softplan.api.dto.response.OrderResponseDTO;
import com.softplan.api.dto.request.RegisterNoteRequestDTO;
import com.softplan.api.dto.complement.OrderDTO;
import com.softplan.api.dto.complement.StatusEnum;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerStep<T> {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    OrderResponseDTO orderResponseDTO;
    OrderDTO OrderDTOResponse;
    Long orderService;

    @When("call service post {string}")
    public void callServicePost(String url) throws Exception {
        MvcResult result = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getOrderRequestDTO())))
                .andExpect(status().isCreated()).andReturn();
        orderResponseDTO = (OrderResponseDTO) objectMapper.readValue(result.getResponse().getContentAsString(), OrderResponseDTO.class);
        orderService = orderResponseDTO.getId();

    }

    @Then("I call the query pending order service {string}")
    public void iCallTheQueryPendingOrderService(String url) throws Exception {
        MvcResult result = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        orderResponseDTO = (OrderResponseDTO) objectMapper.readValue(result.getResponse().getContentAsString(), OrderResponseDTO.class);
        validaFieldsResponseDTOPedding(orderResponseDTO);
    }

    @Then("Start order fulfillment no path {string}")
    public void startOrderFulfillmentNoPath(String url) throws Exception {
        MvcResult result = mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getOrderStatusRequest())))
                .andExpect(status().isOk()).andReturn();
        String responseContent = result.getResponse().getContentAsString();
        OrderDTOResponse = (OrderDTO) objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        validaFieldsOrderStartOrder(OrderDTOResponse);
    }

    private OrderStatusRequest getOrderStatusRequest() {
        OrderStatusRequest request = new OrderStatusRequest();
        request.setOrderNumber(orderService);
        request.setTechnicalName("Gilson");
        return request;
    }

    @Then("I add an annotation to the order no path {string}")
    public void iAddAnAnnotationToTheOrderNoPath(String url) throws Exception {
        MvcResult result = mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getRegisterNoteRequestDTO())))
                .andExpect(status().isOk()).andReturn();
    }

    @And("Perform the closing of the service order {string}")
    public void performtheClosingOfTheServiceOrder(String url) throws Exception {
        MvcResult result = mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getOrderStatusRequest())))
                .andExpect(status().isOk()).andReturn();
        String responseContent = result.getResponse().getContentAsString();
        OrderDTOResponse = (OrderDTO) objectMapper.readValue(result.getResponse().getContentAsString(), OrderDTO.class);
        validaFieldsCloseOrder(OrderDTOResponse);
    }

    @Then("Consult all orders {string}")
    public void consultAllOrders(String url) throws Exception {
        MvcResult result = mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        orderResponseDTO = (OrderResponseDTO) objectMapper.readValue(result.getResponse().getContentAsString(), OrderResponseDTO.class);
    }

    @And("Delete all order records {string}")
    public void deleteAllOrderRecords(String url) throws Exception {
        mockMvc.perform(delete(url, orderService)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andReturn();
    }

    private RegisterNoteRequestDTO getRegisterNoteRequestDTO() {
        RegisterNoteRequestDTO request = new RegisterNoteRequestDTO();
        request.setOrderNumber(orderService);
        request.setDescription("Aguardando peças");
        request.setStatus(StatusEnum.PENDING);
        request.setTechnicalName("Roberto");
        return request;
    }

    private OrderRequestDTO getOrderRequestDTO() {

        OrderRequestDTO request = new OrderRequestDTO();
        request.setName("TESTE");
        request.setEquipament("Celular");
        request.setBrand("LTDA");
        request.setEmail("teste@gmail.com");
        request.setProblem("Não liga");
        request.setTelephone("61997854612");
        request.setType("Eletronico");
        request.setAddress("Rua 22");

        return request;
    }

    private void validaFieldsResponseDTOPedding(OrderResponseDTO orderResponseDTO) {
        orderResponseDTO.getOrders().forEach(orderDTO -> {
            OrderRequestDTO request = getOrderRequestDTO();
            Assert.assertEquals(orderDTO.getCustomer().getName(), request.getName());
            Assert.assertEquals(orderDTO.getCustomer().getEmail(), request.getEmail());
            Assert.assertEquals(orderDTO.getCustomer().getTelephone(), request.getTelephone());
            Assert.assertEquals(orderDTO.getCustomer().getAddress(), request.getAddress());
            Assert.assertEquals(orderDTO.getEquipament().getEquipament(), request.getEquipament());
            Assert.assertEquals(orderDTO.getEquipament().getBrand(), request.getBrand());
            Assert.assertEquals(orderDTO.getEquipament().getType(), request.getType());
            Assert.assertEquals(orderDTO.getProblem(), request.getProblem());

        });
    }

    private void validaFieldsOrderStartOrder(OrderDTO response) {
        OrderRequestDTO request = getOrderRequestDTO();
        Assert.assertEquals(response.getCustomer().getName(), request.getName());
        Assert.assertEquals(response.getCustomer().getEmail(), request.getEmail());
        Assert.assertEquals(response.getCustomer().getTelephone(), request.getTelephone());
        Assert.assertEquals(response.getCustomer().getAddress(), request.getAddress());
        Assert.assertEquals(response.getEquipament().getEquipament(), request.getEquipament());
        Assert.assertEquals(response.getEquipament().getBrand(), request.getBrand());
        Assert.assertEquals(response.getEquipament().getType(), request.getType());
        Assert.assertEquals(response.getProblem(), request.getProblem());
        Assert.assertNotNull(response.getStarDate());
        Assert.assertEquals(response.getStatus(), StatusEnum.STARTED);
    }

    private void validaFieldsCloseOrder(OrderDTO orderDTOResponse) {
        Assert.assertEquals(orderDTOResponse.getStatus(), StatusEnum.CLOSE);
        Assert.assertNotNull(orderDTOResponse.getEndDate());
    }
}
