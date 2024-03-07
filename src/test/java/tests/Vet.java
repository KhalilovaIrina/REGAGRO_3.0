package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Vet {
    public static void main(String[] args) {


        String[] massive = {"пчела", "ворон", "пчела", "гусь", "чайка"};
System.out.println(Arrays.stream(massive).sorted());

            List<String> birds = new ArrayList<>();
            for (int i = 0; i <= massive.length-1; i++) {
                birds.add(massive[i]);
            }
        System.out.println(birds);
          List<String> sortedBirds = birds.stream().sorted().collect(Collectors.toList());
          System.out.println(sortedBirds);
          System.out.println(sortedBirds.get(0));

          ;


     //   birds.sort(String::compareTo);

    }
}
