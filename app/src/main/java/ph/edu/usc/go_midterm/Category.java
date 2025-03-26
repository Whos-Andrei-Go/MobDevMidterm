package ph.edu.usc.go_midterm;

import java.util.List;

public class Category {
    private String name;
    private String type;
    private List<String> services;

    public Category(String name, String type, List<String> services) {
        this.name = name;
        this.type = type;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<String> getServices() {
        return services;
    }
}

