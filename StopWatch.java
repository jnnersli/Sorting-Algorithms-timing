/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc200_jli3_3;

/**
 *
 * @author jli3
 */
public class StopWatch {
    private boolean isStopped;
    private double seconds, startTime, endTime;
    
    public StopWatch() {
        isStopped = true;
        seconds = 0.0;
        startTime = 0.0;
        endTime = 0.0;
    }
    
    //returns time in seconds
    public double elapsed() {
        seconds = (endTime - startTime) / 1000000000;
        return seconds;
    }
    
    public StopWatch start() {
        if (isStopped)
            isStopped = false;
        startTime = System.nanoTime();
        return this;
    }
    
    public StopWatch stop() {
        if (!isStopped)
            isStopped = true;
        endTime = System.nanoTime();
        return this;
    }
    
    //resets time to zero, does not start or stop
    public StopWatch reset() {
        seconds = 0.0;
        return this;
    }
}

