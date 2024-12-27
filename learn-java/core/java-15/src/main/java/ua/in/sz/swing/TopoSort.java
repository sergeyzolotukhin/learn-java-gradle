package ua.in.sz.swing;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TopoSort {

    @Getter
    @Setter
    private static class Node implements Iterable<Node> {
        private final int nodeId;
        private final List<Node> adList = new LinkedList<>();
        private boolean visited;

        public Node(int nodeId) {
            this.nodeId = nodeId;
            visited = false;
        }

        public void addNeighbor(Node n) {
            adList.add(n);
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public Iterator<Node> iterator() {
            return adList.iterator();
        }
    }

    private static String topoSort(Node currentNode) {
        currentNode.setVisited(true);
        StringBuilder strBuffer = new StringBuilder();

        for(Node n : currentNode) {
            if(!n.isVisited()) {
                strBuffer.append(topoSort(n));
            }
        }

        strBuffer.append(currentNode.getNodeId());
        strBuffer.append(" | ");
        return strBuffer.toString();
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[5];
        nodes[0] = new Node(0);
        nodes[1] = new Node(1);
        nodes[2] = new Node(2);
        nodes[3] = new Node(3);
        nodes[4] = new Node(4);

        nodes[0].addNeighbor(nodes[1]);
        nodes[0].addNeighbor(nodes[2]);

        nodes[1].addNeighbor(nodes[3]);

        nodes[2].addNeighbor(nodes[3]);
        nodes[2].addNeighbor(nodes[4]);

        System.out.println("| " + topoSort(nodes[0]));
    }
}
