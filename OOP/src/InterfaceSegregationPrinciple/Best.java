package InterfaceSegregationPrinciple;

interface Powerrable {
    void powerOn();

    void powerOff();
}

interface Soundable {
    void playSound();
}

interface Displayable {
    void displayImage();
}

class Television implements Powerrable, Soundable, Displayable {
    public void powerOn() {}
    public void powerOff() {}
    public void playSound() {}
    public void displayImage() {}
}

class Radio implements Powerrable, Soundable {
    public void powerOn() {}
    public void powerOff() {}
    public void playSound() {}
}