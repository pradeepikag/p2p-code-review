import java.util.*;

class Solution {
    public boolean isPredecessor(String one, String two) {
        int diff = 0;
        int i = 0,j = 0;
        while((i < one.length()) && (j < two.length())) {
            if (diff > 2) {
                break;
            }
            if (one.charAt(i) != two.charAt(j)) {
                diff++;
            } else {
                i++;
            }
            j++;
        }
        return (diff <= 1) && (i == one.length());
    }

    public void getTopologicalSorting(Map<Integer, List<Integer>> edges, boolean[] visited, int index,
                                     LinkedList<Integer> topologicalSorting)
    {
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        List<Integer> connectedVertices = edges.get(index);
        if (connectedVertices != null) {
            for (Integer connectedVertex : connectedVertices) {
                getTopologicalSorting(edges, visited, connectedVertex, topologicalSorting);
            }
        }
        topologicalSorting.push(index);
    }

    public int longestStrChain(String[] words) {
        if (words == null) {
            return 0;
        }

        if (words.length == 1) {
            return 1;
        }

        int noOfWords = words.length;
        Map<Integer, List<Integer>> wordsWithLength = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> equalLengthwords = wordsWithLength.get(words[i].length());
            if (equalLengthwords == null) {
                equalLengthwords = new ArrayList<>();
                wordsWithLength.put(words[i].length(), equalLengthwords);
            }
            equalLengthwords.add(i);
        }

        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            int wordLength = words[i].length();
            List<Integer> possibleSuccesorIndexes = wordsWithLength.get(wordLength+1);
            if (possibleSuccesorIndexes != null) {
                for (Integer index : possibleSuccesorIndexes) {
                    if (isPredecessor(words[i], words[index])) {
                        List<Integer> connectedVertices = edges.get(i);
                        if (connectedVertices == null) {
                            connectedVertices = new ArrayList<>();
                            edges.put(i, connectedVertices);
                        }
                        connectedVertices.add(index);
                    }
                }
            }
        }

        boolean[] visited = new boolean[noOfWords];
        LinkedList<Integer> topologicalSorting = new LinkedList<>();
        for (int i = 0; i < noOfWords; i++) {
            if (!visited[i]) {
                getTopologicalSorting(edges, visited, i, topologicalSorting);
            }
        }

        int[] distance = new int[noOfWords];
        int maxDistance = 0;
        while (!topologicalSorting.isEmpty()) {
            int index = topologicalSorting.pop();
            List<Integer> connectedVertices = edges.get(index);
            if (connectedVertices != null) {
                for (Integer connectedVertex : connectedVertices) {
                    distance[connectedVertex] = Math.max(1 + distance[index], distance[connectedVertex]);
                    maxDistance = Math.max(maxDistance, distance[connectedVertex]);
                }
            }
        }

        return maxDistance+1;
    }
}
