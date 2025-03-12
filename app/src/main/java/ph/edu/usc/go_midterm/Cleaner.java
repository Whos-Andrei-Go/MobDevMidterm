package ph.edu.usc.go_midterm;

public class Cleaner {

    private String name;
    private int age;
    private String address;
    private String mobileNumber;
    private float rating;
    private String scheduleAvailability;

    private CapabilityIndex capabilityIndex;

    public Cleaner(String name, int age, String address, String mobileNumber, float rating, String scheduleAvailability, CapabilityIndex capabilityIndex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.rating = rating;
        this.scheduleAvailability = scheduleAvailability;
        this.capabilityIndex = capabilityIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getScheduleAvailability() {
        return scheduleAvailability;
    }

    public void setScheduleAvailability(String scheduleAvailability) {
        this.scheduleAvailability = scheduleAvailability;
    }

    public CapabilityIndex getCapabilityIndex() {
        return capabilityIndex;
    }

    public void setCapabilityIndex(CapabilityIndex capabilityIndex) {
        this.capabilityIndex = capabilityIndex;
    }

    public static class CapabilityIndex {
        private float attitude;
        private float cleaningQuality;
        private float customerSatisfaction;

        public CapabilityIndex(float attitude, float cleaningQuality, float customerSatisfaction) {
            this.attitude = attitude;
            this.cleaningQuality = cleaningQuality;
            this.customerSatisfaction = customerSatisfaction;
        }

        public float getAttitude() {
            return attitude;
        }

        public void setAttitude(float attitude) {
            this.attitude = attitude;
        }

        public float getCleaningQuality() {
            return cleaningQuality;
        }

        public void setCleaningQuality(float cleaningQuality) {
            this.cleaningQuality = cleaningQuality;
        }

        public float getCustomerSatisfaction() {
            return customerSatisfaction;
        }

        public void setCustomerSatisfaction(float customerSatisfaction) {
            this.customerSatisfaction = customerSatisfaction;
        }
    }

    public float getOverallCapability() {
        return (capabilityIndex.getAttitude() + capabilityIndex.getCleaningQuality() + capabilityIndex.getCustomerSatisfaction()) / 3;
    }
}
