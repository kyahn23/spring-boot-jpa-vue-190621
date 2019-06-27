package com.bitstudy.web.controller;

import java.util.HashMap;
import java.util.List;

import com.bitstudy.web.common.util.PageProxy;
import com.bitstudy.web.common.util.Printer;
import com.bitstudy.web.domain.CustomerDTO;
import com.bitstudy.web.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CustomerController
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired CustomerService customerService;
    @Autowired CustomerDTO customer;
    @Autowired PageProxy pxy;
    @Autowired Printer p;

    @RequestMapping("/count")
    public String count() {
        System.out.println("CustomerController count() 경로로 들어옴");
        int count = customerService.countAll();

        // util 에 생성한 Printer 테스트
        p.accept("lambda가 출력한 고객의 총 인원 : " + count);

        // System.out.println("고객의 총 인원 : " + count);
        return "100";
    }

    @GetMapping("/{customerId}/{password}")
    public CustomerDTO login(@PathVariable("customerId") String id, @PathVariable("password") String pass) {
        customer.setCustomerId(id);
        customer.setPassword(pass);

        return customerService.login(customer);
    }

    @GetMapping("/page/{pageNum}")
    public List<CustomerDTO> list(@PathVariable String pageNum) {
        // List<CustomerDTO> list = new ArrayList<>();
        // rowCount, page_num, page_size, block_size
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount", customerService.countAll());
        map.put("page_num", pageNum);
        map.put("page_size", "5");
        map.put("block_size","5");
        pxy.execute(map);
        // for (CustomerDTO customer : list) {
        // System.out.print("아이디 : " + customer.getCustomerId() + "\t이름 : " +
        // customer.getCustomerName()+"\n");
        // }
        return customerService.findCustomers(pxy);
    }

    @PostMapping("")
    public HashMap<String, Object> join(@RequestBody CustomerDTO param) {
        System.out.println("====post mapping====");
        System.out.println(param.getCustomerId());
        System.out.println(param.getPassword());
        System.out.println(param.getCustomerName());
        customerService.addCustomer(param);
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", "SUCCESS");

        return map;
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable String customerId) {
        System.out.println("id 검색 진입 : " + customerId);
        return customerService.findCustomerByCustomerId(customerId);

    }

    @PutMapping("/{customerId}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO param) {
        System.out.println("수정할 객체 : " + param.toString());
        int res = customerService.updateCustomer(param);
        if (res == 1) {
            customer = customerService.findCustomerByCustomerId(param.getCustomerId());
        } else {
            System.out.println("컨트롤러 수정 실패");
        }
        return customer;

    }

    @DeleteMapping("/{customerId}")
    public HashMap<String, Object> deleteCustomer(@PathVariable String customerId) {
        HashMap<String, Object> map = new HashMap<>();
        customer.setCustomerId(customerId);
        customerService.deleteCustomer(customer);
        map.put("result", "탈퇴성공");
        return map;

    }
}