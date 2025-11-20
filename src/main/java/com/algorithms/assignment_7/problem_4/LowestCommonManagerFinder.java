package com.algorithms.assignment_7.problem_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LowestCommonManagerFinder {

    public static CommonManager findLowestCommonManager(List<Employee> employees, int empId1, int empId2) {
        if (employees == null || employees.isEmpty()) {
            return null;
        }
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.getId(), employee);
        }
        if (!map.containsKey(empId1) || !map.containsKey(empId2)) {
            return null;
        }
        List<Integer> path1 = buildPath(map, empId1, new HashSet<>());
        List<Integer> path2 = buildPath(map, empId2, new HashSet<>());
        if (path1 == null || path2 == null || path1.isEmpty() || path2.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> depth1 = new HashMap<>();
        for (int i = 0; i < path1.size(); i++) {
            depth1.put(path1.get(i), i);
        }
        CommonManager best = null;
        for (int j = 0; j < path2.size(); j++) {
            int id = path2.get(j);
            if (depth1.containsKey(id)) {
                int d1 = depth1.get(id);
                int d2 = j;
                Employee manager = map.get(id);
                best = new CommonManager(id, manager.getName(), d1, d2);
                break;
            }
        }
        return best;
    }

    private static List<Integer> buildPath(Map<Integer, Employee> map, int startId, Set<Integer> visiting) {
        if (!map.containsKey(startId)) {
            return null;
        }
        if (!visiting.add(startId)) {
            return null;
        }
        Employee current = map.get(startId);
        List<Integer> path = new ArrayList<>();
        path.add(startId);
        Integer supervisorId = current.getSupervisorId();
        if (supervisorId == null) {
            visiting.remove(startId);
            return path;
        }
        List<Integer> parentPath = buildPath(map, supervisorId, visiting);
        visiting.remove(startId);
        if (parentPath == null) {
            return null;
        }
        path.addAll(parentPath);
        return path;
    }

}

