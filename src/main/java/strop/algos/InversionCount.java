package strop.algos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InversionCount {

    private static long numInversions = 0;
    private static final Logger logger = LoggerFactory.getLogger(InversionCount.class);

    public static void main(String[] args) throws IOException {
        Path inputPath = Paths.get(args[0]);
        List<Integer> input = Files.lines(inputPath)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        countInversions(input);
        System.out.println(numInversions);
    }


    public static List<Integer> countInversions(List<Integer> input) {
        if (input.size() <= 1) {
            return input;
        } else if (input.size() == 2) {
            if (input.get(0) > input.get(1)) {
                numInversions++;
                int tmp = input.get(0);
                input.set(0, input.get(1));
                input.set(1, tmp);
            }

            return input;
        } else {
            int midPoint = input.size() / 2;
            return countSplitInversions(countInversions(input.subList(0, midPoint)), countInversions(input.subList(midPoint, input.size())));
        }
    }

    public static List<Integer> countSplitInversions(List<Integer> left, List<Integer> right) {
        int outputSize = left.size() + right.size();
        List<Integer> output = new ArrayList<>(outputSize);

        int leftIndex = 0, rightIndex = 0;
        for (int i = 0; i < outputSize; i++) {
            if (rightIndex >= right.size() || (leftIndex < left.size() && left.get(leftIndex) <= right.get(rightIndex))) {
                output.add(left.get(leftIndex++));
            } else {
                output.add(right.get(rightIndex++));
                numInversions += left.size() - leftIndex;
            }
        }

        return output;
    }
}
