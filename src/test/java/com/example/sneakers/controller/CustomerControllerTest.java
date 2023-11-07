package com.example.sneakers.controller;

import com.example.sneakers.request.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

  @Autowired
  MockMvc mockMvc;

  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void customerCreateSuccessful() throws Exception {
    CustomerRequest cur = new CustomerRequest();
    cur.setName("AAA");
    cur.setSurname("BBB");
    cur.setUsername("CCC");
    cur.setPassword("DDD");
    cur.setMail("a@gmail.com");
    String jsonBody = mapper.writeValueAsString(cur);
    mockMvc.perform(post("/api/customers/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody))
            .andExpect(status().isCreated());

  }

  @Test
  public void customerCreateFailedMailFormat_ThrowsBadReq() throws Exception {
    CustomerRequest cur = new CustomerRequest();
    cur.setName("AAA");
    cur.setSurname("BBB");
    cur.setUsername("CCC");
    cur.setPassword("DDD");
    cur.setMail("EEE");
    String jsonBody = mapper.writeValueAsString(cur);
    mockMvc.perform(post("/api/customers/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody))
            .andDo(print()).andExpect(status().isBadRequest());

  }

  @Test
  @WithMockUser
  public void customerUpdateSuccessful() throws Exception {
    CustomerRequest cur = new CustomerRequest();
    cur.setName("AAA");
    cur.setSurname("BBB");
    cur.setUsername("CCC");
    cur.setPassword("DDD");
    cur.setMail("b@gmail.com");
    String jsonBody = mapper.writeValueAsString(cur);
    mockMvc.perform(put("/api/customers/1/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody))
            .andExpect(status().isOk());

  }

  @Test
  @WithMockUser
  public void customerUpdate_Failed_notFound() throws Exception {
    CustomerRequest cur = new CustomerRequest();
    cur.setName("AAA");
    cur.setSurname("BBB");
    cur.setUsername("CCC");
    cur.setPassword("DDD");
    cur.setMail("b@gmail.com");
    String jsonBody = mapper.writeValueAsString(cur);
    mockMvc.perform(put("/api/customers/8/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody))
            .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser
  @Sql({"/com/example/sneakers/clear.sql", "/com/example/sneakers/controller/customer/customer.sql", "/com/example/sneakers/controller/sneaker/sneaker.sql"})
  public void addSneakerToCustomerOwnedList_Successful() throws Exception {
    mockMvc.perform(put("/api/customers/1/addOwnedSneaker/1"))
            .andDo(print()).andExpect(status().isOk());
  }

}
