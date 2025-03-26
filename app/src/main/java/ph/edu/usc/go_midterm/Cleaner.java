package ph.edu.usc.go_midterm;

import java.util.Arrays;

public class Cleaner {

    private int id;
    private String name;
    private int age;
    private String address;
    private String mobileNumber;
    private float rating;
    private String scheduleAvailability;
    private String[] serviceTypes;
    private CapabilityIndex capabilityIndex;

    public Cleaner(int id, String name, int age, String address, String mobileNumber, float rating, String scheduleAvailability, String[] serviceTypes, CapabilityIndex capabilityIndex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.rating = rating;
        this.scheduleAvailability = scheduleAvailability;
        this.serviceTypes = serviceTypes;
        this.capabilityIndex = capabilityIndex;
    }

    public int getId() {
        return id;
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

    public String[] getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(String[] serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public String getServiceTypesAsString() {
        return String.join(", ", serviceTypes);
    }

    public CapabilityIndex getCapabilityIndex() {
        return capabilityIndex;
    }

    public void setCapabilityIndex(CapabilityIndex capabilityIndex) {
        this.capabilityIndex = capabilityIndex;
    }

    public static class CapabilityIndex {
        private int attitude;
        private int cleaningQuality;
        private int customerSatisfaction;

        public CapabilityIndex(int attitude, int cleaningQuality, int customerSatisfaction) {
            this.attitude = Math.max(0, Math.min(100, attitude));
            this.cleaningQuality = Math.max(0, Math.min(100, cleaningQuality));
            this.customerSatisfaction = Math.max(0, Math.min(100, customerSatisfaction));
        }

        public int getAttitude() {
            return attitude;
        }

        public void setAttitude(int attitude) {
            this.attitude = Math.max(0, Math.min(100, attitude));
        }

        public int getCleaningQuality() {
            return cleaningQuality;
        }

        public void setCleaningQuality(int cleaningQuality) {
            this.cleaningQuality = Math.max(0, Math.min(100, cleaningQuality));
        }

        public int getCustomerSatisfaction() {
            return customerSatisfaction;
        }

        public void setCustomerSatisfaction(int customerSatisfaction) {
            this.customerSatisfaction = Math.max(0, Math.min(100, customerSatisfaction));
        }
    }

    public int getOverallCapability() {
        return (capabilityIndex.getAttitude() + capabilityIndex.getCleaningQuality() + capabilityIndex.getCustomerSatisfaction()) / 3;
    }
}
