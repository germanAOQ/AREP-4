package edu.escuelaing.arep.springweb;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws IOException 
     */
    @Test
    public void shouldGetHolaMessage() throws IOException
    {
    	URL url = new URL("https://stark-ridge-65206.herokuapp.com/springapp/hola");
        URLConnection urlConnection = url.openConnection();
        BufferedReader read = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine = read.readLine();
        assertTrue(inputLine.equals("Saludos desde Spring Boot!"+" "));
        read.close(); 
    }
    
    
    @Test
    public void shouldGetHolaMessageWithQueryParams() throws IOException
    {
    	URL url = new URL("https://stark-ridge-65206.herokuapp.com/springapp/hola?value=test");
        URLConnection urlConnection = url.openConnection();
        BufferedReader read = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine = read.readLine();
        assertTrue(inputLine.equals("Saludos desde Spring Boot! test"));
        read.close(); 
    }
    
    @Test
    public void shouldGetHelloMessage() throws IOException
    {
    	URL url = new URL("https://stark-ridge-65206.herokuapp.com/springapp/hello");
        URLConnection urlConnection = url.openConnection();
        BufferedReader read = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine = read.readLine();
        assertTrue(inputLine.equals("Hi "));
        read.close(); 
    }
    
    @Test
    public void shouldGetHelloMessageWithQueryParams() throws IOException
    {
    	URL url = new URL("https://stark-ridge-65206.herokuapp.com/springapp/hello?value=test");
        URLConnection urlConnection = url.openConnection();
        BufferedReader read = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine = read.readLine();
        assertTrue(inputLine.equals("Hi test"));
        read.close(); 
    }
    
   
}
