package com.md.demo.Controller;


import com.md.demo.crud.iZipcodesCrud;
import com.md.demo.model.Zipcode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
public class zipcodeController {

    @Autowired
    iZipcodesCrud iZipcodeCrud;
    @Autowired
    RestTemplate restTemplate;


    /*this method takes a zipcode from a Url parameter, and if it exist on the mysql database, then it
    return's the zipcode objekt, if it's not stored then it calls https://dawa.aws.dk/postnumre/{zipcode} and returns
    the value. and last if the response of the external api, is an error , then it returns the value from the url param
    and -1 as a city name
     */

    @GetMapping("/getCity")
    public ResponseEntity<Zipcode> getCity(@RequestParam(name = "zipcode") int zipcode) {

        Optional<Zipcode> optionalZipcode = iZipcodeCrud.findByNr(zipcode);

        if (optionalZipcode.isPresent()) {


            return new ResponseEntity<>(optionalZipcode.get(), HttpStatus.OK);
        }

        try {
            Zipcode response = new RestTemplate().getForObject("https://dawa.aws.dk/postnumre/" + zipcode, Zipcode.class);

            if (response != null) {
                iZipcodeCrud.save(response);
            }


            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception E) {

            return new ResponseEntity<>(new Zipcode(zipcode, "-1"), HttpStatus.OK);


        }
    }


}
