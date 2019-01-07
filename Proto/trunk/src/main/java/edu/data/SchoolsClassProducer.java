/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import javax.ws.rs.Produces;

/**
 *
 * @author newe
 */
public class SchoolsClassProducer {

    @Produces
    @SchoolsClass
    public Class createSchoolsClass(){
        return Schools.class;
    }
}
