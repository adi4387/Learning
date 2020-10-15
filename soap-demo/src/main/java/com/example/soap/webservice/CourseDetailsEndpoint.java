package com.example.soap.webservice;

import com.example.soap.courses.CourseDetails;
import com.example.soap.courses.GetCourseDetailsRequest;
import com.example.soap.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @PayloadRoot(namespace = "http://soap.example.com/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = getCourseDetails();
        response.setCourseDetails(courseDetails);

        return response;
    }

    private CourseDetails getCourseDetails() {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(123);
        courseDetails.setName("Spring Soap Demo");
        courseDetails.setDescription("Learn Soap Basic");
        return courseDetails;
    }
}
