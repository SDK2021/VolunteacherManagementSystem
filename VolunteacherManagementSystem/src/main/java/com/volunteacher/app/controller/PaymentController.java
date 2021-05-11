package com.volunteacher.app.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.PaytmChecksum;
import com.volunteacher.app.model.Donor;
import com.volunteacher.app.model.Payment;
import com.volunteacher.app.service.interfaces.PaymentService;

@Controller
@RequestMapping(path = "/vms")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	Payment payment;
	
	@Autowired
	Environment env;
	
	ServletContext context;
	
	@GetMapping("/donors")
	public ResponseEntity<Object> getDonorList(@RequestParam("page") int id)
	{
		return paymentService.donorList(id);
	}
	
	@PostMapping("/donors")
	public ResponseEntity<Object> addDonor(@RequestBody Donor donor)
	{
		return paymentService.addDonor(donor);
	}
	
	
	@PostMapping("/payments")
	public ResponseEntity<Object> addPayment(@RequestBody Payment payment,HttpServletRequest request)
	{
		ServletContext context = request.getServletContext();
		context.setAttribute("Donor", payment);
		System.out.println(payment.getDonor());
		return new ResponseEntity<Object>(HttpStatus.OK);
//		return paymentService.addPayment(payment);
	}
	
	@GetMapping("/donors/{id}")
	public ResponseEntity<Object> getDonor(@PathVariable int id)
	{
		return paymentService.donorById(id);
	}
	
	@DeleteMapping("/donors/{id}")
	public ResponseEntity<Object> deleteDonor(@PathVariable int id)
	{
		return paymentService.deleteDonor(id);
	}
	
	@GetMapping("/payments")
	public ResponseEntity<Object> getPaymentsList(@RequestParam("page") int id)
	{
		return paymentService.paymentList(id);
	}
	
	@GetMapping("/payments/{id}")
	public ResponseEntity<Object> getPaymentByDonor(@PathVariable int id)
	{
		return paymentService.paymentByDonor(id);
	}
	
	@DeleteMapping("/payments/{id}")
	public ResponseEntity<Object> deletePayment(@PathVariable int id)
	{
		return paymentService.deletePayment(id);
	}
	
	@GetMapping("/redirect-paytm")	
	public ModelAndView redirectPaytm(@RequestParam("phonenumber") String number,@RequestParam("amount") String amount,@RequestParam("email") String email) throws Exception
	{
			String transactionId = createTransactionId();
			ModelAndView modelAndView = new ModelAndView("redirect:"+env.getProperty("paytm.payment.sandbox.paytmUrl"));
			TreeMap<String, String> parameters = new TreeMap<>();
		  	parameters.put("MID", env.getProperty("paytm.payment.sandbox.details.MID"));
		  	parameters.put("CHANNEL_ID", env.getProperty("paytm.payment.sandbox.details.CHANNEL_ID"));
		  	parameters.put("INDUSTRY_TYPE_ID", env.getProperty("paytm.payment.sandbox.details.INDUSTRY_TYPE_ID"));
		  	parameters.put("WEBSITE", env.getProperty("paytm.payment.sandbox.details.WEBSITE"));
		  	parameters.put("CALLBACK_URL", env.getProperty("paytm.payment.sandbox.details.CALLBACK_URL"));
	        parameters.put("MOBILE_NO", number);
	        parameters.put("ORDER_ID", transactionId);
	        parameters.put("TXN_AMOUNT", amount);
	        parameters.put("CUST_ID", "saff");
	        parameters.put("EMAIL", email);
	        String checkSum = getCheckSum(parameters);
	        parameters.put("CHECKSUMHASH", checkSum);
	        modelAndView.addAllObjects(parameters);
		return modelAndView;
	}
	
	@PostMapping("/payment-result")
	public String addPayment(Model model,HttpServletRequest request) throws CloneNotSupportedException
	{
		context = request.getServletContext();  
		 Map<String, String[]> mapData = request.getParameterMap();
	        TreeMap<String, String> parameters = new TreeMap<String, String>();
	        
	        String paytmChecksum = "";
	        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
	            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
	                paytmChecksum = requestParamsEntry.getValue()[0];
	            } else {
	            	parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
	            }
	        }
	        String result;
	        ServletContext context = request.getServletContext();
	        Payment payment = (Payment) context.getAttribute("Donor");
	        payment.setTransactionId(parameters.get("ORDERID"));
	        boolean isValideChecksum = false;
	        System.out.println("RESULT : "+parameters.toString());
	        try {
	            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
	            System.out.println(isValideChecksum + paytmChecksum);
	            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
	                if (parameters.get("RESPCODE").equals("01")) {
	                    result = "Payment Successful";
	                //    paymentService.addDonor(payment.getDonor());
	                    paymentService.addPayment(payment);
	                } else {
	                    result = "Payment Failed";
	                }
	            } else {
	                result = "Checksum mismatched";
	            }
	            request.removeAttribute("Donor");
	        } catch (Exception e) {
	            result = e.toString();
	        }
	        model.addAttribute("result",result);
	        parameters.remove("CHECKSUMHASH");
	        model.addAttribute("parameters",parameters);
	        return "result";
	}
	
	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, env.getProperty("paytm.payment.sandbox.merchantKey"));
	}
	
	private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
	        return PaytmChecksum.verifySignature(parameters, env.getProperty("paytm.payment.sandbox.merchantKey"), paytmChecksum);
	}
	   
	public String createTransactionId()
	{
		String transactionId = ""; 
		Random random = new Random();
		String number = "1234567890";
		String smallAlphabetic = "abcdefghijklmnopqrstuvwxyz";
		String capitalAlphabetic = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String combineString = number + smallAlphabetic + capitalAlphabetic;
		char values[] = combineString.toCharArray();
		int PasswordLength = 18;
		for(int i = 0;i<PasswordLength;i++)
		{
			transactionId += values[random.nextInt(combineString.length())];
		}
		System.out.println(transactionId);
		return transactionId;
	}
}
