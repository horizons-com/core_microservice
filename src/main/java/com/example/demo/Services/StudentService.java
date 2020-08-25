package com.example.demo.Services;

import org.apache.tomcat.util.json.JSONParser;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

public class StudentService {
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;
    protected String serviceUrl;

    public StudentService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl: "http://" + serviceUrl;
    }

    public String getAll() {
        return restTemplate.getForObject(serviceUrl + "/students/", String.class);
    }

    public String getStudentById(String id) throws JSONException {
        String studentString = restTemplate.getForObject(serviceUrl + "/students/" + id, String.class);
        JSONObject result = new JSONObject(studentString);
        result.put("messageSentContent", getMessagesSent(result.getString("messagesSent")).get("content"));
        return result.toString();
    }

    private JSONObject getMessagesSent(String id) throws JSONException {
        return new JSONObject( restTemplate.getForObject("http://MESSAGES-MICROSERVICE/messages/" + id, String.class));
    }
}
