package no.ntnu.idata2302.lab04;

import java.util.ArrayList;

public class Heap {

    public static Heap fromValues(int... values) {
        var heap = new Heap();
        for (var each : values) {
            heap.insert(each);
        }
        return heap;
    }

    public void insert(Integer k) {
        array.add(k);

        int index = array.size() - 1;

        while (index > 1 && array.get(index) < array.get(parentOf(index))) {
            swap(index, parentOf(index));

            index = parentOf(index);
        }
    }


    private ArrayList<Integer> array;

    public Heap() {
        array = new ArrayList<Integer>();
        array.add(0);
    }

    public int takeMinimum() {
        if (array.size() <= 1) {
            throw new RuntimeException("Heap is empty");
        }

        // Step 1: Remove the root, which is the minimum element.
        int min = array.get(1);

        // Step 2: Move the last element to the root.
        int lastElement = array.remove(array.size() - 1);

        if (array.size() > 1) {
            array.set(1, lastElement);

            int index = 1;

            // Step 3: Compare this last element with its children.
            while (leftChildOf(index) < array.size()) {
                int smallerChildIndex = leftChildOf(index);

                // Check if the right child exists and is smaller than the left child.
                if (rightChildOf(index) < array.size() && array.get(rightChildOf(index)) < array.get(leftChildOf(index))) {
                    smallerChildIndex = rightChildOf(index);
                }

                // Step 4: If the element is larger than one of its children, swap it with the smaller child.
                if (array.get(index) > array.get(smallerChildIndex)) {
                    swap(index, smallerChildIndex);
                    index = smallerChildIndex;
                } else {
                    break;
                }
            }
        }

        return min;
    }


    public void decreaseKey(int i, int k) {
        // TODO: Implement this operation
        throw new RuntimeException("Not yet implemented");
    }

    private int parentOf(int index) {
        return index / 2;
    }

    private int leftChildOf(int index) {
        return index * 2;
    }

    private int rightChildOf(int index) {
        return index * 2 + 1;
    }

    void swap(int pos1, int pos2) {
        int temp = array.get(pos1);
        array.set(pos1, array.get(pos2));
        array.set(pos2, temp);
    }
}
