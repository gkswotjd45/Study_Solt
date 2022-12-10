package ch6;

public class Tv
{
    String color;
    boolean power;
    int channel;
    
    void power() {
        this.power = !this.power;
    }
    
    void channelUp() {
        ++this.channel;
    }
    
    void channelDown() {
        --this.channel;
    }
}