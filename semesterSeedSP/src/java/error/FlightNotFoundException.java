/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author steffen
 */
public class FlightNotFoundException extends Exception {
    
    public FlightNotFoundException (String f){
    super(f);
    } 
}
