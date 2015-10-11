package data;

import featurea.Pulse;
import featurea.Pulse.Zoom;

public class pulses{
    public static final Pulse pulse1(){return new Pulse(1, null);}
    public static final Pulse pulse10(){return new Pulse(10, new Zoom[]{new Zoom(12, 12), new Zoom(12, 1f/12f)});}
}