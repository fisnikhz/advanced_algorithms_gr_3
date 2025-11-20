package com.algorithms.assignment_7.problem_4;

public record CommonManager(int managerId, String managerName, int distanceToEmp1, int distanceToEmp2) {

    @Override
    public String toString() {
        return "CommonManager{" +
                "managerId=" + managerId +
                ", managerName='" + managerName + '\'' +
                ", distanceToEmp1=" + distanceToEmp1 +
                ", distanceToEmp2=" + distanceToEmp2 +
                '}';
    }
}

